package com.hehe.cost_control_api.repository;

import com.hehe.cost_control_api.model.Category;
import com.hehe.cost_control_api.model.Income;
import com.hehe.cost_control_api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>, JpaSpecificationExecutor<Income> {

    @Query("SELECT DISTINCT e.category FROM Income e WHERE e.user = :user")
        List<Category> findDistinctIncomeCategoriesByUser(@Param("user") Users user);

}
