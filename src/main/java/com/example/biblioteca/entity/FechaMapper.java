package com.example.biblioteca.entity;

import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class FechaMapper {
    @Named("nowAsDate")
    public static Date nowAsDate() {
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }
}
