#pragma once
#include <QObject>
#include <QDebug>
#include <QTimer>
/*
Controleaza jocul: scor, viteza mingilor,  cant se termina, etc
Emite semnale pentru a notifica evenimente de interes
*/
class BrickGameEngine: public QObject{
	Q_OBJECT  //e nevoie de acest macro daca vrem semnale custom
	int score = 0;
	int dead = 0;
	int nrBricks = 0;
	QTimer timer;
	int ballMoveDelay = 20;
	int elapsedMoves = 0;
signals:
	//semnale generate de engine
	void scoreChanged(int currentScore);
	void deadChanged(int currentNrDead);
	void advanceBoard();
	void brickCreated(int x, int y, int brickW, int brickH);
	void gameFinished(bool win);

public:
	BrickGameEngine() {
		emit scoreChanged(score);
		emit deadChanged(dead);
	}

	void brickHit() {		
		score += 1;
		nrBricks--;
		emit scoreChanged(score);
	}

	void bottomHit() {
		dead += 1;
		emit deadChanged(dead);
	}

	void loadLevel() {
		int brickW = 70;
		int brickH = 25;
		for (int y = 30; y < 150; y += brickH + 10) {
			for (int x = 40; x < 800 - 40; x += brickW + 10) {
				emit brickCreated(x, y, brickW, brickH);
				nrBricks++;
			}
		}
	}

	bool isGameFinished() {
		return nrBricks == 0 || dead > 15;
	}

	void startGame() {
		loadLevel();
		QObject::connect(&timer, &QTimer::timeout, [&]() {
			emit advanceBoard();
			elapsedMoves++;
			if (elapsedMoves > 1000) {
				//marim viteza
				elapsedMoves =0;
				ballMoveDelay -= 1;
				timer.setInterval(ballMoveDelay);
			}
			if (isGameFinished()) {
				timer.stop();
				emit gameFinished(nrBricks == 0);
			}
		});		
		//generate timeot signal every 20ms
		timer.start(ballMoveDelay);
	}
};