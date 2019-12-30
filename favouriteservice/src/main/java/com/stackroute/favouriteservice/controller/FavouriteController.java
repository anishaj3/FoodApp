package com.stackroute.favouriteservice.controller;

import com.stackroute.favouriteservice.domain.Favourite;
import com.stackroute.favouriteservice.exception.FoodAlreadyExistsException;
import com.stackroute.favouriteservice.exception.FoodNotFoundException;
import com.stackroute.favouriteservice.service.FavouriteService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/food")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"})
public class FavouriteController {

	private FavouriteService favouriteService;

	@Autowired
	public FavouriteController(final FavouriteService favouriteService) {
		this.favouriteService = favouriteService;
	}
	/**
	 * @param favourite
	 * @param request
	 * @param response
	 * @return
	 * Rest End point for saving new food
	 */
	@PostMapping
	public ResponseEntity<?> saveNewFood(@RequestBody final Favourite favourite, HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity<?> responseEntity;
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId =Jwts.parser()
							.setSigningKey("secretKey")
							.parseClaimsJws(token)
							.getBody()
							.getSubject();
		System.out.println("UserId: " + userId);
		try {
			favourite.setUserId(userId);
			favouriteService.saveFood(favourite);
			responseEntity = new ResponseEntity<Favourite>(favourite,HttpStatus.CREATED);
		} catch (FoodAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}",HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	/**
	 * @param id
	 * @param favourite
	 * @return
	 * Rest Api end point for updating food details
	 */

	/**
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * Rest Api end point for deleting a particular Food
	 */
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> deleteFood(@PathVariable("id")final int id,HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity<?> responseEntity;
		try {
			favouriteService.deleteFoodById(id);
			responseEntity = new ResponseEntity<String>("{\"message\":\"Food deleted successfully\"}",HttpStatus.OK);
		} catch ( FoodNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}",HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	/**
	 * @param id
	 * @return
	 * This service will return a food when given with a food ID
	 */
	@GetMapping(path="/{id}")
	public ResponseEntity<?> fetchFood(@PathVariable("id")final int id) {
		ResponseEntity<?> responseEntity;
		try {
			final Favourite fetchedFavourite = favouriteService.getFoodById(id);
			responseEntity = new ResponseEntity<Favourite>(fetchedFavourite,HttpStatus.OK);
		} catch ( FoodNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}",HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * This method will retrive All Foods
	 */
	@GetMapping
	public ResponseEntity<?> fetchMylFoods(HttpServletRequest request, HttpServletResponse response) {
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId =Jwts.parser()
							.setSigningKey("secretKey")
							.parseClaimsJws(token)
							.getBody()
							.getSubject();
		final List<Favourite> allFavourites = favouriteService.getMyFoods(userId);
		return new ResponseEntity<List<Favourite>>(allFavourites,HttpStatus.OK);
	}

}
