package com.abnamro.recipe.entity;

import com.abnamro.recipe.domain.Ingredient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "RECIPE")
public class RecipeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long recipeId;
  private String recipeName;
  private String instructions;
  private Integer numberOfServings;
  private boolean vegetarian;
  @ElementCollection(targetClass = Ingredient.class, fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  private List<Ingredient> ingredients;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime createdDate;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime updatedDate;
}
