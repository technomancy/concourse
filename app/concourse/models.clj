(ns concourse.models)

(defn make-date
  "Return a new Date, but don't be ridiculous about arguments."
  [year month day]
  (java.util.Date. (- year 1970) (- month 1) day))

;;; Gatherings

(defstruct gathering :name :description
           :organizer :attendees
           :length :chosen-time :earliest :latest)

(defn gatherings-invited-to [] [])
(defn gatherings-initiated [] [])

;;; People

(defstruct person :name :email :hashed-password :gatherings)

(def *people* (ref [(struct-map person
                      :name "Phil Hagelberg" :email "technomancy@gmail.com"
                      :hashed-password "8d42e738c7adee551324955458b5e2c0b49ee655"
                      :gatherings (ref [(struct-map gathering
                                          :name "My Gathering" :id 1
                                          :description "A wonderful time for getting together."
                                          :organizer "technomancy@gmail.com"
                                          :attendees ["technomancy@gmail.com"]
                                          :length 1
                                          :earliest (list (make-date 2009 12 10) 10)
                                          :latest (list (make-date 2009 12 18) 10)
                                          )]))]))
