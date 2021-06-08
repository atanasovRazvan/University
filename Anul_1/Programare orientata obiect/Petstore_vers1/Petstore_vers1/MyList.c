#include "MyList.h"

#include <assert.h>
#include <string.h>

/*
Create an empty list
*/
MyList createEmpty() {
	MyList rez;
	rez.lg = 0;
	return rez;
}

/*
Destroy list
*/
void destroy(MyList* l) {
	//nothing to dealocate for now
	l->lg = 0;
}

/*
Get an element from the list
poz - position of the element, need to be valid
return element on the given position
*/
ElemType get(MyList* l, int poz) {
	return l->elems[poz];
}

/*
return number of elements in the list
*/
int size(MyList* l) {
	return l->lg;
}

/*
Add element into the list
post: element is added to the end of the list
*/
void add(MyList* l, ElemType el) {
	l->elems[l->lg] = el;
	l->lg++;
}


/*
Make a shallow copy of the list
return Mylist containing the same elements as l
*/
MyList copyList(MyList* l) {
	MyList rez = createEmpty();
	for (int i = 0; i < size(l); i++) {
		ElemType p = get(l, i);		
		add(&rez, p);
	}
	return rez;
}

void testCreateList() {
	MyList l = createEmpty();
	assert(size(&l) == 0);
}
void testIterateList() {
	MyList l = createEmpty();
	add(&l, createPet("a", "b", 10));
	add(&l, createPet("a2", "b2", 20));
	assert(size(&l) == 2);
	Pet p = get(&l,0);

	assert(strcmp(p.type,"a") == 0);
	p = get(&l, 1);
	assert(strcmp(p.species, "b2") == 0);
	destroy(&l);
	assert(size(&l) == 0);
}

void testCopyList() {
	MyList l = createEmpty();
	add(&l, createPet("a", "b", 10));
	add(&l, createPet("a2", "b2", 20));
	MyList l2 = copyList(&l);
	assert(size(&l2) == 2);
	Pet p = get(&l2, 0);
	assert(strcmp(p.type, "a") == 0);
}
