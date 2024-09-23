package com.training.Building_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.Building_app.model.Basket;
import com.training.Building_app.repository.BasketRepository;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    public Basket createBasket(Basket basket) {
        if (basketRepository.existsByName(basket.getName())) {
            throw new RuntimeException("Basket name must be unique");
        }
        return basketRepository.save(basket);
    }
}
