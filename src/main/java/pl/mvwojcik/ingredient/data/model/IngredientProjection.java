package pl.mvwojcik.ingredient.data.model;

public interface IngredientProjection {
   Long getId();
   String getName();
   Integer getKcal();
   Double getProteins();
   Double getCarbohydrates();
   Double getFat();
}
