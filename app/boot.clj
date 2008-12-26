(use 'compojure.jetty)
(use 'concourse)

;; Define a new HTTP server on port 8080
(defserver server
  {:port 8080}
  "/*" concourse-servlet)

;; Start the server
(start server)
