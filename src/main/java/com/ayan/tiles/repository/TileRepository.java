package com.ayan.tiles.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ayan.tiles.entity.Tile;

@Repository
public interface TileRepository extends PagingAndSortingRepository<Tile, Long> {
	@Query("From Tile b WHERE b.tileCode=:searchText OR b.imgLink=:searchText OR b.size=:searchText OR b.price=:searchText ORDER BY b.price DESC")
	Page<Tile> findAllTiles(Pageable pageable, @Param("searchText") String searchText);

}