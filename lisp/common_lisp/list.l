(cons 'pork (cons 'beef (cons 'chicken ())))
(car '(pork beef chicken))
(cdr '(pork beef chicken))

(car (cdr '(pork beef chicken)))
(cadr '(pork beef chicken))

;;; list constructor
(list 'pork 'beef 'chicken)
'(pork beef chicken)