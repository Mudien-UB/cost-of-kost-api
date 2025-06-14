package com.hehe.cost_control_api.service.impl;

import com.hehe.cost_control_api.model.Category;
import com.hehe.cost_control_api.model.Expense;
import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.model.enums.TypeCategory;
import com.hehe.cost_control_api.repository.CategoryRepository;
import com.hehe.cost_control_api.repository.ExpenseRepository;
import com.hehe.cost_control_api.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Validated
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Expense addExpense(String reason, Double amount, String categoryName, LocalDate expenseDate, Users user, String note) {

        Category category = categoryRepository.findByName(categoryName.toLowerCase()).orElse(null);
        if(category == null) {
            category = Category.builder().name(categoryName.toLowerCase()).type(TypeCategory.EXPENSE).build();

            categoryRepository.saveAndFlush(category);
        }

        Expense expense = Expense.builder()
                .reason(reason)
                .amount(amount)
                .category(category)
                .user(user)
                .dateExpense(expenseDate)
                .note(note)
                .build();

        return expenseRepository.save(expense);
    }
}
