(ns spy-master.components.chat-box
  (:require [reagent.core :as r]))


; establish data connection
;==========================================

; local connection
(def local-connection (js/RTCPeerConnection.))
(def send-channel #((.-createDataChannel local-connection) "sendChannel"))
(defn handle-send-channel-status-change [arg]
  (js/console "Status change"))
(set! (.-onopen send-channel) handle-send-channel-status-change)
(set! (.-onclose send-channel) handle-send-channel-status-change)

; remote connection
(def remote-connection (js/RTCPeerConnection.))
(defn receive-channel-callback []
  (js/console "Channel received"))
(set! (.-ondatachannel remote-connection) receive-channel-callback)

; connect local and remote
(defn add-ice-candidate [connection]
  (def addIceCandidate (.-addIceCandidate connection))
  (fn [e]
    (def candidate (.-candidate e))
    (if candidate
      (addIceCandidate candidate))))

(def add-remote-ice-candidate (add-ice-candidate remote-connection))
(def add-local-ice-candidate (add-ice-candidate local-connection))

(set! (.-onicecandidate local-connection) add-remote-ice-candidate)
(set! (.-onicecandidate remote-connection) add-local-ice-candidate)


; react even handles
; ========================================

(defn connect-peers []
  (js/console.log "CONNECT"))

(defn disconnect-peers []
  (js/console.log "DISCONNECT"))


; Render Chat window
;==========================================

(defn chat-box []
  [:div.chat-window
    [:div.rtc-button-wrapper
      [:button.connect {:id "connectButton" :name "connectButton" :on-click #(connect-peers)} "connect"]
      [:button.disconnect {:id "disconnectButton" :name "disconnectButton" :on-click #(disconnect-peers)} "disconnect"]]
    [:div.messagebox
      [:label {:for "message"} "Enter a message:"
      [:input {:type "text" :name "message" :id "message" :placeholder "message text" :disabled true}]
      [:button.send-message {:id "sendButton" :name "sendButton" :disabled true} "Send"]
      ]]
    [:div.messagebox {:id "receivebox"}
      [:p "Messages received"]]])