#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_QtCompAndLayout.h"

class QtCompAndLayout : public QMainWindow
{
	Q_OBJECT

public:
	QtCompAndLayout(QWidget *parent = Q_NULLPTR);

private:
	Ui::QtCompAndLayoutClass ui;
};
