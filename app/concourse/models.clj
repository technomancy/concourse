(ns concourse.models
  (:use [concourse session]))

(defn make-date
  "Return a new Date, but don't be ridiculous about arguments."
  [year month day]
  (java.util.Date. (- year 1970) (- month 1) day))

;;; Gatherings

(defstruct gathering :name :description
           :organizer :attendees
           :length :chosen-time :earliest :latest)

;; Mocked out for now
(def *gatherings* (ref []))

;;; People

(defstruct person :name :email :hashed-password :gatherings)

;; Mocking this out for now.
(dosync
 (ref-set *gatherings* [(struct-map gathering
                          :name "My Gathering"
                          :description "A wonderful time for getting together."
                          :organizer "technomancy@gmail.com"
                          :attendees (ref {"technomancy@gmail.com" (ref {(make-date 2009 12 10) #{10 11 12}})})
                          :length 1
                          :earliest-day (make-date 2009 12 10)
                          :earliest-hour 10
                          :latest-day (make-date 2009 12 18)
                          :latest-hour 18)])
 (ref-set *people* [(struct-map person
                      :name "Phil Hagelberg" :email "technomancy@gmail.com"
                      :hashed-password "8d42e738c7adee551324955458b5e2c0b49ee655"
                      :gatherings (first @*gatherings*))]))
;;; Times

(defn weeks-for [gathering])

(defn times-for [gathering]
  (second (first (filter #(= (:email (current-person) %)) @(:attendees gathering)))))

(defn update-times [gathering times]
  (dosync (ref-set times-for times)))