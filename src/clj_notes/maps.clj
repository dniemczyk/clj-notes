(ns clj-notes.maps)

;; commas are treated as whitespace, not only for maps
;; but it is interesting that the response will be comma
;; seperated
(def a-map {:a 1 :b 2 :c 3}) ; => {:a 1, :b 2, :c 3}
