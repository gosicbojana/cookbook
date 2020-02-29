(ns domain.recipe
    (:require [schema.core :as s]))
  
  (s/defschema Category {
    :id                 Integer
    :name               String
    :description        String
  })
  
  