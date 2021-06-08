#pragma once
#include <QtWidgets/QGraphicsEllipseItem>
#include <QtWidgets/QGraphicsDropShadowEffect>
#include <QtWidgets/QGraphicsSceneMouseEvent>
#include <qtimer.h>
class Ball :public QGraphicsEllipseItem {
private:
	QGraphicsDropShadowEffect * effect;
	int dx = 5;
	int dy = -5;
public:
	Ball() {

		setRect(0, 0, 20, 20);
		setBrush(QBrush(QImage("ball.jpg")));

		effect = new QGraphicsDropShadowEffect();
		effect->setBlurRadius(30);
		setGraphicsEffect(effect);
			
	}

	void move() {
		setPos(x() + dx, y() + dy);		
		setTransformOriginPoint(rect().width() / 2, rect().height() / 2);
		setRotation(rotation() + 45);
	}
	void changeXDir() {
		dx *= -1;
	}
	void changeYDir() {
		dy *= -1;		
	}
	void moveUpwards() {
		if (dy > 0) {
			dy = -dy;
		}
	}
	//make some animation on hit
	void hit() {
		QPropertyAnimation *animation = new QPropertyAnimation(effect, "color");
		animation->setDuration(5000);
		animation->setStartValue(QColor(Qt::red));
		animation->setEndValue(QColor(Qt::black));
		animation->start(QAbstractAnimation::DeleteWhenStopped);
	}
	~Ball() {
		delete effect;
	}
};