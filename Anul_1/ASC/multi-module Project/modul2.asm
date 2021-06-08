;Sa se verifice daca numarul citit de la tastatura este semiperimetrul triunghiului.
bits 32
global logic
extern exit, printf, scanf
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll

segment data use32 public data

    mesajA   db  "Da",0
    mesajF  db  "Nu",0
    formatX  db  "%d",0
    lat1    db     3
    lat2    db     4
    lat3    db     5
    sperimetru resb 1
    

segment code use32 public code
    logic:
        
        mov al, [esp+4]
        mov [lat3], al
        mov al, [esp+8]
        mov [lat2], al
        mov al, [esp+12]
        mov [lat1], al
        
        
        push sperimetru
        push formatX
        call [scanf]
        add esp, 4*2
        
        mov al, [lat1]
        add al, [lat2]
        add al, [lat3]
        cbw
        mov bl, 2
        div bl
        
        mov bl, [sperimetru]
        cmp al, bl
        jne sfarsit
            
            push mesajA
            call [printf]
            add esp, 4
            jmp sfarsit2
            
        sfarsit:
        
        push mesajF
        call [printf]
        add esp, 4
        sfarsit2:
        
        ;ret 12
        push dword 0
        call [exit]
        
        