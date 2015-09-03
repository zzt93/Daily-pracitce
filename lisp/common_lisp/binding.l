;;;A “Binding” is a relationship of correspondence between a name and a (memory) location.
;;;CommonLisp has both dynamic binding and lexical binding. You get lexical binding by default, but you can ask for dynamic binding by ‘defvar’ing your name or ‘declare’ing it “special”.

(defvar x 12)
;; ==> x

(defun bar ()
  (format t "The value of x is ~a" x))
;; ==> bar

(defun foo (x)
  (bar))
;; ==> foo

(foo 42)
The value of x is 42
;; ==> NIL

x
;; ==> 12

(let ((a 1))
  (let ((a 2))
    (let ((a 3))
      (print a))
    (print a))
  (print a))

;;;Function calls create bindings for their formal arguments when they are called:
;;;A binding made by a function call lasts until the call returns.
(defun foo (a)
  (let ((a 2)) (print a))
  (print a))
(foo 1)


;;;dynamic
;;All variable names and their values live in one global table.
;;;lexical
;;Every binding scope (function, let syntax, …) creates a new table of variable names and values,
;;organised in a hierarchy called “the environment”.

(let ((a 1))                            ; binding (1)
  (let ((f (lambda () (print a))))
    (let ((a 2))                        ; binding (2)
      (funcall f))))
;; ==> 2
;;; A name that is lexically bound is looked up only in bindings in the lexical environment of the name
;;; – that is, in bindings that enclose the name in the source code. So if “a” is lexically bound,
;;; the code above prints “1”, because only binding (1) is in the lexical environment. When there are multiple bindings in the lexical environment, the innermost one is used.

;;; A name that is dynamically bound is looked up only in bindings in the dynamic environment of the name – that is, in all bindings which have been created since the program began and which have not yet been destroyed. When there are multiple bindings in the dynamic environment, the most recently created one is used. So if “a” is dynamically bound, the code above prints “2” because both binding (1) and binding (2) have been created by the time “a” is evaluated, but binding (2) was created more recently.

;;;(In a multi-threaded Lisp we would have to be a bit more careful about dynamic binding to make sure that one thread doesn’t see bindings created on another thread. But EmacsLisp is single-threaded so that’s not a worry.