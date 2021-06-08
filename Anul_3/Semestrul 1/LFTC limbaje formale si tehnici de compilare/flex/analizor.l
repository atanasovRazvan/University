%option noyywrap

digit	[0-9]
nndigit	[1-9]
letter	[a-zA-Z_]
number	{digit}|{nndigit}{digit}*
letterAndDigit	{digit}|{letter}

%%

int														{printf("2 ");}

double													{printf("3 ");}

cin>>													{printf("4 ");}

cout<<													{printf("5 ");}

if 														{printf("6 ");}

else 													{printf("7 ");}

for														{printf("8 ");}

main													{printf("9 ");}

and														{printf("10 ");}

"=="													{printf("12 ");}

">="													{printf("16 ");}

"<="													{printf("15 ");}

"="														{printf("11 ");}

"<"														{printf("13 ");}

">"														{printf("14 ");}

"*"														{printf("17 ");}

"/"														{printf("18 ");}

"%"														{printf("19 ");}

"+"														{printf("20 ");}

"-"														{printf("21 ");}

"("														{printf("22 ");}

")"														{printf("23 ");}

"{"														{printf("24 ");}

"}"														{printf("25 ");}

";"														{printf("26 ");}

([+-]?{number}([.]{number})?)|(["][^"]*["])				{printf("1 ");}

{letter}({letter}|{digit})*								{printf("0 ");}

(^{letter})*({letter}|{digit}|[@])*		{printf("ID_ERROR ");}


[ \n\t]													;

.														{printf("?token ");}

%%
main(){
	printf("FIP: ");
	yylex();
}