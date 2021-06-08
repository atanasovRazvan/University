#pragma once
#include "list.h"

typedef int(*Comparare)(Expenses*e1, Expenses*e2);
void sortare(list *l, Comparare cmp,int o);
/*
Print menu
*/
void print_menu();

/*
Read cmd
*/
int read_cmd();

/*
Read answer
*/
int read_answer();

/*
Read nr_apart
*/
int read_nr_ap();

/*
Read cost
*/
int read_cost();

/*
Read type
*/
char* read_type();