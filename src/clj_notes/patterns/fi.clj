;;;; Replacing Functional Interface
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(ns clj-notes.patterns.fi)

(def people [{:first-name "Michael" :last-name "Smith"}
             {:first-name "Kate" :last-name "Blanchet"}
             {:first-name "Ryu" :last-name "Fukutoshi"}])

;; Returns the list of people sorted by first name
(sort (fn [p1 p2] (compare (p1 :first-name) (p2 :first-name))) people)
; => ({:first-name "Kate", :last-name "Blanchet"}
;     {:first-name "Michael", :last-name "Smith"}
;     {:first-name "Ryu", :last-name "Fukutoshi"})

;; NOTICE: collections are functions - a call of the p1 map on the
;;         first-name keyword key returns the value for that key
