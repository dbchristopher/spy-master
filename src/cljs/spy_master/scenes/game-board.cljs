(ns spy-master.scenes.game-board
  (:require [reagent.core :as reagent :refer [atom]]))

(defn game-board []
  [:div [:h1 "The overview of cards"]
    [:div [:a {:href "/about"} "go to about page"]]])

