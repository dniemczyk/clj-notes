(ns clj-notes.sequences)

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
