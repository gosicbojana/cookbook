(ns swagger.api
    (:require [compojure.api.sweet :refer :all]
              [ring.util.http-response :refer :all]
              [domain.recipe :refer :all]
              [queries.recipe_query :refer :all]
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
          (ok (get-recipes)))
      )
    )
)