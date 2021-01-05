package com.ayan.tiles.service;

import java.util.List;

import com.ayan.tiles.entity.Cart;

public interface CartService {

	public List<Cart> findAll();

	public Cart findById(Long theId);

	public Cart save(Cart theCart);

	public void deleteById(Long theId);

	public List<Cart> findAllByUserEmail(String userEmail);

}
