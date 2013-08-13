(ns luminus-bootstrap-cljs-h2-site.main
  (:require [ajax.core :refer [GET POST]] 
            [domina :refer [value by-id destroy-children! append! set-html!]]
            [domina.events :refer [listen!]]
            [dommy.template :as template]
            [dommy.core :as dommy]
            [cljs.core.async :as async
             :refer [<! >! chan close! sliding-buffer put! alts! timeout]])
  (:require-macros [cljs.core.async.macros :as m :refer [go alt!]]))


;; Example 1

(defn fake-search [kind]
  (fn [query]
    (let [c (chan)]
      (go
       (<! (timeout (rand-int 100)))
       (>! c (str "<span>" kind " result for " query "</span>")))
      c)))

(def web   (fake-search "Web"))
(def image (fake-search "Image"))
(def video (fake-search "Video"))

(defn google [query]
  (.log js/console "searching.")
  (let [c (chan)
        t (timeout 75)]
    (go (>! c (<! (web query))))
    (go (>! c (<! (image query))))
    (go (>! c (<! (video query))))
    (go (loop [i 0 acc []]
          (if (> i 2)
            acc
            (recur (inc i) (conj acc (alt! [c t]
                                            ([v] v)))))))))

(defn event-chan [el type]
  (let [c (chan)]
    (.addEventListener el type #(put! c %))
    c))

(defn ^:export search [query]
  (let [click-channel (event-chan (by-id "search") "click")]
    (go (loop []
          (<! click-channel)
          (dommy/set-html! (by-id "results") (<! (google query)))
          (recur)))))

;; Example 2

(def c (chan))

(defn render [q]
  (apply str
    (for [p (reverse q)]
      (str "<div class='proc-" p "'>Process " p "</div>"))))

(go (while true (<! (async/timeout 250)) (>! c 1)))
(go (while true (<! (async/timeout 1000)) (>! c 2)))
(go (while true (<! (async/timeout 1500)) (>! c 3)))

(defn peekn
  "Returns vector of (up to) n items from the end of vector v"
  [v n]
  (if (> (count v) n)
    (subvec v (- (count v) n))
    v))

(defn ex-2 []
  (let [out (by-id "messages")]
    (go (loop [q []]
          (dommy/set-html! out (render q))
          (recur (-> (conj q (<! c)) (peekn 10)))))))

(defn ^:export init []
  (ex-2))