package com.ayan.tiles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ayan.tiles.entity.Tile;
import com.ayan.tiles.exception.EntityIdException;
import com.ayan.tiles.repository.TileRepository;

@Service
public class TileServiceImpl implements TileService {

	@Autowired
	private TileRepository repository;

	public List<Tile> findAll() {
		return (List<Tile>) repository.findAll();
	}

	public Tile findById(Long theId) {
		Optional<Tile> result = repository.findById(theId);
		Tile theTile = null;

		if (result.isPresent()) {
			theTile = result.get();
		} else {
			throw new EntityIdException("Cannot find Tile: Tile Id " + theId + " as it doesn't exist");
		}
		return theTile;
	}

	public Tile save(Tile theTile) {
		repository.save(theTile);
		return theTile;
	}

	public void deleteById(Long theId) {
		findById(theId);
		repository.deleteById(theId);
	}

	@Override
	public Page<Tile> findAll(Pageable pageable, String searchText) {
		return repository.findAllTiles(pageable, searchText);
	}

	@Override
	public Page<Tile> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
