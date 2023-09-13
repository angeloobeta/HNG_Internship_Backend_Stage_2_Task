package com.example.hng_internship_backend_stage_2_task.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse {
    private String message;
    private int statusCode;
    private LocalDateTime time;
    private Long userId;
}
