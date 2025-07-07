package com.hehe.cost_control_api.repository;

import com.hehe.cost_control_api.model.Category;
import com.hehe.cost_control_api.model.Expense;
import com.hehe.cost_control_api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {

    @Query("""
        SELECT DISTINCT e.category
        FROM Expense e
        WHERE e.user = :user
    """)
    List<Category> findDistinctExpenseCategoriesByUser(@Param("user") Users user);

    // Daily
    @Query("""
        SELECT e.expenseDate, SUM(e.amount)
        FROM Expense e
        WHERE e.user = :user
          AND e.expenseDate BETWEEN :from AND :to
        GROUP BY e.expenseDate
        ORDER BY e.expenseDate
    """)
    List<Object[]> groupByDay(
            @Param("user") Users user,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );

    // Weekly
    @Query(value = """
        SELECT TO_CHAR(expense_date, 'IYYY-"W"IW') AS period, SUM(amount)
        FROM expense
        WHERE user_id = :userId
          AND expense_date BETWEEN :from AND :to
        GROUP BY period
        ORDER BY period
    """, nativeQuery = true)
    List<Object[]> groupByWeek(
            @Param("userId") UUID userId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );

    // Monthly
    @Query(value = """
        SELECT TO_CHAR(expense_date, 'YYYY-MM') AS period, SUM(amount)
        FROM expense
        WHERE user_id = :userId
          AND expense_date BETWEEN :from AND :to
        GROUP BY period
        ORDER BY period
    """, nativeQuery = true)
    List<Object[]> groupByMonth(
            @Param("userId") UUID userId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );

}
