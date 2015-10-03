;;;; The Collection Abstraction
(ns clj-notes.collections)

;;; A collection is a value that you can use with one of
;;; the following functions:
;;;
;;; conj  - add an item to the collection
;;; seq   - get a sequence of a collection
;;; count - get the number of items in a collection
;;; empty - get a empty instance of the same type as the provided collection
;;; =     - value equality of a collection to another (or many) collections

;; conj - allways adds a given value to the colection 'efficiently'
;;        for example for a list it prepends the value, because doing
;;        anything else would require traversing the list - a costly action
;;        if working with lage datasets
(conj '(1 2 3) 4) ; => (4 1 2 3)

;; into - is build upon the conj and as such it will also add data
;;        in the most efficient way
(into '(1 2 3) [:a :b :c]) ; => (:c :b :a 1 2 3)
(into [1 2 3] [:a :b :c])  ; => [1 2 3 :a :b :c]

;; empty - allows to work with a collection of a specific type (without
;;         having to define it beforehand) as the following function shows
(defn swap-pairs [sequential]
  (into (empty sequential)
        (interleave
         (take-nth 2 (drop 1 sequential))
         (take-nth 2 sequential))))

(swap-pairs (apply list (range 10)))   ; => (8 9 6 7 4 5 2 3 0 1)
(swap-pairs (apply vector (range 10))) ; => [1 0 3 2 5 4 7 6 9 8]
