package com.example.springbootweek08.sms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record ValidateCodeRequest(
        @NotBlank
        @Pattern(regexp = "^1\\d{10}$")
        String phone,
        @NotBlank
        @Size(min = 4, max = 8)
        String code
) {
}