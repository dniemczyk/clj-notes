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

(let [{a :a b :b} m]
  (+ a b)) ; => 11

;; it not needed that the keys bound in the maps be keywords
(let [{f "foo"} m]
  (+ f 12)) ; => 100

;; indices into vectors, strings and arrays can be used in
;; the map destructoring form
(let [{x 3 y 8} [12 0 0 -18 44 6 0 0 1]]
  (+ x y)) ; => -17

;; map destructoring can be also composed
(let [{{e :e} :d} m]
  (+ e 12)) ; => 22

;; you can compose map an sequential deconstructors
(let [{[x _ y] :c} m]
  (+ x y)) ; => 16
;; an example how this is done the other way around
(def map-in-vector ["James" {:birthday (java.util.Date. 73 6 1)}])
(let [[name {bd :birthday}] map-in-vector]
  (str name " was born on " bd))
; => James was born on Sun Jul 01 00:00:00 CET 1973

;;; fn -- Creating functions

((fn [x] (+ 8 x)) 12) ; => 20

;; functions can also have multiple arguments
((fn [x y z] (* x y z)) 2 3 -4) ; => -24

;; functions can also have multiple arities here i define a var in the
;; longer form later this can be combined with the defn macro
(def strange-adder (fn adder-self-ref
                     ([x] (adder-self-ref x 1))
                     ([x y] (+ x y))))

(strange-adder 11)    ; => 12
(strange-adder 12 34) ; => 46

;; Destructoring function arguments

;; Variadic functions - functions with rest arguments or (other name) varargs
(defn concat-rest
  [_ & rest]
  (apply str (butlast rest)))

(concat-rest 0 1 2 3 4) ; => "123"

;; creates a uuid or the passed in name in args
(defn make-user
  [& [user-id]]
  {:user-id (or user-id
                (str (java.util.UUID/randomUUID)))})
