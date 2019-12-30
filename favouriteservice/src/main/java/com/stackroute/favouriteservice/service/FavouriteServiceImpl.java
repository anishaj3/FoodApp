package com.stackroute.favouriteservice.service;

import com.stackroute.favouriteservice.domain.Favourite;
import com.stackroute.favouriteservice.exception.FoodAlreadyExistsException;
import com.stackroute.favouriteservice.exception.FoodNotFoundException;
import com.stackroute.favouriteservice.repo.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FavouriteServiceImpl implements FavouriteService {

	private FavouriteRepository foodRepo;

	@Autowired
	public FavouriteServiceImpl(final FavouriteRepository foodRepo) {
		super();
		this.foodRepo = foodRepo;
	}


	@Override
	public boolean saveFood(Favourite favourite) throws FoodAlreadyExistsException {
		final Optional<Favourite> object = foodRepo.findById(favourite.getNdbno());
		if (object.isPresent()) {
			throw new FoodAlreadyExistsException("Could not save, food already exsits");
		}
		foodRepo.save(favourite);
		return true;
	}

	@Override
	public Favourite getFoodById(int id) throws FoodNotFoundException {
		final Favourite favourite = foodRepo.findById(id).orElse(null);
		if (favourite == null) {
			throw new FoodNotFoundException("Food not found");
		}		
		return favourite;
	}


	@Override
	public boolean deleteFoodById(int id) throws FoodNotFoundException {
		foodRepo.deleteById(id);
		return true;
	}


	@Override
	public List<Favourite> getMyFoods(String userId) {
		return foodRepo.findByUserId(userId);
	}

}
