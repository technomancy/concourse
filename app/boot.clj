(add-classpath "file:///home/phil/src/concourse3/app/")

(ns concourse
  (:require concourse)
  (:require [compojure.jetty :as jetty]))

;; Define a new HTTP server on port 8080
(jetty/defserver server
  {:port 8888}
  "/*" concourse/serv)

;; Start the server
(jetty/start server)