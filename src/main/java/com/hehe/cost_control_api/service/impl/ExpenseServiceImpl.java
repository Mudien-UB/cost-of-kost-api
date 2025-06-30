package com.hehe.cost_control_api.service.impl;

import com.hehe.cost_control_api.model.Category;
import com.hehe.cost_control_api.model.Expense;
import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.model.enums.ExpenseSortType;
import com.hehe.cost_control_api.model.enums.TypeCategory;
import com.hehe.cost_control_api.repository.CategoryRepository;
import com.hehe.cost_control_api.repository.ExpenseRepository;
import com.hehe.cost_control_api.repository.specification.ExpenseSpecification;
import com.hehe.cost_control_api.service.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Validated
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Expense addExpense(String reason, Double amount, String categoryName, LocalDate expenseDate, Users user, String note) {

        Category category = categoryRepository.findByName(categoryName.toLowerCase()).orElse(null);
        if(category == null) {
            category = Category.builder().name(categoryName.toLowerCase()).type(TypeCategory.EXPENSE).build();

            categoryRepository.save(category);
        }

        Expense expense = Expense.builder()
                .reason(reason)
                .amount(amount)
                .category(category)
                .user(user)
                .expenseDate(expenseDate)
                .note(note)
                .build();

        return expenseRepository.save(expense);
    }

    @Override
    public Page<Expense> listExpense(Users user,ExpenseSortType sort, LocalDate from, LocalDate to, String categoryName, int page, int size,  boolean asc) {

        Sort.Direction direction = asc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort.getName()));

        Specification<Expense> spec = ExpenseSpecification.filterBy(user, from, to, categoryName);

        return  expenseRepository.findAll(spec, pageable);
    }

    @Override
    public List<Category> getExpenseCategories(Users user) {
        return expenseRepository.findDistinctExpenseCategoriesByUser(user);
    }

    @Override
    @Transactional
    public void deleteExpense(Users user, String expenseId) {
        Long id  = Long.valueOf(expenseId);

        Expense expense = user.getExpenseList().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Expense not found"));

        user.getExpenseList().remove(expense);
        expenseRepository.delete(expense);
    }

}
