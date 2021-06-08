#pragma once
#include "ExpService.h"

void ui_add_expenses(Expediture* list);

void ui_remove_expenses(Expediture* list);

void ui_modify_expenses(Expediture* list);

void ui_print_expenses(list* list_elems);

void ui_sort_list_cost(Expediture* list);

void ui_filtred_list(Expediture* list);

void ui_sort_list_type(Expediture* list_exp);

void ui_undo(Expediture*list_exp);

void ui_tests();

void run();