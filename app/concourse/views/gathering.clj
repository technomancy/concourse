(ns concourse.views.gathering
  (:use (compojure html)
        (concourse views session models)))

(defn link-to-gathering [gathering]
  [:li (link-to (str "/gathering/" (slug gathering)) (:name gathering))])

(defn find-gathering [name]
  (first (filter #(= name (:name %))
                 @*gatherings*)))

(defn week-table [week]
  [:table ])

(defn gathering-view [name]
  (let [gathering (find-gathering name)]
    (layout (str name " - Concourse")
      [:h4 name]
      ;; TODO: link to edit
      [:p.description (:description gathering)]
      [:p.length (str (:length gathering)
                      (if (= 1 (:length gathering)) " hour" " hours")
                      " long")]
      ;; [:div {:id "right-bar"}
      ;;  [:p "These people have been invited:"]
      ;;  [:ul (map #([:li [:link_to %1 (str "mailto: " %1)]])
      ;;            @(:attendees gathering))]]
      [:form {:method "PUT"}
       [:div {:id "week-container"}
        (map week-table (weeks-for gathering))]])))