(ns concourse
  (:use (compojure html http)))

;; View stuff

(defn layout
  "A function to generate the standard outline of a HTML page."
  [title & body]
  (html
    (doctype :html5)
    [:html
      [:head
        [:title title]]
      [:body
        body]]))

;; Dashboard

(defn dashboard-view []
  (layout "Your Gatherings"
          [:h4 "Your Gatherings"]
          [:ul (map dashboard-gathering-link *gatherings*)]))
           

(defn dashboard-gathering-link [gathering]
  (if (current-user-initiated? gathering)
    [:li (link-to (str "/edit/" (:id gathering))
                  (:name gathering))]
    (if (current-user-invited-to? gathering)
      [:li (link-to (str "/invitation/" (:id gathering))
                    (:name gathering))])))