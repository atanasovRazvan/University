#include <QtWidgets/QApplication>
#include <QtWidgets/QGraphicsScene>
#include <QtWidgets/QGraphicsView>
#include <QtWidgets/QGraphicsRectItem>
#include <QtWidgets/QGraphicsDropShadowEffect>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/qpushbutton.h>
#include <stdlib.h>
#include "brickgame.h"
#include "gameengine.h"

class SampleW :public QWidget {
	int x = 0;
public:
	SampleW() {
		//If mouse tracking is disabled (the default)
		//the widget only receives mouse move events when at least one mouse button is pressed while the mouse is being moved
		setMouseTracking(true);
		QVBoxLayout* ly = new QVBoxLayout;
		setLayout(ly);
		ly->addWidget(new QLineEdit);
	}
protected:
	void paintEvent(QPaintEvent* ev) override {		
		QPainter p{ this };

		p.drawLine(0, 0, width(), height());
		p.drawImage(x,0,QImage("sky.jpg"));

		p.setPen(Qt::blue);
		p.setFont(QFont("Arial", 30));
		p.drawText(rect(), Qt::AlignTop | Qt::AlignHCenter, "Qt QPainter");

		p.fillRect(0, 100, 100, 100,Qt::BrushStyle::Dense1Pattern);

	}
	void mouseMoveEvent(QMouseEvent* ev) override {
		qDebug() << ev->pos();
		x = ev->pos().x();
		repaint();
	}
};

void sampleGraphicsFramework() {
	
	QGraphicsScene* scene = new QGraphicsScene;
	scene->setSceneRect(0, 0, 800, 600);


	QGraphicsView* view = new QGraphicsView;
	view->setScene(scene);
	view->setFixedSize(800, 600);
	

	//add items to scene
	QGraphicsEllipseItem* ball = new QGraphicsEllipseItem{ 0,0,20,20 };
	ball->setPos(scene->width() / 2, scene->height() / 2);
	scene->addItem(ball);

	QGraphicsRectItem* r = new QGraphicsRectItem{ 0,0,40,30 };
	r->setPos(20, scene->height() / 2);
	r->setBrush(QBrush(QImage("wood3.jpg")));
	scene->addItem(r);

	for (int i = 0; i < 10; i++) {
		int x = rand() % 800;
		int y = rand() % 600;
		QGraphicsRectItem* r = new QGraphicsRectItem{ 0,0,40,30 };
		r->setPos(x,y);
		r->setBrush(QBrush(QImage("wood1.jpg")));

		scene->addItem(r);
	}
	view->show();

	QTimer* timer = new QTimer;
	QObject::connect(timer, &QTimer::timeout, [=]() {
		ball->setPos(ball->x() + 10, ball->y()+10);
		if (!ball->collidingItems().isEmpty()) {
			qDebug() << "collide";
		}
	});
	timer->start(300);
	
	


	QGraphicsView* view2 = new QGraphicsView;
	view2->setScene(scene);
	view2->setFixedSize(400, 300);
	view2->show();
	
}

class MyWitget :public QWidget {
	int x = 0;
	int y = 0;
public:
	MyWitget() {
		QVBoxLayout* ly = new QVBoxLayout;
		setLayout(ly);
		ly->addWidget(new QLineEdit);
		setMouseTracking(true);
		
		QTimer* timer = new QTimer;
		QObject::connect(timer, &QTimer::timeout, [&]() {
			incY(); 
		});
		timer->start(500);
		
	}
protected:
	void keyPressEvent(QKeyEvent* ev) override {
		if (ev->key() == Qt::Key_Left) {
			qDebug() << "apasat left arrow";
		}
		qDebug() << "apasat ceva buton";
	}

	void incY() {
		y = y + 5;
		repaint();
	}

	void paintEvent(QPaintEvent* ev) override {
		qDebug() << "paintEvent";
		QPainter p{ this };

		p.drawLine(0, 0, width(), height());
		p.drawImage(x,y, QImage("sky.jpg"));

		p.setPen(Qt::blue);
		p.setFont(QFont("Arial", 30));
		p.drawText(rect(), Qt::AlignTop | Qt::AlignHCenter, "Qt QPainter");

		p.fillRect(0, 100, 100, 100, Qt::BrushStyle::Dense1Pattern);
	}
	void mouseMoveEvent(QMouseEvent* ev) override{
		qDebug()<<"mouse x:" << ev->pos().x();
		x = ev->pos().x();
		repaint();
	}
};

class MYCanvas: public QWidget{
public:
	MYCanvas() {
		QVBoxLayout* ly = new QVBoxLayout;
		setLayout(ly);
		ly->addWidget(new QLineEdit);
		ly->addWidget(new QPushButton{"asdasd"});
	}
	void paintEvent(QPaintEvent* ev) override {
		QPainter p{ this };
		p.drawLine(0, 0, width(), height());
		p.drawImage(10, 100, QImage("sky.jpg"));
	}
};

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	
	
	MyWitget w;
	w.show();
	

	//SampleW s;
	//s.show();

    //sampleGraphicsFramework();

	
	
	BrickGameEngine engine;
	BrickGame* view = new BrickGame{ engine };
	view->show();

	engine.startGame();
	
	return a.exec();
	
}
