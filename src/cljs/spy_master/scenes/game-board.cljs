(ns spy-master.scenes.game-board
  (:require [reagent.core :as reagent :refer [atom]]
            [spy-master.components.card :as card]
            [spy-master.dictionary :as dictionary]))

; Shuffle dictionary list
; ===========================================
(defn randomized-list
  "Split string by new line and randomize order"
  [dictionary]
  (shuffle (distinct (clojure.string/split dictionary #"\n"))))

; Define 25 roles:
; 8 Blue, 8 Red, 8 Civilian, 1 Assassin
; ===========================================
(def blue-team-count (+ 8 (rand-int 2)))
(def red-team-count (- 17 blue-team-count))

(def roles
  (shuffle
    (flatten
      (conj
        '(:assassin)
        (take blue-team-count (repeatedly #(keyword :blue)))
        (take red-team-count (repeatedly #(keyword :red)))
        (take 7 (repeatedly #(keyword :civilian)))))))

; Combine roles and words
; ===========================================
(defn unify
  [role word]
  [role word])

(def code-words (map unify roles (randomized-list dictionary/words)))


; Render spymaster "map" visual key
; ===========================================

(def map-class (if (> blue-team-count red-team-count) "blue" "red"))

(defn card-map []
  [:div.map-wrapper
    [:div {:class (str "first-team " map-class)}]
    [:div.card-map
      (for [[role word] code-words]
        ^{:key word} [:div { :class (str "card-map__item " (name role))}])]
    [:div {:class (str "first-team " map-class)}]])


; Render scene
; ===========================================
(defn game-board []
  [:div.game-board [:h1 "The overview of cards"]
    [:div [:a {:href "/about"} "go to about page"]]
    [:div.game-grid
    (for [[role word] code-words]
      ^{:key word} [card/word-card word role])]
    (card-map)])



