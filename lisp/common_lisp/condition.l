(if '()
    'I-am-true
    'I-am-false)

(if '(1)
    'I-am-true
    'I-am-false)

;;;Only one of the expressions after the if is actually evaluated.
;;;We can only do one thing in an if statement.
(if (= (+ 1 2) 4)
    'yup
    'nope)
(if (oddp 5)
    'odd-number
    (/ 1 0))

(when (oddp 5)
  (setf *number-is-odd* t)
  'odd-number)

(unless (oddp 4)
  (setf *number-is-odd* nil)
  'even-number)

(defun test(te)
  (cond ((eq te 1)
         (setf a 1) 't-is-1)
        ((eq te 2)
         (setf a 2) 't-is-2)
        (t
         't-is-)))

;;Because the case command uses eq for comparisons, it is usually used only for branching on symbol values.
;;It cannot be used to branch on string values, among other things
(defun test(te)
  (case te
    ((1)
     (setf a 1) 't-is-1)
    ((2)
     (setf a 2) 't-is-2)
    (otherwise
     't-is-)))
