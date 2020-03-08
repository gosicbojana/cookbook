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

  (defn get-user-by-username [username]
    (first
    (select user
      (where {:username [= username]} )
      (limit 1))))
      
    
  (defn create-user [newUser]
    (def foundUser (get-user-by-username (get newUser :username)))
    (if foundUser 
      "User already exists"
      ((def userResult (insert user
        (values {
          :firstName (get newUser :firstName)
          :lastName (get newUser :lastName)
          :username (get newUser :username)
          :password (get newUser :password)
          })
        ))
        (def createdUserId (get userResult :generated_key))
        (get-user createdUserId)
      )
    )
  )
        