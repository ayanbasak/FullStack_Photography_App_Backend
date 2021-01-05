package com.ayan.tiles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayan.tiles.entity.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


	List<Cart> findAllByUserEmail(String userEmail);

}