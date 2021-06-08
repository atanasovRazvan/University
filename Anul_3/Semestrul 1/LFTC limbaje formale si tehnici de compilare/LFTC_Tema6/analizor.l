%{
#include <stdio.h>
#include <stdlib.h>
#include "analizor.tab.h" // generated via bison -d
#include <string.h>

int yyerror(char * s);
int yyparse();
FILE * output;
char * dataSegment;
char * codeSegment;

%}

%option noyywrap

digit	[0-9]
nndigit	[1-9]
letter	[a-zA-Z_]
number	{digit}|{nndigit}{digit}*

identifier	{letter}({letter}|{digit})*
constant	([+-]?{number}([.]{number})?)|(["][^"]*["])

%%

"int main()"											return HEADER;
"int"													return INT;
"cin"													return CIN;
"cout"													return COUT;
"<<"													return COUT_OPERATOR;
">>"													return CIN_OPERATOR;
";"														return ';'; 
"{"														return '{';
"}" 													return '}'; 
"="														return '=';
"-"														return '-';
\*														return '*';
\+														return '+';
\/														return '/';


{constant}												{strcpy(yylval.string, yytext); return CONSTANT;}
{identifier}											{strcpy(yylval.string, yytext); return IDENTIFIER;}

[ \n\t]													;

.														;
%%

int yyerror(char * s) {
  printf("%s\n", s);
  return 1;
}

void addDataSegment(char segment[1000]) {
  strcat(dataSegment, segment);
}

void addCodeSegment(char segment[1000]) {
  strcat(codeSegment, segment);
}

int main(void) { 
	output = fopen("output.asm","w");

	dataSegment = malloc(3000 * sizeof(char));
	dataSegment[0] = '\0';

	codeSegment = malloc(3000 * sizeof(char));
	codeSegment[0] = '\0';

	addDataSegment("\nsegment data use32 class=data\n");
	addDataSegment("\t_format db \"Print: %d\", 10, 0\n");
	addDataSegment("\t_sformat db \"%d\", 0\n");

	addCodeSegment("segment code use32 class=code\n");
	addCodeSegment("start:\n");
	int val = yyparse(); 

	fprintf(output, "bits 32\n");
	fprintf(output, "global start\n");
	fprintf(output, "extern exit, printf, scanf\n");
	fprintf(output, "import exit msvcrt.dll \n");
	fprintf(output, "import printf msvcrt.dll \n");
	fprintf(output, "import scanf msvcrt.dll \n");

	fprintf(output, "%s\n\n", dataSegment);
	fprintf(output, "%s\n\n", codeSegment);
	return val;
}