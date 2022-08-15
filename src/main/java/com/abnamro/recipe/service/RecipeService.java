package com.abnamro.recipe.service;


import com.abnamro.recipe.domain.Recipe;
import com.abnamro.recipe.entity.RecipeEntity;
import com.abnamro.recipe.exceptions.RecipeNotFoundException;
import com.abnamro.recipe.filter.RecipeFilter;
import com.abnamro.recipe.filter.RecipeSpecification;
import com.abnamro.recipe.mapper.RecipeMapper;
import com.abnamro.recipe.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    /*
     * Retrieve all recipes from DB
     */
    public List<Recipe> viewRecipes() {
        log.info("RecipeService::viewRecipes()");
        var entities = recipeRepository.findAll();
        return entities.stream().map(RecipeMapper::mapToRecipe).collect(Collectors.toList());
    }

    public Recipe createRecipe(Recipe recipe) {
        log.info("RecipeService::createRecipe()");
        var recipeEntity = RecipeMapper.mapToRecipeEntity(recipe);
        var entity = recipeRepository.save(recipeEntity);
        return RecipeMapper.mapToRecipe(entity);
    }


    public List<Recipe> searchRecipes(final RecipeFilter recipeFilter) {
        log.info("RecipeService::searchRecipes()");
        var entities = recipeRepository.findAll(RecipeSpecification.getRecipeByCriteria(recipeFilter));
        return entities.stream().map(RecipeMapper::mapToRecipe).collect(Collectors.toList());
    }

    public void updateRecipe(Recipe recipe, Long id) {
        log.info("RecipeService::updateRecipe()");
        var optionalRecipeEntity = recipeRepository.findById(id);
        if (optionalRecipeEntity.isEmpty()) {
            throw new RecipeNotFoundException("recipe is not found for recipe Id : " + id);
        }
        var foundEntity = optionalRecipeEntity.get();

        foundEntity.setRecipeId(id);
        foundEntity.setRecipeName(recipe.getRecipeName());
        foundEntity.setInstructions(recipe.getInstructions());
        foundEntity.setNumberOfServings(recipe.getNumberOfServings());
        foundEntity.setIsVegetarian(recipe.getIsVegetarian());
        foundEntity.setIngredients(recipe.getIngredients());
        foundEntity.setCreatedDate(recipe.getCreatedDate());
        foundEntity.setUpdatedDate(recipe.getUpdatedDate());

        RecipeMapper.mapToRecipe(recipeRepository.save(foundEntity));
    }

    public void deleteRecipe(Long id) {
        log.info("RecipeService::deleteRecipe()");
        Optional<RecipeEntity> optionalRecipeEntity = recipeRepository.findById(id);
        if (optionalRecipeEntity.isEmpty()) {
            throw new RecipeNotFoundException("recipe is not found for recipe Id : " + id);
        }
        recipeRepository.deleteById(id);
    }
}
