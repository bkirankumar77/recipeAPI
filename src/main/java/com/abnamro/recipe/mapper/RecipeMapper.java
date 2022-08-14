package com.abnamro.recipe.mapper;

import com.abnamro.recipe.domain.Recipe;
import com.abnamro.recipe.entity.RecipeEntity;
import lombok.Data;

@Data
public class RecipeMapper {

  private RecipeMapper() {
    throw new IllegalStateException("Utility class");
  }

  public static Recipe mapToRecipe(RecipeEntity entity) {
    return Recipe.builder().recipeId(entity.getRecipeId())
        .recipeName(entity.getRecipeName())
        .instructions(entity.getInstructions())
        .numberOfServings(entity.getNumberOfServings())
        .vegetarian(entity.isVegetarian())
        .ingredients(entity.getIngredients())
        .createdDate(entity.getCreatedDate())
        .updatedDate(entity.getUpdatedDate()).build();
  }

  public static RecipeEntity mapToRecipeEntity(Recipe recipe) {
    return RecipeEntity.builder().recipeId(recipe.getRecipeId())
        .recipeName(recipe.getRecipeName())
        .instructions(recipe.getInstructions())
        .numberOfServings(recipe.getNumberOfServings())
        .vegetarian(recipe.isVegetarian())
        .ingredients(recipe.getIngredients())
        .createdDate(recipe.getCreatedDate())
        .updatedDate(recipe.getUpdatedDate()).build();
  }
}
