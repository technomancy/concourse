;; (add-classpath "file:///home/phil/src/concourse3/app/")

(ns concourse
  (:use (compojure html http validation control)
        (concourse models views session)))

;; Routes

(defservlet concourse-servlet
  (GET "/"               (ensure-login session (dashboard-view)))

;;;   (GET "/new"            (ensure-login (new-gathering-form))) ;; TODO
;;;   (POST "/new"           (ensure-login (new-gathering))) ;; TODO

;;;   (GET "/edit/:id"       (ensure-login (edit-gathering-form))) ;; TODO
;;;   (PUT "/edit/:id"       (ensure-login (edit-gathering))) ;; TODO

  (GET "/invitation/:id" (ensure-login session (edit-invitation-form)))
  (PUT "/invitation/:id" (ensure-login session (edit-invitation-view)))

;;;   (GET "/login"          (login-form)) ;; TODO
;;;   (POST "/login"         (login)) ;; TODO
;;;   (GET "/logout"         (ensure-login (logout-form))) ;; TODO
;;;   (POST "/logout"        (ensure-login (logout))) ;; TODO
;;;   (GET "/signup"         (signup-form)) ;; TODO
;;;   (POST "/signup"        (signup)) ;; TODO
  (GET "/static/*"
       (serve-file "public" (route :*)))

;;;   (ANY "*"               (page-not-found))
  )
