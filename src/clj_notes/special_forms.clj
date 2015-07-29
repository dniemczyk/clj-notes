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
