;2. Sa se tipareasca lista nodurilor de pe nivelul k dintr-un arbore de tipul (1).

;(A 2 B 0 C 2 D 0 E 0)


(defun leftFake(l nv nm)
    (cond
    ((null l) nil)
    ((> nv nm) nil)
    (t (cons (car l) (cons (cadr l) (leftFake (cddr l) (+ nv 1) (+ nm (cadr l))))))
    )
)

(defun left(l)
    (leftFake (cddr l) 0 0)
)

(defun rightFake(l nv nm)
    (cond
    ((null l) nil)
    ((> nv nm) l)
    (t (rightFake (cddr l) (+ nv 1) (+ nm (cadr l))))
    )
)

(defun right(l)
    (rightFake (cddr l) 0 0)
)

(defun level(l k niv)
    (cond
    ((null l) nil)
    ((= k niv) (list (car l)))
    (t (append (level (left l) k (+ niv 1)) (level (right l) k (+ niv 1))))
    )
)

(defun levelWr(l k)
    (level l k 0)
)
    
