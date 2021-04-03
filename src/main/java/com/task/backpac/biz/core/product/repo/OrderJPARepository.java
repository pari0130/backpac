package com.task.backpac.biz.core.product.repo;

import com.task.backpac.biz.core.product.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJPARepository extends JpaRepository<Order, Long>{
}
