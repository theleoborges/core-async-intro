(ns luminus-bootstrap-cljs-h2-site.routes.home
  (:use compojure.core)
  (:require [luminus-bootstrap-cljs-h2-site.views.layout :as layout]
            [luminus-bootstrap-cljs-h2-site.util :as util]))

(defn home-page []
  (layout/render
    "home.html" {:content (util/md->html "/md/docs.md")}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page)))

