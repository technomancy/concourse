(ns concourse.views
  (:use (compojure html http)
        (concourse models session)))

;; View stuff

(defn layout
  "A function to generate the standard outline of a HTML page."
  [title & body]
  (html
   (doctype :html5)
   [:html
    [:head
     [:title title]
     [:script {:src "/static/javascripts/jquery.js" :type "text/javascript"}]
     [:link {:rel "stylesheet" :href "/static/stylesheets/concourse.css" :type "text/css"}]]
    [:body
     [:div {:id "header"} [:h1 [:a {:href "/"} "Concourse"]]]
     [:div.clearfix {:id "wrapper"}
      [:div.navbar
       (if (logged-in?)
         [:div {:id "loggedinas"}
          "Logged in as " [:strong (:email (current-person))] " | "
          [:a {:href "/logout"} "log out"]
          ])]

      [:div {:id "content"}
       ;; TODO: notices
       body]]

     [:div {:id "footer"} [:a {:href "http://technomancy.us"} "By Phil Hagelberg"]]]]))

(defn current-person-initiated? [g] false)
(defn current-person-invited-to? [g] true)

;; Dashboard

(defn dashboard-gathering-link [gathering]
  (if (current-person-initiated? gathering)
    [:li (link-to (str "/edit/" (:id gathering))
                  (:name gathering))]
    (if (current-person-invited-to? gathering)
      [:li (link-to (str "/invitation/" (:id gathering))
                    (:name gathering))])))

(defn dashboard-view []
  (layout "Your Gatherings"
          [:h4 "Your Gatherings"]
          [:ul (map dashboard-gathering-link @(:gatherings (current-person)))]))

;; Gatherings

(defn edit-invitation-form []
  )

(defn edit-invitation-view []
  )