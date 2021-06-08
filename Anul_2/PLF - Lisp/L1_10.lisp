(defun produs(l)
    (cond
    ((null l) 1)
    ((numberp (car l)) (* (car l) (produs (cdr l))))
    (t (produs (cdr l)))
    )
)

(defun my_lenght(l)
    (cond
    ((null l) 0)
    (t (+ 1 (my_lenght (cdr l))))
    )
)

;(defun multime(el l1 l2)
;    (cond
;    ((= (my_lenght l1) 1) nil
;    (t
;        (cond
;        ((= (my_lenght l2) 1) (multime (car l1) (cdr l1) (cddr l1))
;        (t 
;        
;)

(defun nrElemente(l el)
    (cond
    ((null l) 0)
    ((= (car l) el) (+ (nrElemente (cdr l) el) 1))
    (t (nrElemente (cdr l) el))
    )
)

(defun exista(l el)
    (cond
    ((> (nrElemente l el) 1) 0)
    (t 1)
    )
)

(defun perechi(l rez)
    (cond
    ((null l) rez)
    ((exista rez (car l)) (perechi (cdr l) rez))
    (t (perechi (cdr l) (append rez (list (car l) (nrElemente l (car l))))))
    )    
)






