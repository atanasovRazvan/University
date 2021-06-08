bits 32
global start
extern exit, printf, scanf
import exit msvcrt.dll 
import printf msvcrt.dll 
import scanf msvcrt.dll 

segment data use32 class=data
	_format db "Print: %d", 10, 0
	_sformat db "%d", 0
	tigari dd 0
	bani dd 0
	baniRamasi dd 0


segment code use32 class=code
start:
	push dword bani
	push dword _sformat
	call [scanf]
	add esp, 4 * 2
	push dword tigari
	push dword _sformat
	call [scanf]
	add esp, 4 * 2
	mov eax, 0
	add eax, [bani]
	sub eax, [tigari]
	mov dword [baniRamasi], eax

	push dword [baniRamasi]

	push dword _format
	call [printf]
	add esp, 4 * 2


