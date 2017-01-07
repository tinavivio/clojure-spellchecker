(ns clojure-spellchecker.core
  (:require [clojure.string :as str])
  (:import (org.apache.commons.lang3 StringUtils)))

(def words (set (map str/trim (str/split-lines (slurp "resources/wordsEn.txt")))))

(defn correct? [w]
  (contains? words w))

(defn distance [w1 w2] (StringUtils/getLevenshteinDistance w1 w2))

(defn min-distance [w]
  (apply min-key (partial distance w) words))

(defn -main
  [& args]
  (let [word (first args)]
    (if (correct? word) (println "correct")
                        (println "Did you mean" (str (min-distance word) "?")))))