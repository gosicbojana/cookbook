(ns queries.recipe_query
    (:require [database.connection]
              [queries.category_query :refer :all]
              [domain.recipe :refer :all]
              [korma.core :refer :all]
    )
)
  
  (defentity recipe)
  
  (defn get-recipes []
    (select recipe)
  )
  
  (defn get-recipe [id]
    (first
    (select recipe
      (where {:id [= id]} )
      (limit 1)))
  )

  (defn get-by-name [name]
    (first
      (select recipe
        (where {:name [= name]} )
        (limit 1)
      )
    )
  )

  (defn create-recipe [newRecipe]
    (def foundRecipe (get-by-name (get newRecipe :name)))
    (def foundCategory (get-category (get newRecipe :categoryId)))
    (if foundRecipe 
      "Recipe already exists"
      (if foundCategory 
          ((def recipeResult 
            (insert recipe
              (values {
                :name (get newRecipe :name)
                :description (get newRecipe :description)
                :estimatedTime (get newRecipe :estimatedTime)
                :nutritionalValue (get newRecipe :nutritionalValue)
                :categoryId (get newRecipe :categoryId)
              })
            ))
            (def createdRecipeId (get recipeResult :generated_key))
            (get-recipe createdRecipeId))
        "Non existing category entered!"
      )
    )
  )

  (defn update-recipe [id recipeUpdated]
    (def foundRecipeName (get-by-name (get recipeUpdated :name)))
    (def foundCategory (get-category (get recipeUpdated :categoryId)))
    (if foundRecipeName 
      "Recipe already exists"
      (if foundCategory 
          (update recipe
            (set-fields {
              :name (get recipeUpdated :name)
              :description (get recipeUpdated :description)
              :estimatedTime (get recipeUpdated :estimatedTime)
              :nutritionalValue (get recipeUpdated :nutritionalValue)
              :categoryId (get recipeUpdated :categoryId)
            }) (where {:id [= id]}))
        "Non existing category entered!"
      )
    )
  )

  (defn delete-recipe [id]
    (def foundRecipe (get-recipe id))
    (if foundRecipe 
      (delete recipe
        (where {:id [= id]}))
        "Recipe with specified ID doesn't exist"
    )
  )