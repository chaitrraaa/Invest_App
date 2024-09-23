package com.training.Building_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.Building_app.model.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    boolean existsByName(String name);
}
