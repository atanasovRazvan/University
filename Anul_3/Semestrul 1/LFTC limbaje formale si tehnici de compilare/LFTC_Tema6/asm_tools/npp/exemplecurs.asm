bits 32
global start
extern exit
import exit msvcrt.dll

segment data use32 class=data
        
    b   dw	-15642, 2ba5h

segment code use32 class=code
    start:
    
    mov bx, [b]
    
    push dword 0
    call [exit]