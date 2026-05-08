package com.example.springbootweek08.sms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record SendCodeRequest(
        @NotBlank
        @Pattern(regexp = "^1\\d{10}$", message = "请输入 11 位国内手机号")
        String phone
) {
}