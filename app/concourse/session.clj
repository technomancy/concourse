(ns concourse.session
  (:use (compojure http)
        (concourse models)))

;; Session functions

(def session)

(defn authenticate "Try to find the person with the given email/password."
  [email password]
  (some #(and (= (:email %) email) (= (:password %) password))
        @*people*))

(defn current-person "Returns the current person." []
  (first @*people*))
;; TODO: don't just mock this out.
;;;   ;; TODO: save this as a thread-local var
;;;   (authenticate (:email @session) (:password @session)))

(defn logged-in? "Is the person logged in?" []
  (not (not (current-person))))

(defmacro ensure-login
  "Run the body if the person is logged in, otherwise send them to the login form."
  [this-session & body]
  ;; TODO: need to pass in session here and bind it dynamically?
  `(binding [session ~this-session]
     (if (logged-in?)
       ~@body
       (redirect-to "/login"))))

