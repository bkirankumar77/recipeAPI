package com.abnamro.recipe.filter;

import com.abnamro.recipe.entity.RecipeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class RecipeSpecification {

  private RecipeSpecification() {
    throw new IllegalStateException("Utility class");
  }

  public static Specification<RecipeEntity> getRecipeByCriteria(RecipeFilter filter) {
    List<Predicate> predicates = new ArrayList<>();
    return (root, query, criteriaBuilder) -> {
      if (filter.getVegetarian() != null) {
        predicates.add(criteriaBuilder
            .equal(root.<Boolean>get("vegetarian"), filter.getVegetarian()));
      }
      if (filter.getNoOfServings() != null) {
        predicates.add(criteriaBuilder
            .ge(root.<Integer>get("numberOfServings"), filter.getNoOfServings()));
      }
      if (filter.getInstructions() != null) {
        predicates.add(criteriaBuilder.like(root.get("instructions"),
            "%" + filter.getInstructions() + "%"));
      }
      if (filter.getIncludeIngredient() != null) {
        criteriaBuilder
            .like(root.get("ingredients"), "%" + filter.getIncludeIngredient().name() + "%");
      }
      if (filter.getExcludeIngredient() != null) {
        criteriaBuilder
            .notLike(root.get("ingredients"), "%" + filter.getExcludeIngredient().name() + "%");
      }
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}