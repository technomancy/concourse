(ns concourse.test
  (:use [concourse gatherings people session times])
  (:use [clojure.contrib.test-is]))

(deftest test-people (is (= 1 (count @*people*))))

;;; Gatherings

(dosync
 (ref-set *gatherings* [(struct-map gathering
                          :name "My Gathering"
                          :description "A wonderful time for getting together."
                          :organizer "technomancy@gmail.com"
                          :attendees (ref {"technomancy@gmail.com" (ref {(make-date 2009 12 10) #{10 11 12}})})
                          :length 1
                          :earliest-day (make-date 2009 12 10)
                          :earliest-hour 10
                          :latest-day (make-date 2009 12 18)
                          :latest-hour 18)]))

(def a-gathering (first @*gatherings*))

(deftest test-gatherings (is (= 1 (count @*gatherings*))))

(defn make-test-date [day] (make-date 2009 1 day))

(deftest test-weeks-for
  (is (= [[]] (weeks-for (make-test-date 2) (make-test-date 2) [[]])))
  ;; (is (= [[(make-test-date 2) (make-test-date 3)]]
  ;;          (weeks-for (struct-map gathering :earliest-day (make-test-date 2)
  ;;                                 :latest-day (make-test-date 3)))))
  ;; (is (= [[(make-test-date 2) (make-test-date 3)]
  ;;         [(map make-test-date (range 4 11))]
  ;;         [(make-test-date 11) (make-test-date 12)]] (weeks-for a-gathering)))
  )