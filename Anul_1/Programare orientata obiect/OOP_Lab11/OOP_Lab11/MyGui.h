#pragma once
#include <QtWidgets/qwidget.h>
#include <QtWidgets/qpushbutton.h>
#include <qlineedit.h>
#include <QtWidgets/qboxlayout.h>
#include <QtWidgets/qlistwidget.h>
#include <qerrormessage.h>
#include <qlabel.h>
#include <sstream>
#include "Service.h"
#include "ValidateException.h"
#include "MarketGUI.h"
#include <QTableWidget>
#include <cstring>

class MyGUI : public QWidget {
public:
	//MyGUI(Service& serv) : film_service{ serv }
	MyGUI(Service& service) : service{ service } {
		initializeGUIComponents();
		initialGUIState();
		connectSignals();
	}
private:
	Service& service;

	QTableWidget* carList = new QTableWidget(0, 5, this);

	QLineEdit* registrationNumber = new QLineEdit;
	QLineEdit* manufacturer = new QLineEdit;
	QLineEdit* model = new QLineEdit;
	QLineEdit* type = new QLineEdit;

	QPushButton* add = new QPushButton{ "Add" };
	QPushButton* update = new QPushButton{ "Update" };
	QPushButton* remove = new QPushButton{ "Remove" };
	QPushButton* undo = new QPushButton{ "Undo" };

	QPushButton* marketBtn = new QPushButton{ "Market" };

	QPushButton* sortModel = new QPushButton{ "Sort by model" };
	QPushButton* sortType = new QPushButton{ "Sort by type" };

	QPushButton* filterType = new QPushButton{ "Filter by type" };
	QPushButton* filterModel = new QPushButton{ "Filter by model" };

	QWidget* sortFilterWidet = new QWidget;


	void initializeGUIComponents() {
		carList->setSelectionMode(QAbstractItemView::SingleSelection);
		carList->setHorizontalHeaderItem(0, new QTableWidgetItem("Registration Number"));
		carList->setHorizontalHeaderItem(1, new QTableWidgetItem("Manufacturer"));
		carList->setHorizontalHeaderItem(2, new QTableWidgetItem("Model"));
		carList->setHorizontalHeaderItem(3, new QTableWidgetItem("Type"));
		carList->setHorizontalHeaderItem(4, new QTableWidgetItem("Nr Type"));
		QHBoxLayout* main = new QHBoxLayout;
		setLayout(main);

		QVBoxLayout* leftSide = new QVBoxLayout;
		QVBoxLayout* list = new QVBoxLayout;

		list->addWidget(carList);

		QHBoxLayout* filerAndUndo = new QHBoxLayout;
		filerAndUndo->addWidget(sortModel);
		filerAndUndo->addWidget(sortType);
		filerAndUndo->addWidget(filterModel);
		filerAndUndo->addWidget(filterType);

		leftSide->addLayout(list);
		leftSide->addLayout(filerAndUndo);





		QVBoxLayout* rightSide = new QVBoxLayout;

		QVBoxLayout* lineInput = new QVBoxLayout;
		lineInput->addWidget(registrationNumber);
		lineInput->addWidget(manufacturer);
		lineInput->addWidget(model);
		lineInput->addWidget(type);

		QVBoxLayout* label = new QVBoxLayout;
		QLabel* rnLabel = new QLabel("RegistrationNumber: ");
		QLabel* manLabel = new QLabel("Manufacturer: ");
		QLabel* modelLabel = new QLabel("Model: ");
		QLabel* typeLabel = new QLabel("Type: ");
		label->addWidget(rnLabel);
		label->addWidget(manLabel);
		label->addWidget(modelLabel);
		label->addWidget(typeLabel);

		QHBoxLayout* inputLayout = new QHBoxLayout;
		inputLayout->addLayout(label);
		inputLayout->addLayout(lineInput);

		QHBoxLayout* editBtn = new QHBoxLayout;
		editBtn->addWidget(add);
		editBtn->addWidget(update);
		editBtn->addWidget(remove);
		editBtn->addWidget(undo);

		rightSide->addLayout(inputLayout);
		rightSide->addLayout(editBtn);
		rightSide->addWidget(marketBtn);
		rightSide->addStretch(1);

		main->addLayout(leftSide);
		main->addLayout(rightSide);
	}

	void initialGUIState() {
		carList->setRowCount(0);
		int counter = 0, size = service.size();
		for (auto &car : service.getAll()) {
			carList->insertRow(carList->rowCount());
			//m_table->setItem(currentRow, 0, new QTableWidgetItem(m_lineEdit1->text()));
			  //string str = car.g
			string man = car.getManufacturer();
			string rn = car.getRegistrationNumber();
			string type = car.getType();
			string model = car.getModel();
			int nr = 0;
			for (auto const& el : service.getAll()) {
				if (el.getType() == type)
					nr++;
			}

			QTableWidgetItem* item = new QTableWidgetItem(rn.c_str());
			QTableWidgetItem* item2 = new QTableWidgetItem(man.c_str());
			QTableWidgetItem* item3 = new QTableWidgetItem(model.c_str());
			QTableWidgetItem* item4 = new QTableWidgetItem(type.c_str());
			QTableWidgetItem* item5 = new QTableWidgetItem(std::to_string(nr).c_str());

			if (counter < size / 3) {
				item->setBackground(QBrush{ Qt::red, Qt::SolidPattern });
				item2->setBackground(QBrush{ Qt::red, Qt::SolidPattern });
				item3->setBackground(QBrush{ Qt::red, Qt::SolidPattern });
				item4->setBackground(QBrush{ Qt::red, Qt::SolidPattern });
				item5->setBackground(QBrush{ Qt::red, Qt::SolidPattern });
			}
			else if (counter < 2 * size / 3) {
				item->setBackground(QBrush{ Qt::darkYellow, Qt::SolidPattern });
				item2->setBackground(QBrush{ Qt::darkYellow, Qt::SolidPattern });
				item3->setBackground(QBrush{ Qt::darkYellow, Qt::SolidPattern });
				item4->setBackground(QBrush{ Qt::darkYellow, Qt::SolidPattern });
				item5->setBackground(QBrush{ Qt::darkYellow, Qt::SolidPattern });
			}
			else {
				item->setBackground(QBrush{ Qt::blue, Qt::SolidPattern });
				item2->setBackground(QBrush{ Qt::blue, Qt::SolidPattern });
				item3->setBackground(QBrush{ Qt::blue, Qt::SolidPattern });
				item4->setBackground(QBrush{ Qt::blue, Qt::SolidPattern });
				item5->setBackground(QBrush{ Qt::blue, Qt::SolidPattern });
			}
			counter++;
			carList->setItem(carList->rowCount() - 1, 0, item);
			carList->setItem(carList->rowCount() - 1, 1, item2);
			carList->setItem(carList->rowCount() - 1, 2, item3);
			carList->setItem(carList->rowCount() - 1, 3, item4);
			carList->setItem(carList->rowCount() - 1, 4, item5);
			//QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(car.getAll()), carList);
			//item->setData(Qt::UserRole, QString::fromStdString(car.getRegistrationNumber()));
		}
	}

	void connectSignals() {
		QObject::connect(marketBtn, &QPushButton::clicked, [&]() {
			MarketGUI *marketGui = new MarketGUI(service);
			marketGui->show();
		});

		QObject::connect(undo, &QPushButton::clicked, [&]() {
			try {
				service.undo();
			}
			catch (RepoException& ve) {
				QErrorMessage err(this);
				std::stringstream ss{};
				ss << ve;
				err.showMessage(QString::fromStdString(ss.str()));
				err.exec();
			}
			initialGUIState();
		});

		QObject::connect(remove, &QPushButton::clicked, [&]() {
			int size = carList->selectedItems().size();
			if (size > 0) {
				int id = carList->selectedItems().first()->data(Qt::UserRole).toInt();
				service.removeCar(2);
				initialGUIState();
			}
		});

		QObject::connect(carList, &QTableWidget::itemSelectionChanged, [&]() {
			int i = carList->currentRow();
			if (i < 0) {
				this->registrationNumber->clear();
				this->type->clear();
				this->manufacturer->clear();
				this->model->clear();
			}
			else {

				QString str = carList->item(i, 0)->text();
				registrationNumber->setText(str);

				str = carList->item(i, 1)->text();
				manufacturer->setText(str);

				str = carList->item(i, 2)->text();
				model->setText(str);

				str = carList->item(i, 3)->text();
				type->setText(str);

				/*item = carList->itemAt(i, 1);
				manufacturer->setText(item->data(Qt::UserRole).toString());
				item = carList->itemAt(i, 2);
				model->setText(item->data(Qt::UserRole).toString());
				item = carList->itemAt(i, 3);
				type->setText(item->data(Qt::UserRole).toString());
				item = carList->itemAt(i, 4);*/
				//QString rn = item->data(Qt::UserRole).toString();
				//this->registrationNumber->setText(rn);
			}
		});

		QObject::connect(update, &QPushButton::clicked, [&]() {
			try {
				std::string rn = this->registrationNumber->text().toStdString();
				std::string type = this->type->text().toStdString();
				std::string manufacturer = this->manufacturer->text().toStdString();
				std::string model = this->model->text().toStdString();

				service.modifyCar(rn, manufacturer, model, type);
				initialGUIState();
				this->registrationNumber->clear();
				this->type->clear();
				this->manufacturer->clear();
				this->model->clear();
			}
			catch (ValidateException& ve) {
				QErrorMessage err(this);
				std::stringstream ss{};
				ss << ve;
				err.showMessage(QString::fromStdString(ss.str()));
				err.exec();
			}
			catch (RepoException& ve) {
				QErrorMessage err(this);
				std::stringstream ss{};
				ss << ve;
				err.showMessage(QString::fromStdString(ss.str()));
				err.exec();
			}
		});

		QObject::connect(add, &QPushButton::clicked, [&]() {
			try {
				std::string rn = this->registrationNumber->text().toStdString();
				std::string type = this->type->text().toStdString();
				std::string manufacturer = this->manufacturer->text().toStdString();
				std::string model = this->model->text().toStdString();

				service.addCar(rn, manufacturer, model, type);
				initialGUIState();
				this->registrationNumber->clear();
				this->type->clear();
				this->manufacturer->clear();
				this->model->clear();
			}
			catch (ValidateException& ve) {
				QErrorMessage err(this);
				std::stringstream ss{};
				ss << ve;
				err.showMessage(QString::fromStdString(ss.str()));
				err.exec();
			}
			catch (RepoException& ve) {
				QErrorMessage err(this);
				std::stringstream ss{};
				ss << ve;
				err.showMessage(QString::fromStdString(ss.str()));
				err.exec();
			}
		});
		/*QObject::connect(add_button, &QPushButton::clicked, [&]() {
			add_window->show();
			add_window->show();
		});
		QObject::connect(ok_button, &QPushButton::clicked, [&]() {
			try {
				std::string aux_title = title->text().toStdString();
				std::string aux_type = type->text().toStdString();
				std::string aux_actor = actor->text().toStdString();
				int aux_year = year->text().toInt();
				film_service.addFilm(aux_title, aux_type, aux_actor, aux_year);
				initialGUIState();
				title->clear();
				type->clear();
				actor->clear();
				year->clear();
				add_window->hide();
			}
			catch (ValidateException& ve) {
				add_window->hide();
				QErrorMessage err(this);
				std::stringstream ss{};
				ss << ve;
				err.showMessage(QString::fromStdString(ss.str()));
				err.exec();
			}
		});

		QObject::connect(undo_button, &QPushButton::clicked, [&]() {
			try {
				film_service.undo();
				initialGUIState();
			}
			catch (ValidateException& ve) {
				QErrorMessage err(this);
				std::stringstream ss{};
				ss << ve;
				err.showMessage(QString::fromStdString(ss.str()));
				err.exec();
			}
		});

		QObject::connect(remove_button, &QPushButton::clicked, [&]() {
			if (film_list->selectedItems().size() > 0) {
				int film_id = film_list->selectedItems().first()->data(Qt::UserRole).toInt();
				film_service.removeFilm(film_id);
				initialGUIState();
			}
		});*/
	}
};

