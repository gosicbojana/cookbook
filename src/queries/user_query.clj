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

  (defn update-user [id userUpdated]
    (def foundUser (get-user id))
    (def existingUserByUsername (get-user-by-username (get userUpdated :username)))
    (if foundUser 
      (if (and existingUserByUsername (not= id (get existingUserByUsername :id))) 
        "User already exists"
        (update user
          (set-fields {
            :firstName (get userUpdated :firstName)
            :lastName (get userUpdated :lastName)
            :username (get userUpdated :username)
            :password (get userUpdated :password)
          })
          (where {:id [= id]}))
      )
      "User with supplied Id doesn't exist"
    )
  )
            
  (defn delete-user [id]
    (def foundUser (get-user id))
    (if foundUser 
      (delete user
        (where {:id [= id]}))
      "User with supplied id doesn't exist"
    )
  )
  
        