(ns concourse.util)

(defn make-date
  "Return a new Date, but don't be ridiculous about arguments."
  [year month day]
  (java.util.Date. (- year 1970) (- month 1) day))
