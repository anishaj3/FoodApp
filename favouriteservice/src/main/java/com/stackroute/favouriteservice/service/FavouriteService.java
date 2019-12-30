package com.stackroute.favouriteservice.service;

import com.stackroute.favouriteservice.domain.Favourite;
import com.stackroute.favouriteservice.exception.FoodAlreadyExistsException;
import com.stackroute.favouriteservice.exception.FoodNotFoundException;

import java.util.List;

public interface FavouriteService {
	
	boolean saveFood(Favourite favourite) throws FoodAlreadyExistsException;
	Favourite getFoodById(int id) throws FoodNotFoundException;
	boolean deleteFoodById(int id) throws FoodNotFoundException;
	List<Favourite> getMyFoods(String userId);
	
}
