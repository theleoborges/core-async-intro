(ns luminus-bootstrap-cljs-h2-site.routes.cljsexample
  (:require [compojure.core :refer :all]
            [noir.response :as response]
            [luminus-bootstrap-cljs-h2-site.views.layout :as layout]))

(def messages
  (atom 
    [{:message "Hello world"
      :user    "Foo"}
     {:message "Ajax is fun"
      :user    "Bar"}]))

(defroutes cljs-routes
  (GET "/cljsexample" [] (layout/render "cljsexample.html"))
  (GET "/core.async-1" [] (layout/render "coreasync-1.html"))     
  (GET "/core.async-2" [] (layout/render "coreasync-2.html"))   
  (GET "/messages" [] (response/edn @messages))
  (POST "/add-message" [message user] 
        (response/edn 
          (swap! messages conj {:message message :user user}))))
