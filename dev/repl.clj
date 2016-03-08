(ns repl
  (:require [clojure.pprint :as pprint]
            [clojure.tools.namespace.repl :refer [refresh]]
            [puppetlabs.trapperkeeper.app :as tka]
            [puppetlabs.trapperkeeper.bootstrap :as tkb]
            [puppetlabs.trapperkeeper.config :as tkc]
            [puppetlabs.trapperkeeper.core :as tk]))


;;; Basic system life cycle

(def system nil)

(defn init
  []
  (alter-var-root
   #'system
   (fn [_] (tk/build-app
            (tkb/parse-bootstrap-config! "./dev-resources/bootstrap.cfg")
            (tkc/load-config "./dev-resources/config.edn"))))
  (alter-var-root #'system tka/init)
  (tka/check-for-errors! system))

(defn start
  []
  (alter-var-root #'system (fn [s] (if s (tka/start s))))
  (tka/check-for-errors! system))

(defn stop
  []
  (alter-var-root #'system (fn [s] (when s (tka/stop s)))))

(defn go
  []
  (init)
  (start))

(defn reset
  []
  (stop)
  (refresh :after 'repl/go))


;;; Utilities for interacting with running system

(defn context
  "Get the current TK application context.  Accepts an optional array
  argument, which is treated as a sequence of keys to retrieve a nested
  subset of the map (a la `get-in`)."
  ([]
   (context []))
  ([keys]
   (get-in @(tka/app-context system) keys)))
