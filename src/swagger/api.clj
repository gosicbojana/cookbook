(ns swagger.api
    (:require [compojure.api.sweet :refer :all]
              [ring.util.http-response :refer :all]
              [domain.recipe :refer :all]
              [queries.recipe_query :refer :all]
              [domain.category :refer :all]
              [queries.category_query :refer :all]
    )
)
  
  (def app
      (api
        {:swagger
        {:ui "/"
          :spec "/swagger.json"
          :data {
              :info {:title "Cookbook" :description ""}
                :tags [
                    {:name "recipes", :description "Recipes API"}
                    ]
                  }
              }
          }
  
      (context "/recipes" []
        :tags ["recipes"]
  
        (GET "/" []
          :return [Recipe]
          :summary "Gets all recipes"
          (ok (get-recipes))
        )
    

        (GET "/:id" []
          :path-params [id]
          :summary "Get recipe by id"
          (def getRecipeById (get-recipe id))
          (if getRecipeById (ok getRecipeById) (not-found))
        )

          
        (POST "/" []
          :summary "Create recipe"
          :body [recipe NewRecipe]
          (def result (create-recipe recipe))
          (if (= (type result) java.lang.String) 
            (bad-request result)
            (ok result) 
          )
        )

        (DELETE "/:id" []
          :summary "Delete recipe"
          :path-params [id]
          (def result (delete-recipe id))
          (if (= (type result) java.lang.String) 
            (bad-request result)
            (ok nil) 
          )
        )

        (PUT "/:id" []
          :summary "Update recipe"
          :path-params [id]
          :body [recipe NewRecipe]
          (def result (update-recipe id recipe))
          (if (= (type result) java.lang.Integer) 
            (ok nil) 
            (bad-request result)
          )
        )
      )
    )
  )