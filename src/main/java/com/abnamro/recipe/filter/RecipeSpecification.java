package com.abnamro.recipe.filter;

import com.abnamro.recipe.entity.RecipeEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class RecipeSpecification {

    private RecipeSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<RecipeEntity> getRecipeByCriteria(RecipeFilter filter) {
        List<Predicate> predicates = new ArrayList<>();
        return (root, query, criteriaBuilder) -> {
            if (filter.getIsVegetarian() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.<Boolean>get("isVegetarian"), filter.getIsVegetarian()));
            }
            if (filter.getNoOfServings() != null) {
                predicates.add(criteriaBuilder
                        .ge(root.<Integer>get("numberOfServings"), filter.getNoOfServings()));
            }
            if (filter.getInstructions() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("instructions")),
                        "%" + filter.getInstructions().toLowerCase() + "%"));
            }
            if (filter.getIncludeIngredient() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("ingredients")), "%" +
                        filter.getIncludeIngredient().toLowerCase() + "%"));
            }
            if (filter.getExcludeIngredient() != null) {
                predicates.add(criteriaBuilder
                        .notLike(criteriaBuilder.lower(root.get("ingredients")),
                                "%" + filter.getExcludeIngredient().toLowerCase() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}