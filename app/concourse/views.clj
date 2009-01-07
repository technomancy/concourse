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
     [:div#header [:h1 [:a {:href "/"} "Concourse"]]]
     [:div#wrapper.clearfix
      [:div.navbar
       (if (logged-in?)
         [:div#loggedinas
          "Logged in as " [:strong (:email (current-person))] " | "
          [:a {:href "/logout"} "log out"]])]

      [:div#content
       ;; TODO: notices
       body]]

     [:div#footer [:a {:href "http://technomancy.us"} "By Phil Hagelberg"]]]]))

(defn slug [obj]
  (java.net.URLEncoder/encode (:name obj)))