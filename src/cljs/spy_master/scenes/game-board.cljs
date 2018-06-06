(ns spy-master.scenes.game-board
  (:require [reagent.core :as reagent :refer [atom]]
            [spy-master.components.card :as card]
            [spy-master.dictionary :as dictionary]))

; Shuffle dictionary list
; ===========================================
(defn randomized-list
  "Split string by new line and randomize order"
  [dictionary]
  (shuffle (clojure.string/split dictionary #"\n")))

; Define 25 roles:
; 8 Blue, 8 Red, 8 Civilian, 1 Assassin
; ===========================================
(def roles
  (shuffle
    (flatten
      (conj
        '(:assassin)
        (take 8 (repeatedly #(keyword :blue)))
        (take 8 (repeatedly #(keyword :red)))
        (take 8 (repeatedly #(keyword :civilian)))))))

; Combine roles and words
; ===========================================
(defn unify
  [role word]
  [role word])

(def code-words (map unify roles (randomized-list dictionary/words)))

; Render word list
; ===========================================
(defn game-board []
  [:div.game-board [:h1 "The overview of cards"]
    [:div [:a {:href "/about"} "go to about page"]]
    [:div.game-grid
    (for [[role word] code-words]
      ^{:key word} [card/word-card word role])]])

