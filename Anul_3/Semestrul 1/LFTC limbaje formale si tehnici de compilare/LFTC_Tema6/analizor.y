%{

#include <stdio.h>
#include <string.h>
#include <ctype.h>

int yylex(void);
int yyerror(const char *s);
FILE * output;

void addDataSegment(char segment[1000]);
void addCodeSegment(char segment[1000]);

%}

%union {
  int number;
  char string[2000];
}
%error-verbose
%token HEADER
%token INT
%token CONSTANT
%token IDENTIFIER
%token CIN
%token COUT
%token CIN_OPERATOR
%token COUT_OPERATOR
%type<string> CONSTANT IDENTIFIER var expresie

%%

program : HEADER '{' lista_instructiuni '}'
		;
		
lista_instructiuni : instructiune_declarare lista_instructiuni
				   | instructiune_declarare
				   ;
				   
instructiune_declarare	: instructiune
						| declarare
						;
				
declarare	: INT IDENTIFIER ';' {
				char code[1000];
				code[0] = 0;
				strcat(code, "\t");
				strcat(code, $2);
				strcat(code, " dd 0\n");
				addDataSegment(code);
			}
			;
				
instructiune	: citire
				| scriere
				| atribuire
				;
				
citire	: CIN CIN_OPERATOR IDENTIFIER ';' {
			char code[1000];
			code[0] = 0;
			strcat(code, "\tpush ");
			strcat(code, "dword ");
			strcat(code, $3);
			strcat(code, "\n");
			addCodeSegment(code);
			addCodeSegment("\tpush dword _sformat\n");
			addCodeSegment("\tcall [scanf]\n");
			addCodeSegment("\tadd esp, 4 * 2\n");
		}
		;
		
scriere	: COUT COUT_OPERATOR var ';' {
			char code[1000];
			code[0] = 0;
			strcat(code, "\n");
			strcat(code, "\tpush ");

			if(isdigit($3[0])) {
				strcat(code, $3);
			} else {
				strcat(code, "dword [");
				strcat(code, $3);
				strcat(code, "]\n");
			}
			strcat(code, "\n");
			addCodeSegment(code);
			addCodeSegment("\tpush dword _format\n");
			addCodeSegment("\tcall [printf]\n");
			addCodeSegment("\tadd esp, 4 * 2\n");
		}
		;
		
atribuire	: IDENTIFIER '=' expresie ';' {
				char code[1000];
				code[0] = 0;
				strcat(code, "\tmov eax, 0\n");
				strcat(code, $3);
				strcat(code, "\tmov dword [");
				strcat(code, $1);
				strcat(code, "], ");
				strcat(code, "eax\n");
				addCodeSegment(code);
			}

expresie	: expresie '+' var {
				char code[1000];
				code[0] = 0;
				strcat(code, "\tadd eax, ");
				if(isdigit($3[0])) {
				  strcat(code, $3);
				} else {
				  strcat(code, "[");
				  strcat(code, $3);
				  strcat(code, "]");
				}
				strcat(code, "\n");

				strcpy($$, $1);
				strcat($$, code);
			}

			| expresie '-' var {
				char code[1000];
				code[0] = 0;
				strcat(code, "\tsub eax, ");
				if(isdigit($3[0])) {
				  strcat(code, $3);
				} else {
				  strcat(code, "[");
				  strcat(code, $3);
				  strcat(code, "]");
				}
				strcat(code, "\n");

				strcpy($$, $1);
				strcat($$, code);
			}
			
			| var {
			
				char code[1000];
				code[0] = 0;
				strcat(code, "\tadd eax, ");
				if(isdigit($1[0])) {
				  strcat(code, $1);
				} else {
				  strcat(code, "[");
				  strcat(code, $1);
				  strcat(code, "]");
				}
				strcat(code, "\n");
				strcpy($$, code);
			
			}
			;
			
var			: IDENTIFIER	{ strcpy($$, $1); }
			| CONSTANT		{ strcpy($$, $1); }
			;

%%