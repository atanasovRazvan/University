(defun invers(l)
    (cond
    ((null l) nil)
    (t (append (invers (cdr l)) (list (car l))))
    )
)

(defun test(l tr)
     (cond
     ((and (null l) (= tr 1)) (append l (list 1)))
     ((null l) nil)
     (t (append (list (mod (+ (car l) tr) 10)) (test (cdr l) (/ (+ (car l) tr) 10))))
     )        
)

(defun foo(l)
    (invers (test (invers l) 1))
)