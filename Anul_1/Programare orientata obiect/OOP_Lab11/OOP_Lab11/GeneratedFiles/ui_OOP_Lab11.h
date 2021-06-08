/********************************************************************************
** Form generated from reading UI file 'OOP_Lab11.ui'
**
** Created by: Qt User Interface Compiler version 5.12.3
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_OOP_LAB11_H
#define UI_OOP_LAB11_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_OOP_Lab11Class
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *OOP_Lab11Class)
    {
        if (OOP_Lab11Class->objectName().isEmpty())
            OOP_Lab11Class->setObjectName(QString::fromUtf8("OOP_Lab11Class"));
        OOP_Lab11Class->resize(600, 400);
        menuBar = new QMenuBar(OOP_Lab11Class);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        OOP_Lab11Class->setMenuBar(menuBar);
        mainToolBar = new QToolBar(OOP_Lab11Class);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        OOP_Lab11Class->addToolBar(mainToolBar);
        centralWidget = new QWidget(OOP_Lab11Class);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        OOP_Lab11Class->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(OOP_Lab11Class);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        OOP_Lab11Class->setStatusBar(statusBar);

        retranslateUi(OOP_Lab11Class);

        QMetaObject::connectSlotsByName(OOP_Lab11Class);
    } // setupUi

    void retranslateUi(QMainWindow *OOP_Lab11Class)
    {
        OOP_Lab11Class->setWindowTitle(QApplication::translate("OOP_Lab11Class", "OOP_Lab11", nullptr));
    } // retranslateUi

};

namespace Ui {
    class OOP_Lab11Class: public Ui_OOP_Lab11Class {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_OOP_LAB11_H
