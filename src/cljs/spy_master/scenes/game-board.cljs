(ns spy-master.scenes.game-board
  (:require [reagent.core :as reagent :refer [atom]]
            [spy-master.components.card :as card]
            [spy-master.dictionary :as dictionary]))

; Example for accessing dictionary:
; (.log js/console dictionary/words)

(defn game-board []
  [:div.game-board [:h1 "The overview of cards"]
    [:div [:a {:href "/about"} "go to about page"]]
    [:div.game-grid [card/word-card "Test Word!"]]])

