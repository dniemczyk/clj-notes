(ns clj-notes.macros)

;;; Evaluation and reader

;; You can check what the reader normaly is doing with read-string
(read-string "(* 2 5)") ; => (* 2 5)

;; Its also quite interesting to see what happens when you read an
;; anonymous function
(read-string "#(+ %1 %2 10)")
;; => (fn* [p1__3366# p2__3367#] (+ p1__3366# p2__3367# 10))

;; Reder macros in clojure
;; () ; <= a list reader form
;; str ; <= a symbol reader form
;; [1 2] ; <= a vector reader form containing two number reader forms
;; {:sound "hoot"} ; <= a map reader form with a keyword reader form and
                   ; string reader form

;;; Macros

;; A simple expansion of the when macro
(macroexpand '(when true "smile")) ; => (if true (do "smile"))
