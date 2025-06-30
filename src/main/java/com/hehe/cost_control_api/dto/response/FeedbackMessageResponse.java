package com.hehe.cost_control_api.dto.response;

import com.hehe.cost_control_api.model.FeedbackMessage;

public record FeedbackMessageResponse(
        String title,
        String message,
        String summary
) {
    public static FeedbackMessageResponse of(FeedbackMessage feedbackMessage) {
        return new FeedbackMessageResponse(
                feedbackMessage.getTitle(),
                feedbackMessage.getMessage(),
                feedbackMessage.getSummary()
        );
    }
}
