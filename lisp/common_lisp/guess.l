
;;; all function call is surrounded by parentheses
(defun start-over()
  (defparameter *small* 1); even define a global variable is also a function
  (defparameter *big* 100)
  (guess-my-num))

(defun guess-my-num ()
  (ash (+ *small* *big*) - 1))

(defun smaller ()
  (setf *big* (1- (guess-my-num)))
  (guess-my-num))

(defun bigger ()
  ;; '1+' is a function accept a number
  (setf *small* (1+ (guess-my-num)))
  (guess-my-num))
