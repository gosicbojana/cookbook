(ns queries.category_query
    (:require [database.connection]
              [domain.category :refer :all]
              [korma.core :refer :all]
    )
)

(defentity category)

(defn get-category [id]
    (first
    (select category
      (where {:id [= id]} )
      (limit 1)))
  )