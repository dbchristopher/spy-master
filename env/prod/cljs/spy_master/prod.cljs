(ns spy-master.prod
  (:require [spy-master.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
