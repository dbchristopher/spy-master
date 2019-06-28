(ns spy-master.components.chat-box
  (:require [reagent.core :as r]))

; Chat window
;==========================================


(defn chat-box []
  [:div.chat-window
    [:div.rtc-button-wrapper
      [:button.connect {:id "connectButton" :name "connectButton"} "connect"]
      [:button.disconnect {:id "disconnectButton" :name "disconnectButton"} "disconnect"]]
    [:div.messagebox
      [:label {:for "message"} "Enter a message:"
      [:input {:type "text" :name "message" :id "message" :placeholder "message text" :disabled true}]
      [:button.send-message {:id "sendButton" :name "sendButton" :disabled true} "Send"]
      ]]
    [:div.messagebox {:id "receivebox"}
      [:p "Messages received"]]])