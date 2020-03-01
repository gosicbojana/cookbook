(ns queries.recipe_query
    (:require [database.connection]
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

  (defn create-recipe [newRecipe]
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
            (get-recipe createdRecipeId)
    )
  )

  (defn update-recipe [id recipeUpdated]
          (update recipe
            (set-fields {
              :name (get recipeUpdated :name)
              :description (get recipeUpdated :description)
              :estimatedTime (get recipeUpdated :estimatedTime)
              :nutritionalValue (get recipeUpdated :nutritionalValue)
              :categoryId (get recipeUpdated :categoryId)
            })
            (where {:id [= id]}))
  )

  (defn delete-recipe [id]
      (delete recipe
        (where {:id [= id]}))
  )