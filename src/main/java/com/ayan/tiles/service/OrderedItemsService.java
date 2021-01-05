package com.ayan.tiles.service;

import java.util.List;

import com.ayan.tiles.entity.OrderedItems;

public interface OrderedItemsService {

	public List<OrderedItems> findAll();

	public OrderedItems findById(Long theId);

	public OrderedItems save(OrderedItems theOrderedItems);

	public void deleteById(Long theId);

	public List<OrderedItems> findAllByUserEmail(String email);

	public List<OrderedItems> findAllByStatus(String statustype);
}
