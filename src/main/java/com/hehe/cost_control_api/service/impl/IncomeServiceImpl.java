package com.hehe.cost_control_api.service.impl;

import com.hehe.cost_control_api.model.Category;
import com.hehe.cost_control_api.model.Income;
import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.model.enums.IncomeSortType;
import com.hehe.cost_control_api.model.enums.TypeCategory;
import com.hehe.cost_control_api.repository.CategoryRepository;
import com.hehe.cost_control_api.repository.IncomeRepository;
import com.hehe.cost_control_api.repository.specification.IncomeSpecification;
import com.hehe.cost_control_api.service.IncomeService;
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

@Service
@RequiredArgsConstructor
@Transactional
@Validated
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Income addIncome(String source, Double amount, String categoryName, LocalDate incomeDate, Users user, String note) {

        Category category = categoryRepository.findByName(categoryName.toLowerCase()).orElse(null);
        if(category == null){
            category = Category.builder().name(categoryName.toLowerCase()).type(TypeCategory.INCOME).build();
            categoryRepository.save(category);
        }

        Income income = Income.builder()
                .source(source)
                .amount(amount)
                .category(category)
                .incomeDate(incomeDate)
                .user(user)
                .note(note)
                .build();

        return  incomeRepository.save(income);
    }

    @Override
    public Page<Income> listIncome(Users user,IncomeSortType sort, LocalDate from, LocalDate to, String categoryName, int page, int size,  boolean asc) {

        Sort.Direction direction = asc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort.getName()));
        Specification<Income> spec = IncomeSpecification.filterBy(user, from, to, categoryName);

        return  incomeRepository.findAll(spec, pageable);
    }
}
