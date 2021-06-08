#include <QAbstractTableModel>
#include <QFont>
#include <QBrush>
#include <vector>
#include "Locatar.h"
#pragma once

class Model : public QAbstractTableModel {
	Q_OBJECT;
	std::vector<Locatar> l;
	std::vector<int> sortat;
	std::map<int, QColor> map;
public:
	Model(const std::vector<Locatar>& v, QObject* parent = nullptr);
	int rowCount(const QModelIndex& parent = QModelIndex()) const override;
	int columnCount(const QModelIndex& parent = QModelIndex()) const override;
	QVariant data(const QModelIndex &index, int role = Qt::DisplayRole) const override;
	void timerTikTak(); 
};