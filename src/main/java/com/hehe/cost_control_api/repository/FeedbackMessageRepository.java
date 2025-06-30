package com.hehe.cost_control_api.repository;

import com.hehe.cost_control_api.model.FeedbackMessage;
import com.hehe.cost_control_api.model.enums.TypeLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackMessageRepository extends JpaRepository<FeedbackMessage, Long> {

    @Query("SELECT f FROM FeedbackMessage f WHERE :savingPercent BETWEEN f.minScore AND f.maxScore")
    List<FeedbackMessage> findAllMatchingValue(Float savingPercent);

    FeedbackMessage findByTypeLevel(TypeLevel typeLevel);
}
