package com.hehe.cost_control_api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record BaseResponsePageable<T>(
        Integer status,
        String message,
        T content,
        Integer page,
        Integer size,
        Long totalElements,
        Integer totalPages,
        Boolean isLast,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp
) {

}
