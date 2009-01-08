(ns concourse.test
  (:use [concourse models session])
  (:use [clojure.contrib.test-is]))

(deftest test-people (is (= 1 (count @*people*))))
(deftest test-gatherings (is (= 1 (count @*gatherings*))))
