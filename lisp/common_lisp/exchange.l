(defun exchange (a b)
  (let ((c 0))
    (setf c a)
    (setf a b)
    (setf b c)))


;; using parallel
(setq a 1)
(setq b 2)
(psetq a b  b a)
