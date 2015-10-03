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
