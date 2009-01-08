(ns concourse.views.dashboard
  (:use [concourse views gatherings])
  (:require [concourse.views gathering]))

(defn dashboard-view []
  (layout "Your Gatherings"
          [:h4 "Your Gatherings"]
          [:ul (map concourse.views.gathering/link-to-gathering @*gatherings*)]))
