#include <qthread.h>
#include <QtWidgets/qstatusbar.h>
#include <QtWidgets/qmessagebox.h>
#include <QtWidgets/QApplication>
#include <QtWidgets/qmainwindow.h>
#include <QtWidgets/qlineedit.h>
#include <QtWidgets/qlistwidget.h>
#include <QtWidgets/qpushbutton.h>
#include <QtWidgets/qcombobox.h>
#include <QtWidgets/qboxlayout.h>
#include <QtWidgets\qformlayout.h>
#include <QtWidgets\qgridlayout.h>
#include <QtWidgets\qspinbox.h>
#include <QLabel>
#include <qdebug.h>
#include <QTableWidget>
#include <qcolor.h>
//#include <QtWidgets/qlabel.h>

void horizontalVerticalLayout() {
	QWidget *wnd = new QWidget;
	//QHBoxLayout *hLay = new QHBoxLayout();
	QVBoxLayout *hLay = new QVBoxLayout();
	QPushButton *btn1 = new QPushButton("Btkjashdakjdakjskjsasd &1");
	QPushButton *btn2 = new QPushButton("assaddsd  Bt &2");
	QPushButton *btn3 = new QPushButton("Bt &3");
	//hLay->addStretch();
	hLay->addWidget(btn1);
	//hLay->addStretch();
	hLay->addWidget(btn2);
	//hLay->addStretch();
	QWidget* parteJosw = new QWidget;
	QHBoxLayout* josL = new QHBoxLayout;
	parteJosw->setLayout(josL);
	hLay->addWidget(parteJosw);

	josL->addWidget(btn3);
	josL->addWidget(new QPushButton("Bt8"));
	josL->addWidget(new QPushButton("Bt8"));
	//hLay->addStretch();
	wnd->setLayout(hLay);

	wnd->show();
}

void formLayout() {
	QWidget *wnd3 = new QWidget;
	QVBoxLayout *vL = new QVBoxLayout;
	wnd3->setLayout(vL);
	//create a detail widget
	QWidget *details = new QWidget;
	QFormLayout *fL = new QFormLayout;
	details->setLayout(fL);
	QLabel *lblName = new QLabel("Name");
	QLineEdit *txtName = new QLineEdit;
	fL->addRow(lblName, txtName);
	QLabel *lblAge = new QLabel("Age");
	QLineEdit *txtAge = new QLineEdit;
	fL->addRow(lblAge, txtAge);
	//add detail to window
	vL->addWidget(details);
	QPushButton *store = new QPushButton("&Store");
	vL->addWidget(store);
	//vL->addStretch();
	//show window
	wnd3->show();
}

void gridLayout() {
	QWidget* wnd = new QWidget;
	QGridLayout* ly = new QGridLayout;
	wnd->setLayout(ly);
	QLineEdit* lineEdt = new QLineEdit;
	ly->addWidget(lineEdt, 0, 0, 1, 5);

	QPushButton* clearMemoryButton = new QPushButton{ "MC" };
	ly->addWidget(clearMemoryButton, 2, 0);
	QPushButton* readMemoryButton = new QPushButton{ "MR" };
	ly->addWidget(readMemoryButton, 3, 0);
	QPushButton* setMemoryButton = new QPushButton{ "MS" };
	ly->addWidget(setMemoryButton, 4, 0);
	QPushButton* addToMemoryButton = new QPushButton{ "M+" };
	ly->addWidget(addToMemoryButton, 5, 0);

	for (int i = 1; i < 10; ++i) {
		int row = ((9 - i) / 3) + 2;
		int column = ((i - 1) % 3) + 1;
		QPushButton* btn = new QPushButton{ QString::number(i) };
		ly->addWidget(btn, row, column);
	}
	ly->addWidget(new QPushButton{ "0" }, 5, 1);
	ly->addWidget(new QPushButton{ "." }, 5, 2);
	ly->addWidget(new QPushButton{ "=" }, 5, 3);

	wnd->setWindowTitle("Qt Calculator");
	wnd->show();
}

void guiComponents() {
	/*
	QLabel *label = new QLabel("hello world");
	label->show();

	QLineEdit* txt = new QLineEdit;
	txt->show();

	QPushButton* btn = new QPushButton{ "Buton &Fain" };
	btn->show();
	
	QListWidget* lst = new QListWidget;
	lst->addItem("Bla 1");
	lst->addItem("Bla 2");
	lst->addItem("Bla 3");
	lst->addItem("Bla 4");
	new QListWidgetItem{ "Bla5",lst };
	lst->show();
	
	QComboBox* cmb = new QComboBox;
	cmb->addItem("Item1");
	cmb->addItem("Item2");
	cmb->addItem("Item3");
	cmb->addItem("Item4");
	cmb->show();
	*/
	QTableWidget* table=new QTableWidget{ 10,20 };
	table->show();
	
	QTableWidget tableWrong{ 10,20 };
	tableWrong.show();
	//destroyed when we exit the function
}

QWidget* createButtons(QApplication &a) {
	QWidget* btns = new QWidget;
	QHBoxLayout* btnsL = new QHBoxLayout;
	btns->setLayout(btnsL);
	QPushButton* store = new QPushButton("&Store");
	btnsL->addWidget(store);
	QPushButton* close = new QPushButton("&Close");
	btnsL->addWidget(close);
	//connect the clicked signal from close button to the quit slot (method)
	//QObject::connect(close, SIGNAL(clicked()), &a, SLOT(quit()));
	//QObject::connect(close,&QPushButton::clicked, &a, &QApplication::quit);
	QObject::connect(close, &QPushButton::clicked, &a, &QApplication::quit);
	//QObject::connect(close, &QPushButton::clicked, [&a]() {
	//	a.quit(); });
	QObject::connect(store, &QPushButton::clicked, []() {
		QMessageBox::information(nullptr, "Titlu", "Felicitaari ati castigat!!!");
	});
	return btns;
}

void slidere() {
	QSpinBox *spAge = new QSpinBox();
	QSlider *slAge = new QSlider(Qt::Horizontal);
	QVBoxLayout* lay = new QVBoxLayout;
	lay->addWidget(spAge);
	lay->addWidget(slAge);
	QWidget* main = new QWidget;
	main->setLayout(lay);
	main->show();
	//Synchronise the spinner and the slider
	//Connect spin box - valueChanged to slider setValue
	//	QObject::connect(spAge, SIGNAL(valueChanged(int)), slAge, SLOT(setValue(int)));
	//Connect - slider valueChanged to spin box setValue
	//	QObject::connect(slAge, SIGNAL(valueChanged(int)), spAge, SLOT(setValue(int)));
	//prutem prelua valori de la semnal
	QObject::connect(spAge, SIGNAL(valueChanged(int)),
		slAge, SLOT(setValue(int)));
	//Connect - slider valueChanged to spin box setValue
	QObject::connect(slAge, &QSlider::valueChanged,
		[spAge](int val) {spAge->setValue(val); });

}
/**
* Create GUI using absolute positioning
*/
void createAbsolute() {
	QWidget* main = new QWidget();
	QLabel* lbl = new QLabel("Name person of interest:", main);
	lbl->setGeometry(10, 10, 40, 20);
	QLineEdit* txt = new QLineEdit(main);
	txt->setGeometry(60, 10, 100, 20);
	main->show();
	main->setWindowTitle("Absolute");
}
/**
* Create the same GUI using form layout
*/
void createWithLayout() {
	QWidget* main = new QWidget();
	QFormLayout *fL = new QFormLayout(main);
	QLabel* lbl = new QLabel("Name  person of interest:", main);
	QLineEdit* txt = new QLineEdit(main);
	fL->addRow(lbl, txt);
	main->show();
	main->setWindowTitle("Layout");
	//fix the height to the "ideal" height
	main->setFixedHeight(main->sizeHint().height());
}

class MyGUI : public QMainWindow {
	
private:
	QPushButton * btn1 = new QPushButton{ "my buton" };
	QPushButton * btn2 = new QPushButton{ "my buton" };
	void initComponents() {
		setStatusBar(new QStatusBar);
		QWidget* centru = new QWidget;

		QVBoxLayout* vl = new QVBoxLayout;
		centru->setLayout(vl);
		vl->addWidget(btn1);
		vl->addWidget(btn2);		
		setCentralWidget(centru);
	}
	void setInitialState() {
		btn1->setEnabled(false);
	}
public:
	MyGUI() {
		initComponents();
		setInitialState();
	}

};

int main2(int argc, char *argv[])
{
	QApplication a(argc, argv);
	//MyGUI gui;
	//gui.show();

	
	//guiComponents();
    //horizontalVerticalLayout();
	//createAbsolute();
	//createWithLayout();

	//createButtons(a)->show();
	slidere();




	//formLayout();

	//gridLayout();

	return a.exec();
}
#include <vector>
#include <string>

class MyException{};
class Service {
	std::vector<std::string> rez{ "a","b","v" };
public:
	void add(const std::string& el) {
		if (el.size() == 0) {
			throw MyException{};
		}
		rez.push_back(el);
		QThread::sleep(5);
	}
	std::vector<std::string> getAll() {
		
		return rez;
	}
};


class MyGui :public QWidget {

private:
	Service& serv;
	QListWidget * lista = new QListWidget;
	QPushButton* exitBtn = new QPushButton{"&Exit"};
	QPushButton* deleteBtn = new QPushButton{ "&Sterge" };
	QPushButton* adaugaBtn = new QPushButton{ "&Adauga" };
	QLineEdit* edit = new QLineEdit;

	void initComponents() {
		QVBoxLayout* mainL = new QVBoxLayout;
		setLayout(mainL);
		mainL->addWidget(lista);
		mainL->addWidget(edit);

		QHBoxLayout* btnL = new QHBoxLayout;
		btnL->addWidget(deleteBtn);
		btnL->addWidget(adaugaBtn);
		btnL->addStretch();		
		btnL->addWidget(exitBtn);		
		QWidget* btnW = new QWidget;
		btnW->setLayout(btnL);
		mainL->addWidget(btnW);
	}
	void initSignalsAndSlots() {
		QObject::connect(exitBtn, &QPushButton::clicked, [&]() {
			QMessageBox::information(nullptr, "By", "ByBy!!!");
			this->setVisible(false);
		});
		QObject::connect(lista, &QListWidget::itemSelectionChanged, [&]() {
			this->deleteBtn->setEnabled(true);
			auto item = lista->selectedItems().at(0);			
			this->deleteBtn->setText("Sterge " + item->text());			
		});
		QObject::connect(adaugaBtn, &QPushButton::clicked,this,&MyGui::adaugaGUI);
	}

	void adaugaGUI() {
		try {
			serv.add(edit->text().toStdString());
			populeazaLista(serv.getAll());
		}
		catch (MyException&) {
			QMessageBox::warning(nullptr, "Validare", "String gresit!!!!");
		}
	}

	void populeazaLista(std::vector<std::string> elems) {
		lista->clear();
		int i = 0;
		for (const auto& e :elems){
			QListWidgetItem* item = new QListWidgetItem{ QString::fromStdString(e) };
			if (i % 2 == 0) {
				item->setData(Qt::BackgroundRole, QColor(255, 0, 0, 127));
			}
			i++;
			lista->addItem(item);
		}
	}

	void loadInitialState() {
		populeazaLista(serv.getAll());
		deleteBtn->setEnabled(false);
		
	}

public:
	MyGui(Service& serv) :serv{ serv } {
		initComponents();
		initSignalsAndSlots();
		loadInitialState();
		
	}
};


int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	Service serv;
	MyGui gui{ serv };
	gui.show();
	return a.exec();
}