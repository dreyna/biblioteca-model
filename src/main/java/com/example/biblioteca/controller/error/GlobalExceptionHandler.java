package com.example.biblioteca.controller.error;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ProblemDetail problem(HttpStatusCode status, String title, String detail, HttpServletRequest req) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(status, detail);
        pd.setTitle(title);
        pd.setType(URI.create("about:blank"));
        pd.setInstance(URI.create(req.getRequestURI()));
        pd.setProperty("timestamp", OffsetDateTime.now().toString());
        pd.setProperty("path", req.getRequestURI());
        return pd;
    }
    // 404: recurso de negocio no encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFound(ConfigDataResourceNotFoundException ex, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problem(HttpStatus.NOT_FOUND, "Recurso no encontrado", ex.getMessage(), req));
    }

    // 404: no existe endpoint (ruta equivocada)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ProblemDetail> handleNoHandler(NoHandlerFoundException ex, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problem(HttpStatus.NOT_FOUND, "Endpoint no encontrado", "La ruta solicitada no existe.", req));
    }

    // 400: cuerpo JSON inválido
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> handleUnreadable(HttpMessageNotReadableException ex, HttpServletRequest req) {
        return ResponseEntity.badRequest()
                .body(problem(HttpStatus.BAD_REQUEST, "Solicitud inválida", "El cuerpo de la solicitud es inválido o malformado.", req));
    }

    // 400: validaciones @Valid fallidas
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        ProblemDetail pd = problem(HttpStatus.BAD_REQUEST, "Datos inválidos", "Se encontraron errores de validación.", req);
        Map<String, List<String>> errors = new LinkedHashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            errors.computeIfAbsent(fe.getField(), k -> new ArrayList<>()).add(fe.getDefaultMessage());
        }
        pd.setProperty("errors", errors);
        return ResponseEntity.badRequest().body(pd);
    }

    // 400: validaciones por ConstraintViolation (p.ej. @RequestParam, @PathVariable)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest req) {
        ProblemDetail pd = problem(HttpStatus.BAD_REQUEST, "Parámetros inválidos", "Se encontraron violaciones de restricción.", req);
        List<String> errors = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .toList();
        pd.setProperty("errors", errors);
        return ResponseEntity.badRequest().body(pd);
    }

    // 400/422: error de negocio
    @ExceptionHandler({BusinessException.class, ServiceException.class})
    public ResponseEntity<ProblemDetail> handleBusiness(RuntimeException ex, HttpServletRequest req) {
        // Puedes devolver 422 si distingues "entidad procesable"
        ProblemDetail pd = problem(HttpStatus.UNPROCESSABLE_ENTITY, "Error de negocio", ex.getMessage(), req);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(pd);
    }

    // 409: conflictos de integridad (FK, Unique, etc.)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDetail> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(problem(HttpStatus.CONFLICT, "Conflicto de datos", "La operación viola restricciones de integridad.", req));
    }

    // 400: parámetro requerido faltante
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ProblemDetail> handleMissingParam(MissingServletRequestParameterException ex, HttpServletRequest req) {
        ProblemDetail pd = problem(HttpStatus.BAD_REQUEST, "Parámetro faltante",
                "Falta el parámetro requerido: " + ex.getParameterName(), req);
        return ResponseEntity.badRequest().body(pd);
    }

    // 4xx/5xx lanzadas explícitamente como ErrorResponseException (opcional)
    @ExceptionHandler(ErrorResponseException.class)
    public ResponseEntity<ProblemDetail> handleErrorResponse(ErrorResponseException ex, HttpServletRequest req) {
        ProblemDetail pd = ex.getBody();
        if (pd.getInstance() == null) {
            pd.setInstance(URI.create(req.getRequestURI()));
        }
        if (pd.getTitle() == null) {
            pd.setTitle("Error");
        }
        pd.setProperty("timestamp", OffsetDateTime.now().toString());
        pd.setProperty("path", req.getRequestURI());
        return ResponseEntity.status(ex.getStatusCode()).body(pd);
    }

    // 500: cualquier otra excepción no controlada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGeneric(Exception ex, HttpServletRequest req) {
        ProblemDetail pd = problem(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno",
                "Ha ocurrido un error no esperado. Intente nuevamente.", req);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(pd);
    }
}
