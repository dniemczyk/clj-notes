(ns clojure-notes.special-forms)

;;; quote - Suppressing evaluation

(quote x) ; => x
(symbol? (quote x)) ; => true

;; the reader syntax for quote is '
(read-string "'x") ; => (quote x)

;;; do - code blocks

;; do evaluates all expressions provided to it and yields the value
;; of the last one
(do
  (println "Hi")
  (apply * [4 5 6])) ; Hi => 120

;; many functions like fn, let, loop and try wrap their function bodies
;; in a implicit do expression
(let [a 4
      b 12]
  (println (format "You have as always a %s and a %s" a b))
  (+ a b))
;; evaluates to:
(let [a 4
      b 12]
  (do
    (println (format "You have as always a %s and a %s" a b))
    (+ a b)))

;;; def - defining variables

(def p-var "foo") ; it is posible to redefine those later

;;; let - local bindings

(defn hypot
  [x y]
  ;; name references are lexicaly scoped to the let expression
  (let [x2 (* x x)
        y2 (* y y)]
    (Math/sqrt (+ x2 y2))))

;;; Destructuring (let2)

(def v [42 "foo" 92.2 [5 12]])

(first v)  ; => 42
(second v) ; => foo
(last v)   ; => [5 12]
(nth v 2)  ; => 92.2
(v 2)      ; => 92.2
(.get v 2) ; => 92.2

;; sequential destructuring - works with any collection
(let [[x y z] v]
  (+ x z)) ; => 134.2

(let [[x _ _ [y z]] v]
  (+ x z)) ; => 54

(let [[x & more] v]
  more) ; => ("foo" 92.2 [5 12])

;; retaining deconstructed value
(let [[x _ z :as original-vector] v]
  (conj  original-vector (+ x z))) ; => [42 "foo" 92.2 [5 12] 134.2]

;; map destructuring - hash-maps, array-maps and records and any collection
;; that utilizes java.util.Map

;; start with a basic map
(def m {:a 5
        :b 6
        :c [7 8 9]
        :d {:e 10 :f 11}
        "foo" 88
        42 false})
