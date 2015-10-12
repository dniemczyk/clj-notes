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

;; many functions call seq on their elements implicitly
(map str "Clojure") ; => ("C" "l" "o" "j" "u" "r" "e")
(set "Programming") ; => #{\a \g \i \m \n \o \P \r}

;; there are many other ways to consume seqs but the most popular are
(first "Damian") ; => \D
(rest "Damian")  ; => (\a \m \i \a \n)
(next "Damian")  ; => (\a \m \i \a \n)
(last "Damian")  ; => \n

;; it would seem that 'rest' and 'next' produce the same result but further
;; investigation with an almost empty seq shows the difference
(rest "D") ; => ()
(rest nil) ; => ()
(next "D") ; => nil
(next nil) ; => nil

;; seqs are not lists, e.g. a count of a seq requires full traversal of
;; the seq, where a list tracks its own length so:
;;= "Elapsed time: 19.073424 msecs"
(let [s (range 1e5)]
  (time (count s))) ; => 100000

;;= "Elapsed time: 0.02385 msecs"
(let [s (apply list (range 1e5))]
  (time (count s))) ; => 100000

;;; Sequence creation
;;;;;;;;;;;;;;;;;;;;;

;; cons - cons creates a sequence by always prepending the given first
;;        value to the sequence given as the second value
(cons 4 (range 2 5)) ; => (4 2 3 4)
(cons :d [:a :b :c]) ; => (:d :a :b :c)

;; list* - a helper for creating seqs with any number of head values and
;;         one sequence as a tail value. The two sniplets are equivalent
(cons 6 (cons 5 (cons 4 (range 1 4)))) ; => (6 5 4 1 2 3)
(list* 6 5 4 (range 1 4)) ; => (6 5 4 1 2 3)

;;; Lazy sequences
;;;;;;;;;;;;;;;;;;

(defn fib-seq
  "Returns a lazy sequence of Fibonacci numbers"
  ([]
   (fib-seq 0 1))
  ([a b]
   (lazy-seq
    (cons b (fib-seq b (+ a b))))))

(take 5 (fib-seq))
;; => (1 1 2 3 5)

;; another interesting example is a function that creates a lazy
;; sequence of random integers
(defn random-ints
  "Returs a lazy sequence of random integers in the range [0..limit]"
  [limit]
  (lazy-seq
   (cons (rand-int limit)
         (random-ints limit))))

;; Remember as this function is not pure the result will differ
(take 10 (random-ints 50)) ; => (41 23 1 36 41 26 20 34 44 28)
