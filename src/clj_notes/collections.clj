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
