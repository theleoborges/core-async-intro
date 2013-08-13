(ns core-async-intro.routes.cljsexample
  (:require [compojure.core :refer :all]
            [noir.response :as response]
            [core-async-intro.views.layout :as layout]))

(defroutes cljs-routes
  (GET "/core.async-1" [] (layout/render "coreasync-1.html"))     
  (GET "/core.async-2" [] (layout/render "coreasync-2.html"))   )
