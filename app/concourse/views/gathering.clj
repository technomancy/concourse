(ns concourse.views.gathering
  (:use (compojure html)
        (concourse views session
                   gatherings people times)))

(defn link-to-gathering [gathering]
  [:li (link-to (str "/gathering/" (slug gathering)) (:name gathering))])

(defn find-gathering [name]
  (first (filter #(= name (:name %))
                 @*gatherings*)))

(defn day-cell-for [day hour gathering]
  [:td {:class "deselected-time"}
   (check-box (str "h" hour "-d" (get-date day) "-y" (get-year day)))])

(defn hours-row-for [hour week-days gathering]
  [:tr [:td (str hour ":00")]
   (map #(day-cell-for % hour gathering) week-days)])

;; For now, assuming every week is a full week.
(defn week-table [week-days gathering]
  [:table [:thead [:tr [:th "&nbsp;"]
                   ;; TODO: filler here
                   (map #(vector :th (str (get-date %) " " (get-month-str %)))
                        week-days)]]
   [:tbody
    (map #(hours-row-for % week-days gathering)
         (range (:earliest-hour gathering)
                (:latest-hour gathering)))]])

;; TODO: should be using deftmpl here?

(defn gathering-view [name]
  (let [gathering (find-gathering name)]
    (layout (str name " - Concourse")
      [:h4 name]
      ;; TODO: link to edit
      [:p#description (:description gathering)]
      [:p.length (str (:length gathering)
                      (if (= 1 (:length gathering)) " hour" " hours")
                      " long")]
      ;; [:div#right-bar
      ;;  [:p "These people have been invited:"]
      ;;  [:ul (map #([:li [:link_to %1 (str "mailto: " %1)]])
      ;;            @(:attendees gathering))]]
      [:form {:method "PUT"}
       [:div#week-container
        (map #(week-table % gathering) (weeks-for gathering))]
       (include-js "/static/javascripts/gathering.js")
       [:p.explanation "Drag over the times at which you could attend."]
       (submit-button "Save Times")])))
