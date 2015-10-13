(defparameter *nodes* '((living-room (you are in the living-room.
                                     a wizard is snoring loudly on the couch.))
                       (garden (you are in the garden.
                                there is a well in front of you.))
                       (attic (you are in the attic.
                               there is a giant welding torch in the corner.)))
  )

(defun describe-location (location nodes)
  (cadr (assoc location nodes)))


(defparameter *edges* '((living-room
                         (garden west door)
                         (attic upstairs ladder))
                        (garden
                         (living-room east door))
                        (attic
                         (living-room downstairs ladder))
                        ))

(defun describe-path (edge)
  `(there is a ,(caddr edge) going ,(cadr edge) from here.))


(defun describe-paths (location edge)
  (apply #'append (mapcar #'describe-path (cdr (assoc location edge)))))

(defparameter *objects* '(whiskey frog bucket chain))

(defparameter *object-location* '(
                                  (whiskey living-room)
                                  (bucket living-room)
                                  (chain garden)
                                  (frog garden)))

(defun objects-at (loc objs obj-locs)
  (labels obj-at-p (obj)
          (eq loc (cadr (assoc objs obj-locs))))
  (remove-if-not #'obj-at-p (objs))
  )


(defun describe-objects (loc objs obj-locs)
  (labels describe-obj (obj)
          `(you see a ,obj on the floor))
  (apply #'append (mapcar #'describe-obj (objects-at loc objs obj-locs))))

(defparameter *location* 'living-room)

(defun look ()
  (append
   (describe-location *location* *nodes*)
   (describe-paths *location* *edges*)
   (describe-objects *location* *objects* *object-location*)))


(defun walk (direction)
  (let ((next (find direction
                    (cdr (assoc *location* *edges*))
                    :key #'cadr)))
    (if next
        (progn (setf *location* (car next)
                     (look)))
        '(you can go this way.))))