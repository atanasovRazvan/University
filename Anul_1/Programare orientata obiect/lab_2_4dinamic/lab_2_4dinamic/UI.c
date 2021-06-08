#include "UI.h"
#include "Utils.h"
#include "list.h"
#include "Domain.h"
#include <stdio.h>

void ui_add_expenses(Expediture* list) {
	printf("Add :\n~nr apartment~\n~cost~\n~type of expenses~\n");
	int nr_apart = read_nr_ap();
	float cost = (float)read_cost();
	char* type = read_type();
	addExp(list, nr_apart,cost,type);
}

void ui_remove_expenses(Expediture* list) {
	printf("Introdu index");
	int i;
	scanf_s("%d",&i);
	int sw=removeExp(list, i);
	if (sw == -1) {
		printf("Element inexistent");
	}
}

void ui_print_expenses(list* flist) {
	int it;
	for (it = 0; it < flist->lg; it++) {
		Expenses* exp = getExpenses(flist,it);
		printf("Ap:%d\tCost:%0.2f\tType:%s\n", exp->nr_apart, exp->cost, exp->type);
	}
}

void ui_modify_expenses(Expediture* list) {
	printf("Modify :\n~index\n~nr apartment~\n~cost~\n~type of expenses~\nindex>>");
	int i;
	scanf_s("%d", &i);
	int nr_apart = read_nr_ap();
	float cost = (float)read_cost();
	char *type = read_type();
	int sw = 0;
	sw = modifyExp(list, i, nr_apart, cost, type);
	if (sw == -1) {
		printf("Element inexistent");
	}
}

void ui_filtred_list(Expediture* list_exp) {
	printf("~Filter~\nWhich one do you prefer ?\n1.By nr.apart\t2.By type of expenses\t3.By cost\n");
	int answer = read_answer();
	int nr_apart;
	float cost;
	char* type;
	list* flist=createList(destroyExp);
	int cond = 1;
	switch (answer)
	{
	case 1: {
		nr_apart = read_nr_ap();
		flist = filter_nr_apart(list_exp, nr_apart);
		break;
	}
	case 2: {
		type = read_type();
		flist = filter_type(list_exp, type);
		break;

	}
	case 3: {
		cost = (float)read_cost();
		flist = filter_cost(list_exp, cost);
		break;
	}
	default:
		printf("Invalid answer!\n");
		cond = 0;
		break;
	}
	if (cond == 1) {
		int it;
		for (it = 0; it < flist->lg; it++) {
			Expenses* exp = getExpenses(flist, it);
			printf("Ap:%d\tCost:%0.2f\tType:%s\n",exp->nr_apart,exp->cost, exp->type);
		}
		destroyList(flist);
	}
	
}
void ui_sort_list_cost(Expediture* list_exp) {
	printf("Sorted list:\n");
	list* filtred_list;
	filtred_list = sort_by_cost(list_exp);
	int it = 0;
	for (it = 0; it < size(filtred_list); it++) {
		Expenses* exp = getExpenses(filtred_list, it);
		printf("Ap:%d\tCost:%0.2f\tType:%s\n", exp->nr_apart, exp->cost, exp->type);
	}
	destroyList(filtred_list);
}

void ui_sort_list_type(Expediture* list_exp) {
	printf("Sorted list:\n");
	list* filtred_list;
	filtred_list = sort_by_cost(list_exp);
	int it = 0;
	for (it = 0; it < size(filtred_list); it++) {
		Expenses* exp = getExpenses(filtred_list, it);
		printf("Ap:%d\tCost:%0.2f\tType:%s\n", exp->nr_apart, exp->cost, exp->type);
	}
	destroyList(filtred_list);
}
void ui_undo(Expediture*list_exp) {
	int sw = undo(list_exp);
	if(sw==0){
		printf("Undo facut cu succes\n");
	}
	else {
		printf("Undo nereusit");
	}
}

void run() {
	Expediture list = createExpediture();
	int cmd;
	int cond = 1;
	printf("\t~Administrator imobil~\nIntroduce your command from menu:\n");
	print_menu();

	while (cond) {
		cmd = read_cmd();
		switch (cmd)
		{
		case 0:
		{
			int answer;
			if (cmd == 0) {
				printf("Are you sure ?\n1.Yes\t2.No\n");
				answer = read_answer();
				if (answer == 1) {
					cond = 0;
				}
				else {
					if (answer != 2) {
						printf("Invalid answer!\n");
					}
				}
			}
			break;
		}
		case 1:
		{
			ui_add_expenses(&list);
			printf("Adaugat.\n");
			break;
		}
		case 2:
		{
			ui_modify_expenses(&list);
			printf("Modificat.\n");
			break;
		}
		case 3:
		{
			ui_remove_expenses(&list);
			printf("Sters.\n");
			break;
		}
		case 4:
		{
			ui_filtred_list(&list);
			break;
		}
		case 5:
		{
			ui_sort_list_cost(&list);
			break;
		}
		case 6:
		{
			print_menu();
			break;
		}
		case 7: {
			printf("Lista cheltuieli.\n");
			ui_print_expenses(list.allExp);
			break;
		}
		case 8: {
			ui_sort_list_type(&list);
			break;
		}
		case 9: {
			ui_undo(&list);
			break;
		}
		default:
			printf("Invalid command\n");
			break;
		}
	}
	destroyExpediture(&list);
}


void ui_tests() {
	testCopyList();
	testCreateDestroy();
	testCreateList();
	testIterateList();
	testResize();
	test_add_filters_exp();
	test_sort();
	test_rem();
	test_remove();
	test_modifyExp();
	test_undo();
	test_mod();
}