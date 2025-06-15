package com.hehe.cost_control_api.repository;

import com.hehe.cost_control_api.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>, JpaSpecificationExecutor<Income> {

}
