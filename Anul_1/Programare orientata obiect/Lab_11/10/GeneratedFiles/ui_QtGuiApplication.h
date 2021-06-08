/********************************************************************************
** Form generated from reading UI file 'QtGuiApplication.ui'
**
** Created by: Qt User Interface Compiler version 5.12.3
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_QTGUIAPPLICATION_H
#define UI_QTGUIAPPLICATION_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_QtGuiApplicationClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *QtGuiApplicationClass)
    {
        if (QtGuiApplicationClass->objectName().isEmpty())
            QtGuiApplicationClass->setObjectName(QString::fromUtf8("QtGuiApplicationClass"));
        QtGuiApplicationClass->resize(600, 400);
        menuBar = new QMenuBar(QtGuiApplicationClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        QtGuiApplicationClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(QtGuiApplicationClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        QtGuiApplicationClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(QtGuiApplicationClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        QtGuiApplicationClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(QtGuiApplicationClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        QtGuiApplicationClass->setStatusBar(statusBar);

        retranslateUi(QtGuiApplicationClass);

        QMetaObject::connectSlotsByName(QtGuiApplicationClass);
    } // setupUi

    void retranslateUi(QMainWindow *QtGuiApplicationClass)
    {
        QtGuiApplicationClass->setWindowTitle(QApplication::translate("QtGuiApplicationClass", "QtGuiApplication", nullptr));
    } // retranslateUi

};

namespace Ui {
    class QtGuiApplicationClass: public Ui_QtGuiApplicationClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_QTGUIAPPLICATION_H
