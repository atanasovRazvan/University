;mianasov

;My lenght
(defun my-lenght(l)
    (cond
    ((null l) 0)
    ((numberp (car l)) (+ 1 (my-lenght(cdr l))))
    (t (my-lenght(cdr l)))
    )
)

;Problema a
(defun diferenta (l1 l2)
	(cond
		((null l2) nil)
		((member (car l2) l1) (diferenta l1 (cdr l2)))
		(T (cons (car l2) (diferenta l1 (cdr l2))))
	)
)

;Problema b
(defun rev (l)
    (cond ((null l) nil)
        ((listp (car l)) (append (rev (cdr l)) (list (rev (car l)))))
        (t (append (rev (cdr l)) (list (car l))))
	)
)

;Problema c
(defun impl (l)
    (cond
    ((null l) nil)
    ((and (not(listp(car l))) (= (% (my-lenght l) 2) 1)) (cons (car l) (impl (cdr l))))
    ((and (listp(car l)) (= (% (my-lenght l) 2) 1)) (cons (impl (car l)) (impl (cdr l))))
    (t (impl (cdr l)))
    )
)

;Problema d
(defun sum (l)
	(cond
	((null l) 0)
	((numberp (car l)) (+ (car l) (sum (cdr l))))
	(T (sum (cdr l)))
	)
)
