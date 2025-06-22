package com.hehe.cost_control_api.repository;

import com.hehe.cost_control_api.model.Category;
import com.hehe.cost_control_api.model.Expense;
import com.hehe.cost_control_api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {

    @Query("SELECT DISTINCT e.category from Expense e WHERE e.user = :user")
    List<Category> findDistinctExpenseCategoriesByUser(@Param("user") Users user);


}
