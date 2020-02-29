(ns database.connection
    (:require [korma.db :as korma]))
  
  (def db-connection-info (korma/mysql 
    {:classname "com.mysql.jdbc.Driver"
     :subprotocol "mysql"
     :user "bojana"
     :password "bojana"
     :subname "//localhost:3306/recipes"}))
  
  ; set up korma
  (korma/defdb db db-connection-info)