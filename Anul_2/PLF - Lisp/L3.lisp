;Definiti o functie care determina numarul nodurilor de pe nivelul k
; dintr-un arbore n-ar reprezentat sub forma (radacina lista_noduri_subarb1
; ... lista_noduri_subarbn) Ex: arborelele este (a (b (c)) (d) (e (f))) si
;k=1 => 3 noduri


;Tre sa mai transform in MAP-uri


(defun numar_noduri(l k niv)
    (cond
    ((and (atom l) (= niv k)) 1)
    ((listp l) (apply #'+ (mapcar #'(lambda(n) (numar_noduri n k (+ 1 niv))) l)))
    (t 0)
    )
)

(defun foo(l k)
    (numar_noduri l k -1)
)