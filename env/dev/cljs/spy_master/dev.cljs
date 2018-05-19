(ns ^:figwheel-no-load spy-master.dev
  (:require
    [spy-master.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
