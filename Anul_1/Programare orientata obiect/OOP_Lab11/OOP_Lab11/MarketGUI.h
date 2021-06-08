#pragma once
#include <QtWidgets/qpushbutton.h>
#include <qlineedit.h>
#include <QtWidgets/qboxlayout.h>
#include <QtWidgets/qlistwidget.h>
#include <qerrormessage.h>
#include <qlabel.h>
#include <sstream>
#include "Service.h"
#include "ValidateException.h"
class MarketGUI : public QWidget {
public:
	MarketGUI(Service &market) : market{ market } {
		initializeGUIComponents();
		initialGUIState();
		connectSignals();
	};
	~MarketGUI() = default;
private:
	Service& market;
	QListWidget* list = new QListWidget;

	QLineEdit* registrationNumber = new QLineEdit;
	QLineEdit* manufacturer = new QLineEdit;
	QLineEdit* model = new QLineEdit;
	QLineEdit* type = new QLineEdit;
	QLineEdit* nrCarGenerated = new QLineEdit;

	QPushButton* add = new QPushButton{ "Add" };
	QPushButton* remove = new QPushButton{ "Remove all" };
	QPushButton* generate = new QPushButton{ "Generate" };

	void initializeGUIComponents() {
		list->setSelectionMode(QAbstractItemView::NoSelection);

		QHBoxLayout *main = new QHBoxLayout;
		setLayout(main);

		QVBoxLayout* leftSide = new QVBoxLayout;
		leftSide->addWidget(list);

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

		QHBoxLayout* operationLayout = new QHBoxLayout;
		operationLayout->addWidget(add);
		operationLayout->addWidget(remove);

		QHBoxLayout* generateLayout = new QHBoxLayout;
		generateLayout->addWidget(nrCarGenerated);
		generateLayout->addWidget(generate);


		rightSide->addLayout(inputLayout);
		rightSide->addLayout(operationLayout);
		rightSide->addLayout(generateLayout);

		main->addLayout(leftSide);
		main->addLayout(rightSide);
	}

	void initialGUIState() {
		list->clear();
		for (auto car : market.getMarket()) {
			QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(car.getAll()), list);
			item->setData(Qt::UserRole, QString::fromStdString(car.getRegistrationNumber()));
		}
	}

	void connectSignals() {
		QObject::connect(add, &QPushButton::clicked, [&]() {
			try {
				std::string rn = this->registrationNumber->text().toStdString();
				std::string type = this->type->text().toStdString();
				std::string manufacturer = this->manufacturer->text().toStdString();
				std::string model = this->model->text().toStdString();

				market.addCarMarket(rn, manufacturer, model, type);
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
			catch (MarketException& ve) {
				QErrorMessage err(this);
				std::stringstream ss{};
				ss << ve;
				err.showMessage(QString::fromStdString(ss.str()));
				err.exec();
			}
		});

		QObject::connect(remove, &QPushButton::clicked, [&]() {
			try {
				std::string rn = this->registrationNumber->text().toStdString();
				market.removeMarket();
				initialGUIState();
			}
			catch (MarketException& ve) {
				QErrorMessage err(this);
				std::stringstream ss{};
				ss << ve;
				err.showMessage(QString::fromStdString(ss.str()));
				err.exec();
			}
		});

		QObject::connect(generate, &QPushButton::clicked, [&]() {
			int n = this->nrCarGenerated->text().toInt();
			market.generateCarsMarket(n);
			initialGUIState();

			if (n <= 0) {
				QErrorMessage err(this);
				std::stringstream ss{};
				ss << "Introu o valoare corespunzatoare!";
				err.showMessage(QString::fromStdString(ss.str()));
				err.exec();
			}


		});
	}
};

