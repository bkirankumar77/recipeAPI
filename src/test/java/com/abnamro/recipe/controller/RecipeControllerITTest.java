package com.abnamro.recipe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.abnamro.recipe.domain.Ingredient;
import com.abnamro.recipe.domain.Recipe;
import com.abnamro.recipe.exceptions.CustomizeExceptionHandler;
import com.abnamro.recipe.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class RecipeControllerITTest {

  @InjectMocks
  RecipesController recipesController;
  @Mock
  RecipeService recipeService;

  @Autowired
  MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(recipesController)
        .setControllerAdvice(new CustomizeExceptionHandler()).build();
  }

  @Test
  void getRecipeTest() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(getRecipe());
    MockHttpServletResponse response = mockMvc
        .perform(get("/api/v1/recipes").contentType(APPLICATION_JSON).content(requestJson))
        .andReturn().getResponse();
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  void createRecipeTest() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(getRecipe());
    MockHttpServletResponse response = mockMvc
        .perform(post("/api/v1/recipes").contentType(APPLICATION_JSON).content(requestJson))
        .andReturn().getResponse();
    assertEquals(HttpStatus.CREATED.value(), response.getStatus());
  }

  @Test
  void updateRecipeTest() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(getRecipe());
    MockHttpServletResponse response = mockMvc
        .perform(put("/api/v1/recipes/1").contentType(APPLICATION_JSON).content(requestJson))
        .andReturn().getResponse();
    assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
  }

  @Test
  void deleteRecipeTest() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(getRecipe());
    MockHttpServletResponse response = mockMvc
        .perform(delete("/api/v1/recipes/1").contentType(APPLICATION_JSON).content(requestJson))
        .andReturn().getResponse();
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  private Recipe getRecipe() {
    Recipe recipe = new Recipe();
    recipe.setRecipeId(1L);
    recipe.setRecipeName("Salmon Soup");
    recipe.setInstructions("take bowl,take pan,salmon peices,chillpowder");
    recipe.setIngredients(Arrays.asList(new Ingredient[]{Ingredient.SALMON}));
    recipe.setVegetarian(false);
    recipe.setNumberOfServings(2);
    recipe.setCreatedDate(LocalDateTime.now());
    recipe.setUpdatedDate(LocalDateTime.now());
    return recipe;
  }
}
