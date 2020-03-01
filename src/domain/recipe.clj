(ns domain.recipe
    (:require [schema.core :as s]
              [ring.swagger.schema :refer [coerce!]]))
  
  (s/defschema Recipe {
    :id                 Integer
    :name               String
    :description        String
    :estimatedTime      Double
    :nutritionalValue   Double
    :categoryId         Integer
  })


  (s/defschema NewRecipe (dissoc Recipe :id))
  
  