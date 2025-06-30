package com.hehe.cost_control_api.service;

import com.hehe.cost_control_api.model.FeedbackMessage;

public interface FeedbackMessageService {

    FeedbackMessage generateFeedbackMessage(Float savingPercent);
    FeedbackMessage generateDefaultFeedbackMessage();
}
