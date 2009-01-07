(ns concourse.views.dashboard
  (:use (concourse models views session)
        (concourse.views gathering)))

(defn dashboard-view []
  (layout "Your Gatherings"
          [:h4 "Your Gatherings"]
          [:ul (map link-to-gathering @*gatherings*)]))
