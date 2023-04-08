package com.computershop.app.repository;

import com.computershop.app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoy extends JpaRepository<Order, Long> {
}
