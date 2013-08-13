(ns core-async-intro.routes.auth
  (:use compojure.core)
  (:require [core-async-intro.views.layout :as layout]
            [noir.session :as session]
            [noir.response :as resp]
            [noir.validation :as vali]
            [noir.util.crypt :as crypt]))