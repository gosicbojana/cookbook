(ns queries.user_query
    (:require [database.connection]
              [domain.user :refer :all]
              [korma.core :refer :all]
    )
)
  
  (defentity user)
  
  (defn get-users []
    (select user)
  )

  (defn get-user [id]
    (first
    (select user
      (where {:id [= id]} )
      (limit 1))))
  