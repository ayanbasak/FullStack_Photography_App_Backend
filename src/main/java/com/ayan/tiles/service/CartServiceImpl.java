package com.ayan.tiles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayan.tiles.entity.Cart;
import com.ayan.tiles.exception.EntityIdException;
import com.ayan.tiles.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository repository;

	public List<Cart> findAll() {
		return repository.findAll();
	}

	public Cart findById(Long theId) {
		Optional<Cart> result = repository.findById(theId);
		Cart theCart = null;

		if (result.isPresent()) {
			theCart = result.get();
		} else {
			throw new EntityIdException("Cannot find Cart: Cart Id " + theId + " as it doesn't exist");
		}
		return theCart;
	}

	public Cart save(Cart theCart) {
		repository.save(theCart);
		return theCart;
	}

	public void deleteById(Long theId) {
		findById(theId);
		repository.deleteById(theId);
	}

	@Override
	public List<Cart> findAllByUserEmail(String userEmail) {
		return repository.findAllByUserEmail(userEmail);
	}

	

}
