(ns clj-notes.maps)

;; commas are treated as whitespace, not only for maps
;; but it is interesting that the response will be comma
;; seperated
(def a-map {:a 1 :b 2 :c 3}) ; => #'clj-notes.maps/a-map

(conj a-map [:d 4]) ; => {:a 1, :b 2, :c 3, :d 4}
