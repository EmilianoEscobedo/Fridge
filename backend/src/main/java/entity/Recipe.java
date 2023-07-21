package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer cookingTime;
    private String description;
    private String instructions;

    @ManyToMany
    @JoinTable(
            name = "recipe_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<RecipeIngredient> recipeIngredients;

    public Recipe(String name, Integer cookingTime, String description, String instructions) {
        this.name = name;
        this.cookingTime = cookingTime;
        this.description = description;
        this.instructions = instructions;
        this.recipeIngredients = new ArrayList<>();
    }
}
