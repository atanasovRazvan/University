'''
Created on Apr 1, 2020

@author: Razvan
'''

from random import randint, seed


def generateInitialState(n, mat):
    visited = [0] * (n+1)
    visited[0] = 1
    current = 0
    solution = []
    
    while True:
        minimum = 999999
        pos = 0
        for i in range(n+1):
            if minimum > mat[current][i] and visited[i] == 0:
                minimum = mat[current][i]
                pos = i
        solution.append(pos)
        visited[pos] = 1
        current = pos
        if 0 not in visited:
            break
    return solution
    

# permutation-based representation
class Chromosome:
    def __init__(self, problParam = None, mat = None):
        self.__problParam = problParam  #problParam has to store the number of nodes/cities
        self.__repres = generateInitialState(self.__problParam['noNodes'], mat)
        self.__fitness = 0.0
        self.__mat = mat
    
    @property
    def repres(self):
        return self.__repres 
    
    @property
    def fitness(self):
        return self.__fitness 
    
    @repres.setter
    def repres(self, l = []):
        self.__repres = l 
    
    @fitness.setter 
    def fitness(self, fit = 0.0):
        self.__fitness = fit 
    
    def crossover(self, c):
        # order XO
        pos1 = randint(-1, self.__problParam['noNodes'] - 1)
        pos2 = randint(-1, self.__problParam['noNodes'] - 1)
        if (pos2 < pos1):
            pos1, pos2 = pos2, pos1 
        k = 0
        newrepres = self.__repres[pos1 : pos2]
        for el in c.__repres[pos2:] +c.__repres[:pos2]:
            if (el not in newrepres):
                if (len(newrepres) < self.__problParam['noNodes'] - pos1):
                    newrepres.append(el)
                else:
                    newrepres.insert(k, el)
                    k += 1

        offspring = Chromosome(self.__problParam, self.__mat)
        offspring.repres = newrepres
        return offspring
    
    def mutation(self):
        # insert mutation
        pos1 = randint(0, self.__problParam['noNodes'] - 1)
        pos2 = randint(0, self.__problParam['noNodes'] - 1)
        if (pos2 < pos1):
            pos1, pos2 = pos2, pos1
        el = self.__repres[pos2]
        del self.__repres[pos2]
        self.__repres.insert(pos1 + 1, el)
        
    def __str__(self):
        return "\nChromo: " + str(self.__repres) + " has fit: " + str(self.__fitness)
    
    def __repr__(self):
        return self.__str__()
    
    def __eq__(self, c):
        return self.__repres == c.__repres and self.__fitness == c.__fitness