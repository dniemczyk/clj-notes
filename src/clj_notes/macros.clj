(ns clj-notes.macros)

;;; Macros

;; A simple expansion of the when macro
(macroexpand '(when true "smile"))
;; => (if true (do "smile"))
