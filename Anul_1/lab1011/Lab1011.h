#pragma once

#include <QtWidgets/QMainWindow>
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
#include "CosWidget.h"
#include "Model.h"

class Lab1011 : public QMainWindow, public Observable
{
	Q_OBJECT

public:
	Lab1011(Service& sv, QWidget *parent = Q_NULLPTR);

private:
	Service& serv;
	Ui::Lab1011Class ui;
	void init_gui_comps();
	void connect_signals_slots();
	void reload_list(std::vector<Locatar> l);
	void reload_cos(std::vector<Locatar> l);
	void reload_filterz(std::vector<Locatar> l);
	void reload_types(std::vector<DTO> l);
	QTableView *table_list = new QTableView;
	Model* mod = new Model(serv.get_all());
	QLineEdit *input_nume;
	QLineEdit *input_ap;
	QLineEdit *input_tip;
	QLineEdit *input_supraf;
	QPushButton *add;
	QPushButton *update;
	QPushButton *filtrare_tip;
	QPushButton *remove;
	QPushButton *sort_name;
	QPushButton *sort_supraf;
	QPushButton *sort_type;
	QPushButton *filtrare_supraf;
	QWidget *cos_wnd;
	QPushButton *add_cos;
	QPushButton *goleste_cos;
	QPushButton *add_random;
	QPushButton *save_cos;
	QPushButton *show_cos;
	QPushButton *show_rdonly;
	QPushButton *all_types;
	QPushButton *undo;
	QPushButton *random;
	QLineEdit *random_nr;
	//QListWidget *list;
	QTableWidget *list;
	QListWidget *cos;
	QListWidget *filterz_list;
	QListWidget *types_list;
	QWidget *filterz_wnd;
	QWidget *types_wnd;
};
