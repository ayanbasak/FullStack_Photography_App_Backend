package com.ayan.tiles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayan.tiles.entity.OrderedItems;
import com.ayan.tiles.exception.EntityIdException;
import com.ayan.tiles.repository.OrderedItemsRepository;

@Service
public class OrderedItemsServiceImpl implements OrderedItemsService {

	@Autowired
	private OrderedItemsRepository repository;

	public List<OrderedItems> findAll() {
		return repository.findAll();
	}

	public OrderedItems findById(Long theId) {
		Optional<OrderedItems> result = repository.findById(theId);
		OrderedItems theOrderedItems = null;

		if (result.isPresent()) {
			theOrderedItems = result.get();
		} else {
			throw new EntityIdException("Cannot find OrderedItems: OrderedItems Id " + theId + " as it doesn't exist");
		}
		return theOrderedItems;
	}

	public OrderedItems save(OrderedItems theOrderedItems) {
		repository.save(theOrderedItems);
		return theOrderedItems;
	}

	public void deleteById(Long theId) {
		findById(theId);
		repository.deleteById(theId);
	}

	@Override
	public List<OrderedItems> findAllByUserEmail(String email) {
		return repository.findAllByUserEmail(email);
	}

	@Override
	public List<OrderedItems> findAllByStatus(String statustype) {
		return repository.findAllByStatus(statustype);
	}

}
