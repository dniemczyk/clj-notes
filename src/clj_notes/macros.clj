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

;; A qoute reder macro expands to a quote special form
(read-string "'(1 2 3)") ; => (quote (1 2 3))

;;; Macros

;; A simple expansion of the when macro
(macroexpand '(when true "smile")) ; => (if true (do "smile"))

;; Simple macro definition
;;   - this macro ignores the last operand of the function call
(defmacro ignore-last-operand
  [function-call]
  (butlast function-call))

(ignore-last-operand (+ 1 3 5)) ; => 4

;; You often have to define a macro with syntax quoting and unquoting

;; Syntax quoting will always include the symbol's full namespace
;; normal qouting just has the symbol
`+ ; => clojure.core/+

(defn criticize-code
  [criticism code]
  `(println ~criticism (quote ~code)))

(defmacro code-critic
  [{:keys [good bad]}]
  `(do ~@(map #(apply criticize-code %)
             [["bad code:" bad]
              ["good code:" good]])))

;; Without unquote splicing
`(+ ~(list 1 2 3))
; => (clojure.core/+ (1 2 3))

;; With unquote splicing
`(+ ~@(list 1 2 3))
; => (clojure.core/+ 1 2 3)
