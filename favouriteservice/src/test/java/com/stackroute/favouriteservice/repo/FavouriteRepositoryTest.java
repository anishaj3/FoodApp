/*
package com.stackroute.favouriteservice.repo;

import com.stackroute.favouriteservice.domain.Favourite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class FavouriteRepositoryTest {

	@Autowired
	private transient FavouriteRepository repo;

	public FavouriteRepository getRepo() {
		return repo;
	}

	public void setRepo(FavouriteRepository repo) {
		this.repo = repo;
	}
	
	@Test
	public void testSaveFood() throws Exception {
		repo.save(new Favourite("2","NEUMAN'S, CARROT PINEAPPLE BREAD",45001861,"Branded Food Products Database"));
		final Favourite favourite = repo.getOne(2);
		assertThat(favourite.getNdbno()).isEqualTo(2);
	}

@Test
	public void testDeleteFoodById() throws Exception {
		repo.save(new Favourite("1234","NEUMAN'S, CARROT PINEAPPLE BREAD",45001861,"Branded Food Products Database"));
		repo.deleteById(1);
		assertThat(repo.findById(1)).isEmpty();
	}
	
	@Test
	public void testGetAllFoods() throws Exception {		
		repo.save(new Favourite("1234","NEUMAN'S, CARROT PINEAPPLE BREAD",45001861,"Branded Food Products Database"));
		repo.save(new Favourite("1234","NEUMAN'S, CARROT PINEAPPLE BREAD",45001861,"Branded Food Products Database"));
		List<Favourite> favourites = repo.findByUserId("1234");
		assertThat(favourites.size()).isGreaterThan(1);
	}

	
}
*/
