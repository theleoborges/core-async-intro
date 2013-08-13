(ns luminus-bootstrap-cljs-h2-site.handler
  (:require [compojure.core :refer [defroutes]]
            [luminus-bootstrap-cljs-h2-site.routes.auth :refer [auth-routes]]
            [luminus-bootstrap-cljs-h2-site.routes.home :refer [home-routes]]
            [noir.util.middleware :as middleware]
            [noir.session :as session]
            [compojure.route :as route]
            [luminus-bootstrap-cljs-h2-site.models.schema :as schema]
            [taoensso.timbre :as timbre]
            [com.postspectacular.rotor :as rotor]
            [luminus-bootstrap-cljs-h2-site.routes.cljsexample :refer [cljs-routes]]))

(defroutes
  app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(defn init
  "runs when the application starts and checks if the database
   schema exists, calls schema/create-tables if not."
  []
  (timbre/set-config!
    [:appenders :rotor]
    {:min-level :info,
     :enabled? true,
     :async? false,
     :max-message-per-msecs nil,
     :fn rotor/append})
  (timbre/set-config!
    [:shared-appender-config :rotor]
    {:path "luminus_bootstrap_cljs_h2_site.log",
     :max-size (* 512 1024),
     :backlog 10})
  (if-not (schema/initialized?) (schema/create-tables))
  (timbre/info "luminus-bootstrap-cljs-h2-site started successfully"))

(defn destroy
  "destroy will be called when your application
   shuts down, put any clean up code here"
  []
  (timbre/info "luminus-bootstrap-cljs-h2-site is shutting down..."))

(def app
 (middleware/app-handler
   [cljs-routes auth-routes home-routes app-routes]
   :middleware
   []
   :access-rules
   []))

(def war-handler (middleware/war-handler app))

