;Se citesc trei numere din fisier si un numar de la tastatura. Numerele sunt de o cifra despartite prin spatiu.

bits 32
global start

extern exit, scanf, fread, fopen, fclose, printf, logic
import exit msvcrt.dll
import scanf msvcrt.dll
import fread msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import printf msvcrt.dll

segment data use32 public data

    a   dd  0
    b   dd  0
    c   dd  0
    nrCitite    resb    8
    fName   db    "triunghi.txt",0
    modAcces    db     "r+",0
    descFisier  dd  0
    format db "%s%s%s",0

segment code use32 public code
    start:
        
        push modAcces
        push fName
        call [fopen]
        add esp, 4*2
        mov [descFisier], eax
        
        push dword [descFisier]
        push dword 8
        push dword 1
        push dword nrCitite
        call [fread]
        add esp, 4*4
        
        CLD
        mov esi, nrCitite
        lodsb
        mov ah, 30h
        sub al, ah
        mov [a], al
        lodsb
        lodsb
        sub al, ah
        mov [b], al
        lodsb
        lodsb
        sub al, ah
        mov [c], al
        
        push dword [a]
        push dword [b]
        push dword [c]
        call [logic]
        
        push    dword 0
        call    [exit]
