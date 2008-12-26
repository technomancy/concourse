;; (add-classpath "file:///home/phil/src/concourse3/app/")

(ns concourse
  (:use (compojure html http validation control)
        (concourse models views)))

;; Controllers

(defn ensure-login [args]
  ;; TODO: write
  args)

;; Routes

(defservlet concourse-servlet
  (GET "/"               (ensure-login (dashboard-view)))

;;;   (GET "/new"            (ensure-login (new-gathering-form))) ;; TODO
;;;   (POST "/new"           (ensure-login (new-gathering))) ;; TODO

;;;   (GET "/edit/:id"       (ensure-login (edit-gathering-form))) ;; TODO
;;;   (PUT "/edit/:id"       (ensure-login (edit-gathering))) ;; TODO

;;;   (GET "/invitation/:id" (ensure-login (edit-invitation-form))) ;; TODO
;;;   (PUT "/invitation/:id" (ensure-login (edit-invitation))) ;; TODO

;;;   (GET "/login"          (login-form)) ;; TODO
;;;   (POST "/login"         (login)) ;; TODO
;;;   (GET "/logout"         (ensure-login (logout-form))) ;; TODO
;;;   (POST "/logout"        (ensure-login (logout))) ;; TODO
;;;   (GET "/signup"         (signup-form)) ;; TODO
;;;   (POST "/signup"        (signup)) ;; TODO

;;;   (ANY "*"               (page-not-found))
  )

;; TODO: nicer way to specify which pages require login