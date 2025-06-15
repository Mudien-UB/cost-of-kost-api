package com.hehe.cost_control_api.repository.specification;

import com.hehe.cost_control_api.model.Income;
import com.hehe.cost_control_api.model.Users;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IncomeSpecification {
    public static Specification<Income> filterBy(
            Users user,
            LocalDate from,
            LocalDate to,
            String categoryName
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (user != null) {
                predicates.add(cb.equal(root.get("user"), user));
            }
            if (from != null && to != null) {
                predicates.add(cb.between(root.get("incomeDate"), from, to));
            }
            if (categoryName != null && !categoryName.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("category").get("name")), "%" + categoryName.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
