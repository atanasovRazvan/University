#pragma once
#include <QtWidgets/QGraphicsRectItem>
#include <QtWidgets/QGraphicsDropShadowEffect>
#include <QtWidgets/QGraphicsSceneMouseEvent>
#include <QPropertyAnimation>
#include <QKeyEvent>
#include <qdebug.h>
#include <stdio.h>
#include <QTimer>
class Paddle :public QGraphicsRectItem {
	QGraphicsDropShadowEffect * effect;
public:
	Paddle() {		
		setBrush(QBrush(QImage("player.jpg")));
		effect = new QGraphicsDropShadowEffect();
		effect->setBlurRadius(30);		
		setGraphicsEffect(effect);
	}

	void moveX(int dx) {
		setPos(x() +dx, y());
	}

	void hit() {
		QPropertyAnimation *animation = new QPropertyAnimation(effect, "color");
		animation->setDuration(5000);
		animation->setStartValue(QColor(Qt::red));
		animation->setEndValue(QColor(Qt::black));
		animation->start();
	}

};