(ns concourse.people
  (:use [concourse session])
  (:require [concourse gatherings]))

(defstruct person :name :email :hashed-password :gatherings)

;; Mocking this out for now.
(dosync
 (ref-set *people* [(struct-map person
                      :name "Phil Hagelberg" :email "technomancy@gmail.com"
                      :hashed-password "8d42e738c7adee551324955458b5e2c0b49ee655"
                      :gatherings (first @concourse.gatherings/*gatherings*))]))
;;; Times

(defn weeks-for [gathering])

(defn times-for [gathering]
  (second (first (filter #(= (:email (current-person) %)) @(:attendees gathering)))))

(defn update-times [gathering times]
  (dosync (ref-set times-for times)))