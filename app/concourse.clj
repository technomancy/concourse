(add-classpath "file:///home/phil/src/concourse3/jars/clojure-contrib.jar")
(add-classpath "file:///home/phil/src/concourse3/jars/compojure.jar")
(add-classpath "file:///home/phil/src/concourse3/jars/jetty-6.1.14.jar")
(add-classpath "file:///home/phil/src/concourse3/jars/jetty-util-6.1.14.jar")
(add-classpath "file:///home/phil/src/concourse3/jars/servlet-api-2.5-6.1.14.jar")

(ns concourse
  (:use (compojure html http validation control)
        (concourse models views session)
        (concourse.views dashboard gathering)))

;; Routes

(defservlet serv
  (GET "/"               (ensure-login session (dashboard-view)))

;;;   (GET "/new"            (ensure-login (new-gathering-form))) ;; TODO
;;;   (POST "/new"           (ensure-login (new-gathering))) ;; TODO

;;;   (GET "/edit/:name"       (ensure-login (gathering-edit-form))) ;; TODO
;;;   (PUT "/edit/:name"       (ensure-login (gathering-save))) ;; TODO

  (GET "/gathering/:name" (ensure-login session (gathering-view
                                                 (java.net.URLDecoder/decode (route :name)))))
;;  (PUT "/gathering/:name" (ensure-login session (gathering-save-times)))

;;;   (GET "/login"          (login-form)) ;; TODO
;;;   (POST "/login"         (login)) ;; TODO
;;;   (GET "/logout"         (ensure-login (logout-form))) ;; TODO
;;;   (POST "/logout"        (ensure-login (logout))) ;; TODO
;;;   (GET "/signup"         (signup-form)) ;; TODO
;;;   (POST "/signup"        (signup)) ;; TODO

  (GET "/static/*" (serve-file "public" (route :*)))
  (ANY "*" (page-not-found)))

