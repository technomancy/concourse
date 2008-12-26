(ns concourse
  (:use (clojure.contrib seq-utils)))

(defstruct user
  :email :password)

(def *users* (ref {}))

(def *current-user*)

(defn current-user-invited-to?
  "Was the current user invited to this gathering?"
  [gathering]
  (includes? *current-user*
             (:attendees gathering)))

(defn current-user-initiated?
  "Did the current user initiate this gathering?"
  [gathering]
  (= (:initiator gathering) *current-user*))