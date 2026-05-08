package com.example.springbootweek08.sms.dto;

public record SendCodeResponse(
        String phone,
        int ttlSeconds,
        String codePlain
) {
}