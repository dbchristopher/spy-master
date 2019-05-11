(ns spy-master.components.card
  (:require [reagent.core :as r]))

;; -------------------------
;; State management

(defn toggle-state [current-state]
  (if current-state
    false
    true))

;; -------------------------
;; Color mapping

(def color-map {
  :red "red",
  :blue "blue",
  :assassin "black",
  :civilian "brown"})

;; -------------------------
;; Display

(defn word-card [word team]
  (let [is-visible (r/atom false)]
  (fn []
  [:div
    { :class (str (if @is-visible "visible" "hidden") " card")
      :on-click #(swap! is-visible toggle-state)}
    [:div.card-inner
      [:div.card-front word]
      [:div.card-back {:class (team color-map)}
        [:h1 (str team)]
      ]]])))