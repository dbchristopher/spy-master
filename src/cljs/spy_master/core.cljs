(ns spy-master.core
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]
              [spy-master.scenes.game-board :as game]))

;; -------------------------
;; Views

(defn sub-component [text]
  [:div (str "hello " text)])

(defn about-page []
  [:div [:h2 "About spy-master"]
   [:div [:a {:href "/"} "go to the home page"]
   [:div (sub-component "daniel")]]])

;; -------------------------
;; Routes

(defonce page (atom #'game/game-board))

(defn current-page []
  [:div [@page]])

(secretary/defroute "/" []
  (reset! page #'game/game-board))

(secretary/defroute "/about" []
  (reset! page #'about-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
