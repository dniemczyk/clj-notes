;;;; The Sequence abstraction
(ns clj-notes.sequences)

;;; Seqs define define a way to obtain and traverse sequential
;;; views of some sort of values. The key functions working on
;;; seqs are:
;;;
;;; seq - produces a sequence over its arguments
;;; first, last, next - provide ways to consume seqs
;;; lazy-seq - produces a lazy sequence

;; seq usage examples:
(seq "Clojure")   ; => (\C \l \o \j \u \r \e)
(seq {:a 5 :b 6}) ; => ([:a 5] [:b 6])
(seq (java.util.ArrayList. (range 5)))   ; => (0 1 2 3 4)
(seq (into-array ["Awesome" "Clojure"])) ; => ("Awesome" "Clojure")
(seq [])  ; => nil
(seq nil) ; => nil

;;; Lazy sequences

(defn fib-seq
  "Returns a lazy sequence of Fibonacci numbers"
  ([]
   (fib-seq 0 1))
  ([a b]
   (lazy-seq
    (cons b (fib-seq b (+ a b))))))

(take 5 (fib-seq))
;; => (1 1 2 3 5)
