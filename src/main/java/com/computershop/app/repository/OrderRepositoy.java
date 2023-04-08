package com.computershop.app.repository;

import com.computershop.app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoy extends JpaRepository<Order, Long> {
}
