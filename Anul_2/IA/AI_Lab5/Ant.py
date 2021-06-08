'''
Created on Apr 11, 2020

@author: Razvan
'''
from random import randint, uniform

class Ant:
    
    def __init__(self, params):
        self.__params = params
        self.__path = [randint(0, self.__params['noNodes']-1)]
        self.__visited = [False] * params['noNodes']
        self.__visited[self.__path[0]] = 1
        self.__inexistentPath = False
        
    def distance(self):
        
        if self.__inexistentPath:
            return 99999999
        dist = 0
        for i in range(len(self.__path)-1):
            dist += self.__params['mat'][self.__path[i]][self.__path[i+1]]
        dist += self.__params['mat'][self.__path[len(self.__path)-1]][self.__path[0]]
        return dist
    
    def next(self):
        
        q = uniform(0, 1)
        current_node = self.__path[len(self.__path) - 1]

        if q < self.__params["q0"]:
            sum = 0
            possibleNodes = []
            for i in range(0, self.__params['noNodes']):
                if self.__visited[i] == 0 and i != current_node and self.__params['mat'][current_node][i]:
                    sum += pow(self.__params['pheromone'][current_node][i], self.__params["alpha"]) * pow(1/self.__params['mat'][current_node][i], self.__params["beta"])

            for i in range(0, self.__params['noNodes']):
                if self.__visited[i] == 0 and i != current_node and self.__params['mat'][current_node][i]:
                    j = pow(self.__params['pheromone'][current_node][i], self.__params["alpha"]) * pow(1/self.__params['mat'][current_node][i], self.__params["beta"])
                    possibleNodes.append((j / sum, i))

            possibleNodes.sort()

            prob = uniform(0, 1)

            for i in range(len(possibleNodes) - 1):
                if possibleNodes[i][0] <= prob < possibleNodes[i + 1][0]:
                    return possibleNodes[i][1]

            if len(possibleNodes) == 0:
                raise Exception("")
            return possibleNodes[len(possibleNodes) - 1][1]
        
        else:
            possibleNodes = []
            for i in range(len(self.__params['pheromone'][current_node])):
                if i != current_node and self.__visited[i] == 0 and self.__params['mat'][current_node][i]:
                    j = pow(self.__params['pheromone'][current_node][i], self.__params["alpha"]) * pow(1/self.__params['mat'][current_node][i], self.__params["beta"])
                    possibleNodes.append((j, i))

            possibleNodes.sort()

            if len(possibleNodes) == 0:
                raise Exception("")
            return possibleNodes[0][1]
        
    def explore(self):
        try:
            next_node = self.next()
        except:
            self.__inexistentPath = True
            return

        current_node = self.__path[len(self.__path) - 1]
        self.__visited[next_node] = True
        self.__path.append(next_node)

        self.__params['pheromone'][current_node][next_node] = (1 - self.__params["oldPheromoneRate"]) * self.__params["pheromone"][current_node][next_node] + self.__params["oldPheromoneRate"]*self.__params['mat'][current_node][next_node]
        self.__params['pheromone'][next_node][current_node] = self.__params['pheromone'][current_node][next_node]

    def path(self):
        return self.__path
        
        