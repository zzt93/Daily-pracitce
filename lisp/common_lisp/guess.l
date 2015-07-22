(defparameter *small* 1)
(defparameter *big* 100)

(defun guess-my-num ()
  (ash (+ *small* *big*) - 1))

(defun smaller ()
  (setf *big* (1- (guess-my-num)))
  (guess-my-num))

(defun bigger ()
  (setf *small* (1+ (guess-my-num)))
  (guess-my-num))
