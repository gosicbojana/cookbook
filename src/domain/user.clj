(ns domain.user
    (:require [schema.core :as s]
              [ring.swagger.schema :refer [coerce!]]))
  
  (s/defschema User {
    :id                 Integer
    :firstName          String
    :lastName           String
    :username           String
    :password           String
  })

  (s/defschema NewUser (dissoc User :id))
  
  