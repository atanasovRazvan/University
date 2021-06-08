#include "CosWidget.h"

CosWidget::CosWidget(Service & sv, Observable& tati, QWidget * parent) :QWidget{ parent }, win{ tati }, serv{ sv }
{
	win.addObservers(this);
	update();
	QHBoxLayout* ly_principal = new QHBoxLayout;
	//setLayout(ly_principal);
	//ly_principal->addWidget(add_cos);
	setAttribute(Qt::WA_QuitOnClose, false);
	setWindowTitle("List of apartments");
	QVBoxLayout *l1 = new QVBoxLayout();
	QHBoxLayout *l2 = new QHBoxLayout();
	QWidget *cos_w = new QWidget();
	cos = new QListWidget();
	setLayout(l1);
	QWidget *bt1 = new QWidget();
	bt1->setLayout(l2);
	goleste_cos = new QPushButton("Empty list");
	save_cos = new QPushButton("Save list to CSV");
	l2->addWidget(goleste_cos);
	l2->addWidget(save_cos);
	l1->addWidget(cos);
	l1->addWidget(bt1);
	l1->addWidget(random);

	QObject::connect(goleste_cos, &QPushButton::clicked, this, [&]() {
		serv.goleste_cos();
		//reload_cos(serv.get_cos());
		//notify();
		update();
		win.notify();
	});

	QObject::connect(random, &QPushButton::clicked, this, [&]() {
		bool ok;
		int ap = QInputDialog::getInt(this, "Random add", "Number of apartments to add:", 0, 0, 2147483647, 1, &ok);
		if (ok) {
			serv.populeaza(ap);
		}
		else {
			QMessageBox::information(this, "Invalid input",
				"You must input the maximum amount of apartments to be added to the list as an integer!");
		}
		win.notify();
	});

	QObject::connect(save_cos, &QPushButton::clicked, this, [&]() {
		int r = QMessageBox::warning(this, "Apartments", "Are you sure you want to save this to the CSV?", QMessageBox::Save | QMessageBox::Cancel);
		if (r == QMessageBox::Save) {
			serv.salveaza_cos();
		}
	});
}

void CosWidget::update()
{
	cos->clear();
	auto v = serv.get_cos();
	for (auto &x : v) {
		QListWidgetItem* it = new QListWidgetItem(QString::number(x.get_apartament()), this->cos);
		it->setData(Qt::UserRole, QString::fromStdString(x.get_nume()));
		it->setData(257, QString::fromStdString(x.get_tip()));
		it->setData(258, QString::number(x.get_suprafata()));
	}
}

CosRDONLY::CosRDONLY(Service & sv, Observable& tati, QWidget * parent) :serv{ sv }, win{ tati }, QWidget{ parent }
{
	win.addObservers(this);
	setMinimumHeight(300);
	setMinimumWidth(400);
	srand(time(0));
	setAttribute(Qt::WA_QuitOnClose, false);
	setWindowTitle("List of apartments");
	QVBoxLayout *l1 = new QVBoxLayout();
	setLayout(l1);
	l1->addWidget(cos_wnd);
	l1->addWidget(random);

	QObject::connect(random, &QPushButton::clicked, this, [&]() {
		bool ok;
		int ap = QInputDialog::getInt(this, "Random add", "Number of apartments to add:", 0, 0, 2147483647, 1, &ok);
		if (ok) {
			serv.populeaza(ap);
		}
		else {
			QMessageBox::information(this, "Invalid input",
				"You must input the maximum amount of apartments to be added to the list as an integer!");
		}
		win.notify();
	});

	QObject::connect(save_cos, &QPushButton::clicked, this, [&]() {
		int r = QMessageBox::warning(this, "Apartments", "Are you sure you want to save this to the CSV?", QMessageBox::Save | QMessageBox::Cancel);
		if (r == QMessageBox::Save) {
			serv.salveaza_cos();
		}
	});
}

void CosRDONLY::paintEvent(QPaintEvent * ev)
{
	QPainter p{ this };
	p.setPen(Qt::red);
	int x, y, xx, yy;
	for (int i = 1; i <= serv.get_cos().size(); i++) {
		x = rand() % width();
		xx = rand() % width();
		y = rand() % height();
		yy = rand() % height();
		//p.drawPoint(x, y);
		p.drawLine(x, y, xx, yy);
	}
}

void CosRDONLY::update()
{
	//paint pls
	//paintEvent();
	this->repaint();
}

void Observable::addObservers(Observer * obs)
{
	observers.push_back(obs);
}

void Observable::removeObserver(Observer * obs)
{
	observers.erase(std::remove(begin(observers), end(observers), obs), observers.end());
}

void Observable::notify()
{
	for (auto obs : observers) {
		obs->update();
	}
}
