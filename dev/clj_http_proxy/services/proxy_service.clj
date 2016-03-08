(ns clj-http-proxy.services.proxy-service
  (:require [puppetlabs.trapperkeeper.core :as tk]
            [taoensso.timbre :as log]))


(tk/defservice proxy-service
  [[:ConfigService get-in-config]]
  (start [this context]
    (log/info "Starting proxy service")
    context)
  (stop [this context]
    context))
