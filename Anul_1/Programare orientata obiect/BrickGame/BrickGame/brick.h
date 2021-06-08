#pragma once
#include <QtWidgets/QGraphicsRectItem>
#include <QtWidgets/QGraphicsDropShadowEffect>
#include <QtWidgets/QGraphicsSceneMouseEvent>
#include <stdlib.h>
class Brick :public QGraphicsRectItem {

public:
	Brick(int w, int h) {
		setRect(0, 0, w, h);
		
		setBrush(QBrush(QImage("wood"+QString::number(rand()%3+1) +".jpg")));

		QGraphicsDropShadowEffect * effect = new QGraphicsDropShadowEffect();
		effect->setBlurRadius(30);
		setGraphicsEffect(effect);
		
	}
};