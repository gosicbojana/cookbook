(ns domain.recipe
    (:require [schema.core :as s]))
  
  (s/defschema Recipe {
    :id                 Integer
    :name               String
    :description        String
    :estimatedTime      Double
    :nutritionalValue   Double
    :categoryId         Integer
  })
  
  