(ns spy-master.components.card
  (:require [reagent.core :as r]))

;; -------------------------
;; State management
(def is-visible (r/atom false))

(defn toggle-state [current-state]
  (if current-state
    false
    true))

;; -------------------------
;; Display
(defn word-card [word]
  [:div.word-card
    { :class (str (if @is-visible "blue" "default"))
      :on-click #(swap! is-visible toggle-state )}
    word
  ])
