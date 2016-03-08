(ns workbench
  (:require [clojure.pprint :refer [pprint]]
            [eftest.runner :as eft]
            [repl]
            [schema.core :as s]
            [taoensso.timbre :as log])
  (:import [java.lang.management ManagementFactory]))


;;; Enable schema validation for all functions while in dev

(s/set-fn-validation! true)


;;;; Vars


;;;; Functions

(defn show-jvm-opts
  "Returns an array of option strings passed to the current
  JVM running the app."
  []
  (let [runtime-mx-bean (ManagementFactory/getRuntimeMXBean)
        jvm-args        (.getInputArguments runtime-mx-bean)]
    jvm-args))

(defn rerun-tests
  ([]
   (rerun-tests "test"))
  ([path]
   (repl/reset)
   (eft/run-tests (eft/find-tests path)
                  {:report eftest.report.pretty/report})))

(defn get-config
  "Returns the configuration data map from the current REPL app context."
  []
  (get-in (repl/context) [:ConfigService]))
