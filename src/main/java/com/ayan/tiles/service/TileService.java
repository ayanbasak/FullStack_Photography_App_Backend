package com.ayan.tiles.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ayan.tiles.entity.Tile;

public interface TileService {

	public List<Tile> findAll();

	public Tile findById(Long theId);

	public Tile save(Tile theTile);

	public void deleteById(Long theId);
	
	Page<Tile> findAll(Pageable pageable, String searchText);

	Page<Tile> findAll(Pageable pageable);
}
