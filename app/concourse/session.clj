(ns concourse)
;; Session functions

(defmacro ensure-login
  "Run the body if the user is logged in, otherwise send them to the login form."
  [& body]
  ;; TODO: need to pass in session here and bind it dynamically?
  `(if (logged-in?)
     ~@body
     (redirect-to "/login")))

(defn logged-in? "Is the user logged in?" []
  (not (not (current-user))))

(defn current-user "Returns the current user." []
  ;; TODO: save this as a thread-local var
  (authenticate (:email @session) (:password @session)))

(defn authenticate "Try to find the user with the given email/password."
  [email password]
  (first (filter (fn [user] (and (= (:email user) email)
                                (= (:password user) password)))
                 *users*)))