package com.ayan.tiles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ayan.tiles.entity.OrderedItems;

@Repository
public interface OrderedItemsRepository extends JpaRepository<OrderedItems, Long> {

	List<OrderedItems> findAllByUserEmail(String email);

	List<OrderedItems> findAllByStatus(String statustype);

}