#include "Model.h"

Model::Model(const std::vector<Locatar>& v, QObject * parent) :l{ v }, QAbstractTableModel(parent)
{
	for (auto&el : l) {
		sortat.push_back(el.get_apartament());
	}

	std::sort(sortat.begin(), sortat.end());

	int i{ 0 };
	for (auto&x : sortat) {
		if (i < sortat.size() / 3) {
			map[x] = Qt::red;
		}
		else if (i < 2 * sortat.size() / 3) {
			map[x] = Qt::blue;
		}
		else {
			map[x] = Qt::green;
		}
		i++;
	}
}

int Model::rowCount(const QModelIndex & parent) const
{
	return l.size();
}

int Model::columnCount(const QModelIndex & parent) const
{
	return 4;
}

QVariant Model::data(const QModelIndex & index, int role) const
{
	int row = index.row();
	int column = index.column();
	if (role == Qt::DisplayRole) {
		//return QString("Row%1, Column%2").arg(row + 1).arg(column + 1);
		if (column == 0) {
			return QString::number(l.at(row).get_apartament());
		}
		else if (column == 1) {
			return QString::fromStdString(l.at(row).get_nume());
		}
		else if (column == 2) {
			return QString::fromStdString(l.at(row).get_tip());
		}
		else if (column == 3) {
			return QString::number(l.at(row).get_suprafata());
		}
	}
	if (role == Qt::FontRole) {
		QFont f;
		//f.setItalic(row % 4 == 1);
		//f.setBold(row % 2 == 1);
		return f;
	}
	if (role == Qt::BackgroundRole) {
		/*if (column == 1 && row % 2 == 0) {
			QBrush bg(Qt::red);
			return bg;
		}*/
		QBrush bg(map.at(l.at(row).get_apartament()));
		return bg;
	}
	return QVariant();
}

void Model::timerTikTak()
{
	QModelIndex topLeft = createIndex(0, 0);
	QModelIndex bottomRight = createIndex(rowCount(), columnCount());
	emit dataChanged(topLeft, bottomRight);
}
