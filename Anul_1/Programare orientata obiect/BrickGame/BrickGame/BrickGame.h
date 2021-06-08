#pragma once

#include <QtWidgets/QGraphicsScene>
#include <QtWidgets/QGraphicsView>
#include <QtWidgets/QMessageBox>
#include "padle.h"
#include "brick.h"
#include "ball.h"
#include "MyText.h"
#include <vector>
#include "gameengine.h"

class BrickGame:public QGraphicsView {
	BrickGameEngine& engine;
	QGraphicsScene*  scene;
	
	MyTextItem* hitText;
	MyTextItem* deadText;

	Paddle* player;
	std::vector<Ball*> balls;
	QGraphicsRectItem* leftWall;
	QGraphicsRectItem* rightWall;
	QGraphicsRectItem* topWall;
	QGraphicsRectItem* bottomWall;

	void createText() {
		hitText = new MyTextItem{"Score:",0};
		hitText->setPos(20, height() - 40);

		deadText = new MyTextItem{ "Dead:",0 };
		deadText->setPos(width() -120, height() - 40);

		scene->addItem(hitText);
		scene->addItem(deadText);

		QGraphicsTextItem* copyR = new QGraphicsTextItem{"Copyrights reserved"};
		copyR->setPos(width()/2-50, height() - 40);
		scene->addItem(copyR);
	}

	void initEnclosingWals() {
		int wallTickness = 8;
		leftWall = new QGraphicsRectItem( 0,0,wallTickness,height());
		auto br = QBrush(QImage("ball.jpg"));
		leftWall->setBrush(br);
		scene->addItem(leftWall);
		rightWall = new QGraphicsRectItem( width()- wallTickness-1, 0, wallTickness,height() );
		rightWall->setBrush(br);
		scene->addItem(rightWall);
		topWall = new QGraphicsRectItem( 0,0,width(), wallTickness);
		topWall->setBrush(br);
		scene->addItem(topWall);
		bottomWall = new QGraphicsRectItem(0,height()-50,width(), wallTickness);
		bottomWall->setBrush(br);
		scene->addItem(bottomWall);

	}
	void createPlayer() {
		player = new Paddle;
		player->setRect(0,0, 90, 20);
		player->setPos(width() / 2, height() - 100);
		scene->addItem(player);
	}

	void addBrick(int x, int y, int brickW, int brickH) {
		Brick* brick = new Brick{ brickW, brickH };
		brick->setPos(x, y);
		scene->addItem(brick);
	}

	void addBall() {
		Ball* ball = new Ball;
		ball->setPos(width() / 2, height() - 150);
		scene->addItem(ball);		
		balls.push_back(ball);
	}


	void initSignalSlots() {
		QObject::connect(&engine, &BrickGameEngine::scoreChanged, [&](int score) {
			hitText->setValue(score);
		});		
		QObject::connect(&engine, &BrickGameEngine::deadChanged, deadText,&MyTextItem::setValue);	

		//advanceGame invoked every time  
		QObject::connect(&engine, &BrickGameEngine::advanceBoard, this, &BrickGame::advanceGame);

		QObject::connect(&engine, &BrickGameEngine::brickCreated, [&](int x, int y, int brickW, int brickH) {
			addBrick(x,  y,  brickW,  brickH);
		});

		QObject::connect(&engine, &BrickGameEngine::gameFinished, [&](bool win) {
			if (win) {
				QMessageBox::information(this, "Info","You win!!!");
			}
			else {
				QMessageBox::information(this, "Info", "You lose!!!");
			}			
		});
	}

	//handle mouse move
	void mouseMoveEvent(QMouseEvent* ev) override{
		//works only if setMouseTracking(true);
		auto x = ev->pos().x();
		player->setPos(x, player->y());		
	}

	void keyPressEvent(QKeyEvent* ev) override {
		if (ev->key() == Qt::Key_Left) {
			player->moveX(-15);
		}
		else if (ev->key() == Qt::Key_Right) {
			player->moveX(15);
		}
		else if (ev->key() == Qt::Key_Space) {
			Ball* ball = new Ball;
			ball->setPos(width() / 2, height() - 150);
			scene->addItem(ball);
			balls.push_back(ball);
		}

	}

	void advanceGame() {	
		for(auto ball:balls){
			ball->move();				
			auto collides = ball->collidingItems();
			if (collides.isEmpty()) {
				continue;
			}		
			ball->hit();
			for (auto el : collides) {
				if (el == leftWall || el== rightWall) {
					ball->changeXDir();
				}
				else if (el == bottomWall) {
					ball->changeYDir();
					engine.bottomHit();
				}
				else if (el == topWall) {
					ball->changeYDir();
				}
				else if (el == player) {
					ball->moveUpwards();
					player->hit();
				} else if (el->type() == ball->type()) {
					//se loveste minge de minge
					ball->changeYDir();										
				}
				else {
					//e brick
					auto intersect = ball->sceneBoundingRect().intersected(el->sceneBoundingRect());					
					if (intersect.width() == intersect.height()) {
						ball->changeYDir();
						ball->changeXDir();
					}
					else if (intersect.width() > intersect.height()) {
						ball->changeYDir();
					}
					else {
						ball->changeXDir();
					}
					engine.brickHit();
					scene->removeItem(el);
				}				
			}
		}
	}
	void initScene() {
		scene = new QGraphicsScene;
		setScene(scene);
		
		setHorizontalScrollBarPolicy(Qt::ScrollBarAlwaysOff);
		setVerticalScrollBarPolicy(Qt::ScrollBarAlwaysOff);
		setFixedSize(800, 600);
		scene->setSceneRect(0, 0, 800, 600);
		setBackgroundBrush(QBrush(QImage("bg1.jpg")));
	
	}
public:
	BrickGame(BrickGameEngine& engine) :engine{ engine } {
		setMouseTracking(true);
		initScene();
		initEnclosingWals();
		createPlayer();		
		createText();
		addBall();
		initSignalSlots();
	}

};