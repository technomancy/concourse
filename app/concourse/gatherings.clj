(ns concourse.gatherings
  (:use (concourse util)))

(defstruct gathering :name :description
           :organizer :attendees
           :length :chosen-time :earliest :latest)

;; Mocked out for now
(def *gatherings* (ref []))

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
                          :latest-hour 18)]))