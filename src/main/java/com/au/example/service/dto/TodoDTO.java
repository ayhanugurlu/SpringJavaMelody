package com.au.example.service.dto;

import jakarta.persistence.Id;

public record TodoDTO(@Id Long id, String description, boolean done) {

}
