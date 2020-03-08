(ns swagger.api
    (:require [compojure.api.sweet :refer :all]
              [ring.util.http-response :refer :all]
              [domain.recipe :refer :all]
              [domain.category :refer :all]
              [domain.user :refer :all]
              [queries.category_query :refer :all]
              [queries.recipe_query :refer :all]
              [queries.user_query :refer :all]
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
            (ok "Recipe successfully deleted") 
          )
        )

        (PUT "/:id" []
          :summary "Update recipe"
          :path-params [id]
          :body [recipe NewRecipe]
          (def result (update-recipe id recipe))
          (if (= (type result) java.lang.Integer) 
            (ok "Recipe successfully updated") 
            (bad-request result)
          )
        )
      )
 
    (context "/categories" []
        :tags ["categories"]

        (GET "/" []
          :return [Category]
          :summary "Gets all categories"
          (ok (get-categories))
        )


        (GET "/:id" []
          :path-params [id]
          :summary "Get category by id"
          (def getCategoryById (get-category id))
          (if getCategoryById (ok getCategoryById) (not-found))
        )

          
        (POST "/" []
          :summary "Create new category"
          :body [category NewCategory]
          (def result (create-category category))
          (if (= (type result) java.lang.String) 
            (bad-request result)
            (ok result) 
          )
        )

        (DELETE "/:id" []
          :summary "Delete category"
          :path-params [id]
          (def result (delete-category id))
          (if (= (type result) java.lang.String) 
            (bad-request result)
            (ok "Category successfully deleted") 
          )
        )

        (PUT "/:id" []
          :summary "Update category"
          :path-params [id]
          :body [category NewCategory]
          (def result (update-category id category))
          (if (= (type result) java.lang.Integer) 
            (ok "Category successfully updated") 
            (bad-request result)
          )
        )
      )

      (context "/users" []
        :tags ["users"]
  
        (GET "/get-all" []
          :return [User]
          :summary "Gets existing users"
          (ok (get-users))
        )
    
        (GET "/:id" []
          :path-params [id]
          :summary "Get user by id"
          (def getUserById (get-user id))
          (if getUserById (ok getUserById) (not-found))
        )

      
        (GET "/" []
          :query-params [username]
          :summary "Get user by username"
          (def getUserByUsername (get-user-by-username username))
          (if getUserByUsername (ok getUserByUsername) (not-found))
        )

        (POST "/" []
          :summary "Create new user"
          :body [user NewUser]
          (def result (create-user user))
          (if (= (type result) java.lang.String) 
            (bad-request result)
            (ok result) 
          )
        )
      )
    )
  )
    
