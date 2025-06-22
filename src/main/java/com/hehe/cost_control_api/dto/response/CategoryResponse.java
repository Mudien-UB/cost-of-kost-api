package com.hehe.cost_control_api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hehe.cost_control_api.model.Category;

import java.time.LocalDateTime;

public record CategoryResponse(

        String id,
        String name,
        String type,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createTime
) {

    public static CategoryResponse of(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryResponse(
                category.getId().toString(),
                category.getName(),
                category.getType().name(),
                category.getCreateTime()
        );
    }

}
