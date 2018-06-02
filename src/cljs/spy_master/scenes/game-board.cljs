(ns spy-master.scenes.game-board
  (:require [reagent.core :as reagent :refer [atom]]
            [spy-master.components.card :as card]
            [spy-master.dictionary :as dictionary]))


(defn randomized-list
  "Split string by new line and randomize list"
  [dictionary]
  (clojure.core/shuffle (clojure.string/split dictionary #"\n")))

(def code-words
  (take 25 (randomized-list dictionary/words)))

(defn game-board []
  [:div.game-board [:h1 "The overview of cards"]
    [:div [:a {:href "/about"} "go to about page"]]
    [:div.game-grid
    (for [word code-words]
      ^{:key word} [card/word-card word :civilian])]])

