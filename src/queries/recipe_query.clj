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
