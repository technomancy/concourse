(ns concourse)

(defstruct gathering
  :name :description :length

  :chosen_time
  :earliest_time :latest_time

  :initiator :attendees)

(def *gatherings* (ref []))