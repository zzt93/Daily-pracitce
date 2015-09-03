
(let ((a 4)
      (b 5)
      (c 6))
  (+ a b c)
  )

(flet ((t1 (n)
         (+ n 10))
       (t2 (n)
         (- n 10)))
  (t1 5)
  (t2 6)
  )

;;;To make function names available in defined functions,
;;;we can use the labels command. It’s identical in its basic structure to the flet command.
(labels ((a (n)
           (+ n 5))
         (b (n)
           (+ (a n) 5)))
  (b 10))
