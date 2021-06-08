#pragma once
typedef struct {
	int nr_apart;
	float cost;
	char* type;
}Expenses;

Expenses* createExp(int nr_apart, float cost, char* type);

void destroyExp(Expenses* exp);

Expenses* copyExp(Expenses* exp);

void testCreateDestroy();