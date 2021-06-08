; 5-Numar impar

; Cerinta: Sa se citeasca de la tastatura mai multe numere naturale pana la citirea lui 0. Sa se scrie intr un fisier
; separat prin spatii numerele pozitive care au numar par de biti 1

bits 32
global start
extern exit, fopen, scanf, fprintf, fclose
import exit msvcrt.dll
import fopen msvcrt.dll
import scanf msvcrt.dll
import fprintf msvcrt.dll
import fclose msvcrt.dll

segment data use32 class=data

    descriptor dd 0
    numar dd 0
    format db "%d",0
    formatfile db "%d ",0
    fName db "rezultat.txt",0
    modAcces db "w",0

segment code use32 class=code
    start:
        
        push dword modAcces
        push dword fName
        call [fopen]
        add esp, 2*4
        mov [descriptor], eax
        mov eax, 0
        
        repeat:
            
            push dword numar
            push dword format
            call [scanf]
            add esp, 4*2
            
            mov eax, [numar]
            cmp eax, 0
            je sfarsit
            
            mov ebx, 0
            repeat2:
                cmp eax, 0
                jz sfarsit2
                sar eax, 1
                jb corect
                jae incorect
            
                corect:
                    inc ebx
                incorect:
                jmp repeat2
            sfarsit2:
            
            test ebx, 1
            jne nuScrie
                
                push dword [numar]
                push dword format
                push dword [descriptor]
                call [fprintf]
                add esp, 4*3
                
            nuScrie:
            
        jmp repeat
        sfarsit:
        
        push dword [descriptor]
        call [fclose]
        add esp, 4
        
        push    dword 0
        call    [exit]
        
        
        
        
        
