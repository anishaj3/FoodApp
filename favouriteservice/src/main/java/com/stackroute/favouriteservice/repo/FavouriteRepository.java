package com.stackroute.favouriteservice.repo;

import com.stackroute.favouriteservice.domain.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {

	List<Favourite> findByUserId(String userId);
}
