package com.abnamro.recipe.service;

import static org.mockito.Mockito.when;

import com.abnamro.recipe.domain.Recipe;
import com.abnamro.recipe.entity.RecipeEntity;
import com.abnamro.recipe.exceptions.RecipeNotFoundException;
import com.abnamro.recipe.repository.RecipeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class RecipeServiceTest {

  @InjectMocks
  RecipeService recipeService;
  @Mock
  RecipeRepository recipeRepository;

  @Test
  void viewRecipiesTest() {
    List<RecipeEntity> recipeList = new ArrayList<>();
    recipeList.add(getRecipeEntity());
    when(recipeRepository.findAll()).thenReturn(recipeList);
    recipeService.viewRecipes();
  }

  @Test
  void createRecipeTest() {
    when(recipeRepository.save(getRecipeEntity())).thenReturn(getRecipeEntity());
    recipeService.createRecipe(getRecipe());
  }

  @Test
  void updateRecipeTest() {
    when(recipeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getRecipeEntity()));
    when(recipeRepository.save(Mockito.any(RecipeEntity.class))).thenReturn(getRecipeEntity());
    recipeService.updateRecipe(getRecipe(), 1L);
  }

  @Test
  void deleteRecipeTest() {
    when(recipeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getRecipeEntity()));
    recipeService.deleteRecipe(1L);
  }

  @Test
  void updateRecipeNegativeTest() {
    Assertions.assertThrows(RecipeNotFoundException.class, () -> {
      when(recipeRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
      recipeService.updateRecipe(getRecipe(), 1L);
    });

  }

  @Test
  void deleteRecipeNegativeTest() {
    Assertions.assertThrows(RecipeNotFoundException.class, () -> {
      when(recipeRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
      recipeService.deleteRecipe(1L);
    });

  }

  private RecipeEntity getRecipeEntity() {
    RecipeEntity recipe = new RecipeEntity();
    recipe.setRecipeId(1L);
    recipe.setRecipeName("Salmon Soup");
    recipe.setInstructions("take bowl,take pan,salmon peices,chillpowder");
    return recipe;
  }

  private Recipe getRecipe() {
    Recipe recipe = new Recipe();
    recipe.setRecipeId(1L);
    recipe.setRecipeName("Salmon Soup");
    recipe.setInstructions("take bowl,take pan,salmon peices,chillpowder");
    return recipe;
  }
}
