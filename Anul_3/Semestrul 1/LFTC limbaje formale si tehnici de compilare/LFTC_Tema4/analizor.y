%{
#include <stdio.h>
#include <stdlib.h>

%}

%error-verbose
%token VAR
%token CONST
%token INT
%token DOUBLE
%token MAIN
%token IF
%token FOR
%token ELSE
%token CIN
%token COUT
%token AND
%token OR
%token GE
%token LE
%token EQUAL


%%

S 	:	program											
	;

program	:	INT MAIN '(' ')' '{' lista_instr '}'					
		;
	
lista_instr	:	instr_decl lista_instr														
			|	instr_decl											
			;

instr_decl	:	instr											
			|	decl											
			;

instr	:	IF '(' expr ')' '{' lista_instr '}' ELSE '{' lista_instr '}'		
		|	IF '(' expr ')' '{' lista_instr '}'						
		|	FOR '(' instr_expr ';' instr_expr ';' instr_expr ')' '{' lista_instr '}'			
		|	CIN	VAR ';'									
		|	COUT X ';'									
		|	VAR '=' expr ';'								
		;
		
instr_expr	:	expr											
			|	CIN VAR											
			|	COUT X											
			| 	VAR '=' expr
			;

decl	:	INT VAR ';'									
		|	DOUBLE VAR ';'								
		;

expr	:	expr AND expr										
		|	expr OR expr										
		|	arithmetic_expr											
		|	relational_expr											
		;												

relational_expr	:	arithmetic_expr GE arithmetic_expr									
				|	arithmetic_expr LE arithmetic_expr									
				|	arithmetic_expr '>' arithmetic_expr										
				|	arithmetic_expr '<' arithmetic_expr										
				|	arithmetic_expr EQUAL arithmetic_expr									
				;
	
arithmetic_expr	:	arithmetic_expr '+' arithmetic_expr										
				|	arithmetic_expr '-' arithmetic_expr										
				|	arithmetic_expr '*' arithmetic_expr										
				|	arithmetic_expr '/' arithmetic_expr										
				|	arithmetic_expr '%' arithmetic_expr										
				|	X											
				;

X	:	CONST										
	| 	VAR											
	;

%%

yyerror()
{
    printf("syntax error\n");
}

int main()
{
    if(0==yyparse()) printf("Result yyparse: OK\n");
}