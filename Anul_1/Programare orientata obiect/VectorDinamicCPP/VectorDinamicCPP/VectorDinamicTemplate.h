#pragma once
#define INITIAL_CAPACITY 5

template <typename ElementT>
class iterator_lista;

template <typename ElementT>
struct nod
{
	nod *urmator;
	nod *predecesor;
	ElementT el;
};


/*template <class ElementT>
class nod
{
public:
	nod(const ElementT &e = 0, nod *n = NULL, nod *p = NULL) : el(e), urmator(n), predecesor(p) {}
	~nod() {}
	nod *urmator;
	nod *predecesor;
	ElementT el;

};*/

template <typename ElementT>
class lista
{
public:
	/*
	Constructor default
	Alocam loc pentru 5 elemente
	*/
	lista();

	/*
	Constructor de copiere
	*/
	lista(const lista& ot); //rule of 3

	/*
	Eliberam memoria
	*/
	~lista();//rule of 3

	/*
	Operator assgnment
	elibereaza ce era in obiectul curent (this)
	aloca spatiu pentru elemente
	copieaza elementele din ot in this
	*/
	lista& operator=(const lista& ot);//rule of 3


	/*
	Move constructor
	Apelat daca construim un nou vector dintr-un r-value (ex temporary, expresie de la return)
	Obiectul ot nu se mai foloseste astfel se poate refolosi interiorul lui
	*/
	lista(lista&& ot); //rule of 5 
	/*
	Move assignment
	Similar cu move constructor
	Folosit la assignment
	*/
	lista& operator=(lista&& ot); //rule of 5 
	void modifica(ElementT el, ElementT el2);
	void sterge(const ElementT &el);
	//void sterge_tot();
	void add(const ElementT& el);
	nod<ElementT>* gaseste(const ElementT &el);

	ElementT& get(int poz) const;

	void set(int poz, const ElementT& el);

	int size() const noexcept;

	friend class iterator_lista<ElementT>;
	//functii care creaza iteratori
	iterator_lista<ElementT> begin() const;
	iterator_lista<ElementT> end() const;
	int get_size();

private:
	nod<ElementT> *prim;
	nod<ElementT> *ultim;
	int lg;
	//int lg;//numar elemente
	//int cap;//capacitate
	//ElementT* elems;//elemente

	//void ensureCapacity();
};

/*
Constructor default
Alocam loc pentru 5 elemente
*/
template<typename ElementT>
lista<ElementT>::lista() :prim{ nullptr }, ultim{ nullptr }, lg{ 0 } {}

/*
Constructor de copiere
Obiectul current (this) acum se creaza
aloca spatiu pentru elemente
copieaza elementele din ot in this
*/
template<typename ElementT>
lista<ElementT>::lista(const lista<ElementT>& ot) {
	if (ot.prim == nullptr)
		prim = nullptr;
	else {
		lg = ot.lg;
		prim = new nod<ElementT>;
		nod<ElementT> *cop = ot.prim;
		prim->el = ot.prim->el;
		prim->predecesor = nullptr;
		prim->urmator = nullptr;
		nod<ElementT> *temp = prim;
		cop = cop->urmator;

		while (cop != nullptr) {
			temp->urmator = new nod<ElementT>;
			temp->urmator->predecesor = temp;
			temp = temp->urmator;
			temp->el = cop->el;

			cop = cop->urmator;

		}

		ultim = temp;
		ultim->urmator = nullptr;
	}
}

/*
Operator assgnment
elibereaza ce era in obiectul curent (this)
aloca spatiu pentru elemente
copieaza elementele din ot in this
*/
template<typename ElementT>
lista<ElementT>& lista<ElementT>::operator=(const lista<ElementT>& ot) {
	if (this == &ot) {
		return *this;//s-a facut l=l;
	}
	else {
		lg = ot.lg;
		nod<ElementT> *cop = prim;
		while (prim != nullptr)
		{
			cop = prim;
			prim = prim->urmator;
			delete cop;
		}
		prim = new nod<ElementT>;
		cop = ot.prim;
		prim->el = ot.prim->el;
		prim->predecesor = nullptr;
		prim->urmator = nullptr;
		nod<ElementT> *temp = prim;
		cop = cop->urmator;
		while (cop != nullptr) {
			temp->urmator = new nod<ElementT>;
			temp->urmator->predecesor = temp;
			temp = temp->urmator;
			temp->el = cop->el;
			cop = cop->urmator;

		}
		ultim = temp;
		ultim->urmator = nullptr;
		return *this;
	}
}

/*
Eliberam memoria
*/
template<typename ElementT>
lista<ElementT>::~lista() {
	while (ultim != nullptr)
	{
		nod<ElementT> *remove = ultim;
		ultim = ultim->predecesor;
		delete remove;
	}
	lg = 0;
}


/*
Move constructor
Apelat daca construim un nou vector dintr-un r-value (ex temporary)
Obiectul ot nu se mai foloseste astfel se poate refolosi interiorul lui
*/
template<typename ElementT>
lista<ElementT>::lista(lista&& ot) {
	// Copy the data pointer from other
	prim = ot.prim;
	ultim = ot.ultim;
	lg = ot.lg;

	// Release the data pointer from the source object so that  
	// the destructor does not free the memory multiple times.  
	ot.prim = nullptr;
	ot.lg = 0;
	ot.ultim = nullptr;
}

/*
Move assignment
Similar cu move constructor
Folosit la assignment
Elibereaza ce continea obiectul curent (this)
"fura" interiorul lui ot
pregateste ot pentru distrugere
*/
template<typename ElementT>
lista<ElementT>& lista<ElementT>::operator=(lista<ElementT>&& ot) {
	if (this == &ot) {
		return *this;
	}
	nod<ElementT> *cop = prim;
	while (prim != nullptr)
	{
		cop = prim;
		prim = prim->urmator;
		delete cop;
	}

	// Copy the data pointer from other
	prim = ot.prim;
	ultim = ot.ultim;

	lg = ot.lg;

	// Release the data pointer from the source object so that  
	// the destructor does not free the memory multiple times.  
	ot.prim = nullptr;
	ot.ultim = nullptr;
	ot.lg = 0;
	return *this;
}

template<typename ElementT>
inline void lista<ElementT>::modifica(ElementT el, ElementT el2)
{
	nod<ElementT> *n = gaseste(el);
	n->el = el2;
}

template<typename ElementT>
inline void lista<ElementT>::sterge(const ElementT & el)
{

	nod<ElementT> *e = gaseste(el);
	if (lg == 1)
	{
		delete e;
		prim = nullptr;
		ultim = nullptr;
	}
	else
	{
		if (e->urmator == nullptr)
		{
			e->predecesor->urmator = nullptr;
			ultim = e->predecesor;
			delete e;
		}
		else {
			if (e->predecesor == nullptr)
			{
				e->urmator->predecesor = nullptr;
				prim = e->urmator;
				delete e;
			}
			else {
				e->predecesor->urmator = e->urmator;
				e->urmator->predecesor = e->predecesor;
				delete e;
			}
		}
	}
	lg = lg - 1;

}

/*template<typename ElementT>
inline void lista<ElementT>::sterge_tot()
{
	while (prim != nullptr)
	{
		nod<ElementT> *n = prim;
		prim = prim->urmator;
		delete n;
	}
}
*/
template<typename ElementT>
void lista<ElementT>::add(const ElementT& el) {
	nod<ElementT> *n = new nod<ElementT>;
	if (ultim == nullptr)
	{
		ultim = n;
		ultim->el = el;
		ultim->predecesor = nullptr;
		ultim->urmator = nullptr;
		prim = ultim;
	}
	else {
		n->el = el;
		ultim->urmator = n;
		n->predecesor = ultim;
		n->urmator = nullptr;
		ultim = n;
		ultim->urmator = nullptr;
	}
	lg += 1;

}

template<typename ElementT>
inline nod<ElementT>* lista<ElementT>::gaseste(const ElementT & el)
{
	nod<ElementT> *n = prim;
	while (n != nullptr)
	{
		if (n->el == el)
			return n;
		n = n->urmator;
	}
	throw(10);
}

template<typename ElementT>
ElementT& lista<ElementT>::get(int poz) const {
	int nr = 0;
	iterator_lista<ElementT> it = iterator_lista<ElementT>(*this);
	while (it.valid() && nr != poz)
	{
		it.next();
		nr++;
	}
	if (it.valid())
		return it.element();
}

template<typename ElementT>
void lista<ElementT>::set(int poz, const ElementT& el) {
	int nr = 0;
	iterator_lista<ElementT> it = iterator_lista<ElementT>(*this);
	while (it.valid() && nr != poz)
	{
		it.next();
		nr++;
	}
	if (it.valid())
		it.element = el;

}

template<typename ElementT>
int lista<ElementT>::size() const noexcept {
	return lg;
}

/*template<typename ElementT>
void VectDinNewDeleteT<ElementT>::ensureCapacity() {
	if (lg < cap) {
		return; //mai avem loc
	}
	cap *= 2;
	ElementT* aux = new ElementT[cap];
	for (int i = 0; i < lg; i++) {
		aux[i] = elems[i];
	}
	delete[] elems;
	elems = aux;
}
*/
template<typename ElementT>
iterator_lista<ElementT> lista<ElementT>::begin() const
{
	return iterator_lista<ElementT>(*this);
}

template<typename ElementT>
iterator_lista<ElementT> lista<ElementT>::end() const
{
	return iterator_lista<ElementT>(*this, ultim);
}

template<typename ElementT>
inline int lista<ElementT>::get_size()
{
	return lg;
}

template<typename ElementT>
class iterator_lista {
private:
	const lista<ElementT>& v;
	nod <ElementT> *curent;
public:
	iterator_lista(const lista<ElementT>& v) noexcept;
	iterator_lista(const lista<ElementT>& v, nod <ElementT> *ultim)noexcept;
	bool valid()const;
	ElementT& element() const;
	void next();
	ElementT& operator*();
	iterator_lista& operator++();
	bool operator==(const iterator_lista& ot)noexcept;
	bool operator!=(const iterator_lista& ot)noexcept;
};

template<typename ElementT>
iterator_lista<ElementT>::iterator_lista(const lista<ElementT>& v) noexcept :v{ v } { curent = v.prim; }

template<typename ElementT>
iterator_lista<ElementT>::iterator_lista(const lista<ElementT>& v, nod <ElementT> *ultim)noexcept : v{ v }, curent{ ultim }{}

template<typename ElementT>
bool iterator_lista<ElementT>::valid()const {
	return curent != nullptr;
}

template<typename ElementT>
ElementT& iterator_lista<ElementT>::element() const {
	return curent->el;
}

template<typename ElementT>
void iterator_lista<ElementT>::next() {
	curent = curent->urmator;
}

template<typename ElementT>
ElementT& iterator_lista<ElementT>::operator*() {
	return element();
}

template<typename ElementT>
iterator_lista<ElementT>& iterator_lista<ElementT>::operator++() {
	next();
	return *this;
}

template<typename ElementT>
bool iterator_lista<ElementT>::operator==(const iterator_lista<ElementT>& ot) noexcept {
	return curent == ot.curent;
}

template<typename ElementT>
bool iterator_lista<ElementT>::operator!=(const iterator_lista<ElementT>& ot)noexcept {
	return !(*this == ot);
}