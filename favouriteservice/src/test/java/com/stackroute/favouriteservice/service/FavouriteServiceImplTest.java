package com.stackroute.favouriteservice.service;


import com.stackroute.favouriteservice.domain.Favourite;
import com.stackroute.favouriteservice.exception.FoodAlreadyExistsException;
import com.stackroute.favouriteservice.exception.FoodNotFoundException;
import com.stackroute.favouriteservice.repo.FavouriteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Favourite.class)
public class FavouriteServiceImplTest {
	
	@Mock
	private transient FavouriteRepository repo;
	
	private transient Favourite food;
	
	@InjectMocks
	private FavouriteServiceImpl service;
	
	transient Optional<Favourite> options;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		food = new Favourite();
		options = Optional.of(food);
	}
	
	
	@Test
	public void testMockCreation() {
		assertNotNull("JPA repo creation failed", food);
	}

	@Test
	public void testSaveFoodSuccess() throws FoodAlreadyExistsException {
		when(repo.save(food)).thenReturn(food);
		final boolean flag = service.saveFood(food);
		assertTrue("Saving food success", flag);
		verify(repo,times(1)).save(food);	
		verify(repo,times(1)).findById(food.getNdbno());
	}

	@Test
	public void testDeleteFoodById() throws FoodNotFoundException {
	when(repo.findById(1)).thenReturn(options);
	doNothing().when(repo).delete(food);
	final boolean flag = service.deleteFoodById(food.getNdbno());
	assertTrue("Deleting food success", flag);
	}

	@Test
	public void testgetFoodById() throws FoodNotFoundException {
	when(repo.findById(food.getNdbno())).thenReturn(options);
		final Favourite mv = service.getFoodById(food.getNdbno());
	assertEquals("fetching food by id failed",food.getNdbno(), mv.getNdbno());
	verify(repo,times(1)).findById(food.getNdbno());
	}

	@Test
	public void testgetAllFoods() {
		List<Favourite> foods = new ArrayList<>();
     	foods.add(options.get());
		when(repo.findByUserId("1234")).thenReturn(foods);
	List<Favourite> mvs = service.getMyFoods("1234");
	assertEquals("fetching all foods  failed",foods.size(), mvs.size());
	verify(repo,times(1)).findByUserId("1234");
	}

}
