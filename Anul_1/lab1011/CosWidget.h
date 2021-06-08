#include <QtWidgets/QMainWindow>
#include <cstdlib>
#include "ui_Lab1011.h"
#include <QHBoxLayout>
#include <QVBoxLayout>
#include "Service.h"
#include <QListWidget>
#include <QPushButton>
#include <QLineEdit>
#include <QLabel>
#include <QString>
#include <QDebug>
#include <QMessageBox>
#include <QColor>
#include <QDir>
#include <QInputDialog>
#include <QFormLayout>
#include <QTableWidget>
#include <QAbstractItemView>
#include <QPainter>
#include <QPaintEvent>
#pragma once

class Observer {
public:
	virtual void update() = 0;
};

class Observable {
private:
	std::vector<Observer*> observers;
public:
	void addObservers(Observer* obs);

	void removeObserver(Observer*obs);

	void notify();
protected:
	//void notify();
};

class CosWidget : public QWidget, public Observer, public Observable {
	Q_OBJECT;
public:
	CosWidget(Service& sv, Observable& tati, QWidget *parent = Q_NULLPTR);
	void update() override;
signals:
	void changed_cos();
private:
	Service& serv;
	Observable& win;
	QWidget *cos_wnd = new QWidget;
	QPushButton *add_cos = new QPushButton("Add to list");
	QPushButton *goleste_cos = new QPushButton("Empty");
	QPushButton *add_random = new QPushButton("Random add");
	QPushButton *save_cos = new QPushButton("Save to CSV");
	QPushButton *show_cos = new QPushButton("List");
	QListWidget *cos = new QListWidget;
	QPushButton *random = new QPushButton("Add random apts");
};

class CosRDONLY : public QWidget, public Observer {
	Q_OBJECT;
public:
	CosRDONLY(Service& sv, Observable& tati, QWidget *parent = Q_NULLPTR);
	void paintEvent(QPaintEvent* ev) override;
	void update() override;
private:
	Service& serv;
	Observable& win;
	QWidget *cos_wnd = new QWidget;
	QPushButton *add_cos = new QPushButton("Add to list");
	QPushButton *goleste_cos = new QPushButton("Empty");
	QPushButton *add_random = new QPushButton("Random add");
	QPushButton *save_cos = new QPushButton("Save to CSV");
	QPushButton *show_cos = new QPushButton("List");
	QListWidget *cos = new QListWidget;
	QPushButton *random = new QPushButton("Add random apts");
};