'''
Created on Apr 11, 2020

@author: Razvan
'''

from random import randint

from Ant import Ant


class ACO:
    def __init__(self, params, probParams):
        self.__params = params
        self.__probParams = probParams
        self.__ants = []

    def initialize(self):
        self.__ants = []
        for _ in range(self.__probParams["noAnts"]):
            self.__ants.append(Ant(self.__params))

    def initializePheromone(self):
        for i in range(self.__params['noNodes']):
            v = [1] * self.__params['noNodes']
            self.__params['pheromone'].append(v)

    def bestAnt(self):
        bestAnt = self.__ants[0]
        for ant in self.__ants:
            if ant.distance() < bestAnt.distance():
                bestAnt = ant
        return bestAnt

    def placePheromone(self, ant):
        path = ant.path()
        for i in range(len(path) - 1):
            x = path[i]
            y = path[i + 1]
            self.__params['pheromone'][x][y] = self.__params['oldPheromoneRate'] * self.__params['pheromone'][x][y] + self.__params['oldPheromoneRate'] * (1/ant.distance())
            self.__params['pheromone'][y][x] = self.__params['pheromone'][x][y]
            
    def explore(self):
        for _ in range(self.__params['noNodes'] - 1):
            for ant in self.__ants:
                ant.explore()
        self.placePheromone(self.bestAnt())


    def deleteEdge(self):
        x = randint(0, self.__params['noNodes'] - 1)
        y = randint(0, self.__params['noNodes'] - 1)

        while x == y and self.__params['mat'][x][y] == 0:
            y = randint(0, self.__params['noNodes'] - 1)

        print ("Graf dinamic: muchia " + str(x) + " - " + str(y) + " stearsa.")

        self.__params['mat'][x][y] = 0
        self.__params['mat'][y][x] = 0

        self.__params['pheromone'][x][y] = 0
        self.__params['pheromone'][y][x] = 0
        
        