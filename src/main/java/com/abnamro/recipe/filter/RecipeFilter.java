package com.abnamro.recipe.filter;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeFilter {

    private Integer noOfServings;
    private Boolean isVegetarian;
    private String instructions;
    private String includeIngredient;
    private String excludeIngredient;
}
