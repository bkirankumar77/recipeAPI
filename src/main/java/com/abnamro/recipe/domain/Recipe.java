package com.abnamro.recipe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe {

  private Long recipeId;
  @NotNull
  @NotBlank
  @Length(min = 2, max = 50)
  private String recipeName;
  @NotNull
  @NotBlank
  @Length(min = 2, max = 50)
  private String instructions;
  @Min(value = 1)
  @Max(value = 20)
  private Integer numberOfServings;
  private boolean vegetarian;
  @Valid
  @NotEmpty
  @Size(min = 1)
  private List<Ingredient> ingredients;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(required = true, example = "2022-08-14 12:30:00")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime createdDate;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(required = true, example = "2022-08-14 12:30:01")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime updatedDate;
}
