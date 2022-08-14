package com.abnamro.recipe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.abnamro.recipe.domain.Recipe;
import com.abnamro.recipe.service.RecipeService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class RecipeControllerTest {

  @InjectMocks
  RecipesController recipesController;
  @Mock
  RecipeService recipeService;

  @Test
  void createRecipeTest() {
    when(recipeService.createRecipe(any(Recipe.class))).thenReturn(new Recipe());
    ResponseEntity<Recipe> responseEntity = recipesController.createRecipe(getRecipe());
    assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
  }

  @Test
  void getRecipesTest() {
    when(recipeService.viewRecipes()).thenReturn(new ArrayList<>());
    var recipeList = new ArrayList<>();
    recipeList.add(getRecipe());
    ResponseEntity<List<Recipe>> responseEntity = recipesController.getRecipes();
    assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
  }

  @Test
  void updateRecipeTest() {
    Mockito.doNothing().when(recipeService)
        .updateRecipe(Mockito.any(Recipe.class), Mockito.anyLong());
    var responseEntity = recipesController.updateRecipe(getRecipe(), 1L);
    assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
  }

  @Test
  void deleteRecipeTest() {
    Mockito.doNothing().when(recipeService).deleteRecipe(Mockito.anyLong());
    var responseEntity = recipesController.deleteRecipe(1L);
    assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
  }

  private Recipe getRecipe() {
    Recipe recipe = new Recipe();
    recipe.setRecipeId(1L);
    recipe.setRecipeName("Salmon Soup");
    recipe.setInstructions("take bowl,take pan,salmon peices,chillpowder");
    return recipe;
  }
}
