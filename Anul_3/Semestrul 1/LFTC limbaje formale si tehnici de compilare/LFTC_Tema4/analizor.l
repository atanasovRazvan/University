%{
#include <stdio.h>
#include <stdlib.h>
#include "analizor.tab.h" // generated via bison -d
%}

%option noyywrap

digit	[0-9]
nndigit	[1-9]
letter	[a-zA-Z_]
number	{digit}|{nndigit}{digit}*

%%

int														{return INT;}
double													{return DOUBLE;}
main													{return MAIN;}
if														{return IF;}
else													{return ELSE;}
for														{return FOR;}
cin>>													{return CIN;}
cout<<													{return COUT;}

&&														{return AND;}
\|\|													{return OR;}
>=														{return GE;}
\<=														{return LE;}
==														{return EQUAL;}

","														return ',';
";"														return ';'; 
"("														return '(';
")"														return ')';
"{"														return '{';
"}" 													return '}'; 
"="														return '=';
"-"														return '-';
\*														return '*';
\+														return '+';
\/														return '/';
"%"														return '%';
"<"     												return '<';
">"     												return '>';


([+-]?{number}([.]{number})?)|(["][^"]*["])				{return CONST;}
{letter}({letter}|{digit})*								{return VAR;}
[ \n\t]													;

.														;
%%