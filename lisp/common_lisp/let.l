(setq x 7)
;;7                     ;again a warning in CMUCL:
                                        ;x is automaticly declared special
(let ((x 1)
      (y (+ x 1)))
  y
  )
;;8

;;The let* special form is just like let except that it allows values to reference variables defined earlier in the let*. For example,

(setq x 7)
;;7
(let* ((x 1)
       (y (+ x 1)))
  y
  )
;;2


