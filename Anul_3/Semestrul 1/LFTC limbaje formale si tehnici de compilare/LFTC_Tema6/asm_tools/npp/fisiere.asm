bits 32
global start
extern exit, scanf, printf, fopen, fclose, fprintf
import exit msvcrt.dll
import scanf msvcrt.dll
import printf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import fprintf msvcrt.dll

segment data use32 class=data

    mesaj db "Numele fisierului:",0
    format db "%s",0
    modAcces db "w",0
    descriptorFile dd -1
    mesajFisier db "Vwe Ardeal!!",0
    fName resb 20

segment code use32 class=code
    start:
        
        push mesaj
        call [printf]
        add esp, 1*4
        
        push fName
        push format
        call [scanf]
        add esp, 2*4
        
        push dword modAcces
        push dword fName
        call [fopen]
        add esp, 2*4
        
        mov [descriptorFile], eax
        
        push dword mesajFisier
        push dword [descriptorFile]
        call [fprintf]
        add esp, 2*4
        
        push dword [descriptorFile]
        call [fclose]
        add esp, 1*4
        
        push    dword 0
        call    [exit]
