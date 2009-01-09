(ns concourse.times
  (:use [concourse session]))

;; These functions serve to wrap around the horror of java.util.Date
;; If it ever gets beyond a page or two, I probably need to
;; switch to Joda Time.

(defn make-date
  "Return a new Date, but don't be ridiculous about arguments."
  [year month day] ;; TODO: offset is 1900 instead of 1970?
  (java.util.Date. (- year 1900) (- month 1) day))

(defn get-year [d]
  "Seriously, Java? Zero means 1900? Go take a long walk of a short Jetty."
  (+ 1900 (.getYear d)))

(defn get-month [d]
  "And where to even start here? Days are 1-indexed, but months are
zero-indexed? I've heard of functional programming languages, but
dysfunctional ones... that's new to me."
  (+ 1 (.getMonth d)))

(defn get-month-str [d]
  "Can has nice formatting plz?"
  (["Jan" "Feb" "Mar" "Apr" "May" "Jun" "Jul" "Aug" "Sep" "Oct" "Nov" "Dec"]
     (.getMonth d)))

(defn get-date [d]
  "This one's just for consistency. A refuge of sanity in an ocean of Java, if you will."
  (.getDate d))

(defn get-day-of-week [d]
  "getDay doesn't really make me think of weekdays."
  (.getDay d))

;;; Concourse-specific stuff.

(defn weeks-for [gathering]
  "For now, mocked out to return a single week."
  [(map #(make-date 2009 1 %) (range 4 11))])

(defn times-for [gathering]
  (second (first (filter #(= (:email (current-person) %)) @(:attendees gathering)))))

(defn update-times [gathering times]
  (dosync (ref-set times-for times)))