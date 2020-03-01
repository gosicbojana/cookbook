(ns domain.category
    (:require [schema.core :as s]
              [ring.swagger.schema :refer [coerce!]]))
  
  (s/defschema Category {
    :id                 Integer
    :name               String
    :description        String
  })
  
  