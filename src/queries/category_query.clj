(ns queries.category_query
    (:require [database.connection]
              [domain.category :refer :all]
              [korma.core :refer :all]
    )
)

(defentity category)

(defn get-categories []
    (select category)
)
  
(defn get-category [id]
    (first
    (select category
      (where {:id [= id]} )
      (limit 1)))
)

(defn get-by-name-category [name]
    (first
      (select category
        (where {:name [= name]} )
        (limit 1)
      )
    )
)

(defn create-category [newCategory]
    (def cat ( get-by-name-category  (get newCategory :name)))
    (if cat 
      "Category already exists"
          ((def categoryResult 
            (insert category
              (values {
                :name (get newCategory :name)
                :description (get newCategory :description)
              })
            ))
            (def createdCategoryId (get categoryResult :generated_key))
            (get-category createdCategoryId))
    )
)

(defn update-category [id categoryUpdated]
    (def foundCategoryName (get-by-name-category (get categoryUpdated :name)))
    (if foundCategoryName 
      "Category already exists"
          (update category
            (set-fields {
              :name (get categoryUpdated :name)
              :description (get categoryUpdated :description)
            }) (where {:id [= id]}))
    )
)

(defn delete-category [id]
    (def cat (get-category id))
    (if cat 
      (delete category
        (where {:id [= id]}))
        "Category with specified ID doesn't exist"
    )
)