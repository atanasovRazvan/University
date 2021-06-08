#pragma once
#include <QPropertyAnimation>
#include <QFont>
#include <QtWidgets/QGraphicsTextItem>
#include <QtWidgets/QGraphicsDropShadowEffect>
#include <QtWidgets/QGraphicsSceneMouseEvent>
#include <QString>
#include <QDebug>
#include <stdlib.h>

class MyTextItem :public QGraphicsTextItem {
	Q_OBJECT//e nevoie pentru a putea declara sloturi
	int value;
	QString label;
	QGraphicsDropShadowEffect* effect;
public slots:
	void setValue(int val) {
		value = val;
		setPlainText(label + QString::number(value));
		QPropertyAnimation *animation = pickRandomAnimation();
		animation->setDuration(1000);
		animation->start(QAbstractAnimation::DeleteWhenStopped);
	}

public:
	MyTextItem(QString label, int value) :value{ value }, label{ label } {
		setPlainText(label+QString::number(value));
		auto font = QFont{ "times" };
		font.setBold(true);
		setFont(font);
		setDefaultTextColor(Qt::black);
		effect = new QGraphicsDropShadowEffect();
		effect->setBlurRadius(40);
		setGraphicsEffect(effect);
	}

	void addValue(int val) {
		value += val;
		setPlainText(label + QString::number(value));
		QPropertyAnimation *animation = pickRandomAnimation();
		animation->setDuration(1000);
		animation->start(QAbstractAnimation::DeleteWhenStopped);
	}

	QPropertyAnimation * pickRandomAnimation() {
		QPropertyAnimation *animation;
		int rnd = rand() % 4;		
		switch (rnd) {
		case 0:
			animation = new QPropertyAnimation(this, "scale");			
			animation->setStartValue(1.3);
			animation->setEndValue(1.0);
			return animation;
		case 1:
			animation = new QPropertyAnimation(this, "rotation");			
			animation->setStartValue(0);
			animation->setEndValue(360);
			return animation;		
		case 2:
			animation = new QPropertyAnimation(this, "opacity");			
			animation->setStartValue(0.4);
			animation->setEndValue(1.0);
			return animation;
		case 3:
			animation = new QPropertyAnimation(this, "x");
			animation->setStartValue(x()+30);
			animation->setEndValue(x());
			return animation;
		};
	}
};