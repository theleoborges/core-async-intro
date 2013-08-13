(defproject
  core-async-intro
  "0.1.0-SNAPSHOT"
  :dependencies
  [[org.clojure/clojure "1.5.1"]
   [lib-noir "0.6.6"]
   [compojure "1.1.5"]
   [ring-server "0.2.8"]
   [clabango "0.5"]
   [com.taoensso/timbre "2.1.2"]
   [com.postspectacular/rotor "0.1.0"]
   [com.taoensso/tower "1.7.1"]
   [markdown-clj "0.9.28"]
   [com.h2database/h2 "1.3.172"]
   [log4j
    "1.2.17"
    :exclusions
    [javax.mail/mail
     javax.jms/jms
     com.sun.jdmk/jmxtools
     com.sun.jmx/jmxri]]
   [org.clojure/clojurescript "0.0-1806"]
   [domina "1.0.1"]
   [prismatic/dommy "0.1.1"]
   [cljs-ajax "0.1.4"]
   [org.clojure/core.async "0.1.0-SNAPSHOT"]]
  :repositories {"sonatype-oss-public" "https://oss.sonatype.org/content/groups/public/"}
  :cljsbuild
  {:builds
   [{:source-paths ["src-cljs"],
     :compiler
     ;; {:pretty-print false,
     ;;  :output-to "resources/public/js/site.js",
     ;;  :optimizations :advanced}
     {:output-to "resources/public/js/site.js"
      :optimizations :whitespace
      :pretty-print true}}]}
  :ring
  {:handler core-async-intro.handler/war-handler,
   :init core-async-intro.handler/init,
   :destroy core-async-intro.handler/destroy}
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}},
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.1.8"]]}}
  :url
  "http://example.com/FIXME"
  :plugins
  [[lein-ring "0.8.5"] [lein-cljsbuild "0.3.2"]]
  :description
  "FIXME: write description"
  :min-lein-version "2.0.0")