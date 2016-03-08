(defproject clj-http-proxy "0.1.0-SNAPSHOT"
  :description "An HTTP proxy server based on Aleph"
  :url "http://example.com/FIXME"
  :license {:name "The MIT Licence"
            :url  "https://opensource.org/licenses/MIT"}

  :min-lein-version "2.5.0"

  :dependencies [[org.clojure/clojure "1.7.0"]

                 [aleph "0.4.1-beta4"]
                 [org.slf4j/log4j-over-slf4j "1.7.14"]
                 [org.slf4j/jcl-over-slf4j "1.7.14"]
                 [org.slf4j/jul-to-slf4j "1.7.14"]
                 [prismatic/schema "1.0.5"]
                 [com.fzakaria/slf4j-timbre "0.3.0"]
                 [com.taoensso/timbre "4.3.1"]]

  :jvm-opts ["-Duser.timezone=UTC"]

  :profiles {:dev {:source-paths ["dev"]

                   :repl-options {:init-ns workbench
                                  :init    (repl/init)}

                   :dependencies [[eftest "0.1.0"]
                                  [puppetlabs/trapperkeeper "1.3.0"]
                                  [puppetlabs/trapperkeeper "1.3.0" :classifier "test" :scope "test"]]

                   :plugins      [[codox "0.9.4"]
                                  [jonase/eastwood "0.2.3"]
                                  [lein-ancient "0.6.7"]]}}

  :aliases {"start-dev-server" ["trampoline" "with-profile" "dev" "run" "--config" "dev-resources/config.edn"]
            "lint"             ["eastwood" "{:exclude-namespaces [:test-paths]}"]})
