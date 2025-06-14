package com.hehe.cost_control_api.util;

import com.hehe.cost_control_api.dto.response.BaseResponse;
import com.hehe.cost_control_api.dto.response.BaseResponsePageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class BaseResponseUtil {

    public static <T> ResponseEntity<BaseResponse<T>> buildResponse(HttpStatus status, String message, T data) {

        BaseResponse<T> commonResponse = new BaseResponse<T>(
                status.value(),
                message,
                data,
                LocalDateTime.now()
        );

        return ResponseEntity.status(status).body(commonResponse);
    }

    public static <T> ResponseEntity<BaseResponsePageable<T>> buildResponsePageable(HttpStatus status, String message, T data, Integer page, Integer size, Long totalElements, Integer totalPages) {

        BaseResponsePageable<T> response = new BaseResponsePageable<T>(
                status.value(),
                message,
                data,
                page,
                size,
                totalElements,
                totalPages,
                page == totalPages,
                LocalDateTime.now()
        );

        return ResponseEntity.status(status).body(response);
    }

}
