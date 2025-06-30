package com.hehe.cost_control_api.service.impl;

import com.hehe.cost_control_api.model.FeedbackMessage;
import com.hehe.cost_control_api.model.enums.TypeLevel;
import com.hehe.cost_control_api.repository.FeedbackMessageRepository;
import com.hehe.cost_control_api.service.FeedbackMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class FeedbackMessageServiceImpl implements FeedbackMessageService {

    private final FeedbackMessageRepository feedbackMessageRepository;

    @Override
    public FeedbackMessage generateFeedbackMessage(Float savingPercent) {
        if (savingPercent == null) return  generateDefaultFeedbackMessage();

        List<FeedbackMessage> candidates = feedbackMessageRepository.findAllMatchingValue(savingPercent);
        if (candidates.isEmpty()) return generateDefaultFeedbackMessage();

        int index = new Random().nextInt(candidates.size());
        return candidates.get(index);
    }

    public FeedbackMessage generateDefaultFeedbackMessage() {
        return feedbackMessageRepository.findByTypeLevel(TypeLevel.DEFAULT);
    }

}
