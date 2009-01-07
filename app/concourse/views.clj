(ns concourse.views
  (:use (compojure html http)
        (concourse models session)))

;; How to fit this into the ns invocation above?
(require 'compojure.html.page_functions)

;; Common functions

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
          [:a {:href "/logout"} "log out"]])]

      [:div {:id "content"}
       ;; TODO: notices
       body]]

     [:div {:id "footer"} [:a {:href "http://technomancy.us"} "By Phil Hagelberg"]]]]))

(defn slug [obj]
  (java.net.URLEncoder/encode (:name obj)))