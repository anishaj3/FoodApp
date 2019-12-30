package com.stackroute.favouriteservice.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.stackroute.favouriteservice.domain.Favourite;
import com.stackroute.favouriteservice.service.FavouriteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration
@RunWith(SpringRunner.class)
@WebMvcTest(FavouriteController.class)
public class FavouriteControllerTest {

    @Autowired
    private transient MockMvc mvc;

    @MockBean
    private transient FavouriteService service;

    @InjectMocks
    private FavouriteController controller;

    private Favourite favourite;

    static List<Favourite> favourites;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
        favourites = new ArrayList<>();
        favourite = new Favourite();
        favourites.add(favourite);
        favourite = new Favourite("1234","NEUMAN'S, CARROT PINEAPPLE BREAD",45001861,"Branded Food Products Database");
        favourites.add(favourite);
        favourite = new Favourite("1234","NEUMAN'S, CARROT PINEAPPLE BREAD",45001861,"Branded Food Products Database");
        favourites.add(favourite);
    }

    @Test
    public void saveNewFoodTest() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb24iLCJpYXQiOjE1MzYxNTE3NDV9.BBMhGNYi2k04soJuPw8LmT4nzhGtIzkRq3bM6n_JvxI";
        when(service.saveFood(favourite)).thenReturn(true);
        mvc.perform(post("/api/v1/food")
                .header("authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(favourite)))
                .andExpect(status().isCreated());
        verify(service, times(1)).saveFood(Mockito.any(Favourite.class));
    }


    @Test
    public void deleteFoodByIdTest() throws Exception {
        when(service.deleteFoodById(1)).thenReturn(true);
        mvc.perform(delete("/api/v1/food/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(favourite)))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteFoodById(1);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getFoodByIdTest() throws Exception {
        when(service.getFoodById(1)).thenReturn(favourite);
        mvc.perform(get("/api/v1/food/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(favourite)))
                .andExpect(status().isOk());
        verify(service, times(1)).getFoodById(1);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getMyFoodsTest() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb24iLCJpYXQiOjE1MzYxNTE3NDV9.BBMhGNYi2k04soJuPw8LmT4nzhGtIzkRq3bM6n_JvxI";
        when(service.getMyFoods("jon")).thenReturn(favourites);
        mvc.perform(get("/api/v1/food/")
                .header("authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service, times(1)).getMyFoods("jon");
        verifyNoMoreInteractions(service);
    }

    private static String jsonToString(final Object obj) {
        String result;
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "JSON Parsing error";
        }
        return result;
    }
}
