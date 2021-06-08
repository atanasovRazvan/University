(defun foo(l)
    (cond
    ((and (numberp l) (= (MOD l 3) 0)) nil)
    ((atom l) (list l))
    ((listp l) (list (apply #'nconc (mapcar 'foo l))))
    )
)