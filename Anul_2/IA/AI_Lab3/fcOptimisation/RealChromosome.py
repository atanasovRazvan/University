'''
Created on Mar 25, 2020

@author: Razvan
'''

from random import randint
from random import shuffle
from fcOptimisation.utils import generateNewValue
from networkx.algorithms import swap
from copy import deepcopy

class Chromosome:
    def __init__(self, problParam = None):
        self.__problParam = problParam
        self.__repres = [generateNewValue(problParam['min'], problParam['max']) for _ in range(problParam['noNodes'])]
        self.__fitness = 0.0
    
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
        
        #=======================================================================
        # #incrucisare cu 2 puncte de taietura
        # r1 = randint(0, len(self.__repres) - 1)
        # r2 = randint(0, len(self.__repres) - 1)
        # if r1 > r2:
        #     aux = r1
        #     r1 = r2
        #     r2 = aux
        # newrepres = []
        # for i in range(r1):
        #     newrepres.append(self.__repres[i])
        # for i in range(r1, r2):
        #     newrepres.append(c.__repres[i])
        # for i in range(r2, len(self.__repres)):
        #     newrepres.append(self.__repres[i])
        # offspring = Chromosome(c.__problParam)
        # offspring.repres = newrepres
        # return offspring
        #=======================================================================
        
        #incrucisare uniforma
        newrepres = []
        for i in range(self.__problParam['noNodes']):
            r1 = randint(1, 101)
            if r1 <= 50:
                newrepres.append(self.__repres[i])
            else:
                newrepres.append(c.__repres[i])
        offspring = Chromosome(c.__problParam)
        offspring.repres = newrepres
        return offspring
    
    def mutation(self):
        #swap mutation
        pos1 = randint(0, len(self.__repres) - 1)
        pos2 = randint(0, len(self.__repres) - 1)
        aux = self.__repres[pos1]
        self.__repres[pos1] = self.__repres[pos2]
        self.__repres[pos2] = aux
        
        #=======================================================================
        # #insertion mutation
        # pos1 = randint(0, len(self.__repres) - 1)
        # pos2 = randint(0, len(self.__repres) - 1)
        # if pos1>pos2:
        #     aux = pos1 
        #     pos1 = pos2
        #     pos2 = aux
        # self.__repres.insert(pos1+1, self.__repres[pos2])
        # self.__repres.pop(len(self.__repres)-1)
        #=======================================================================
        
        #=======================================================================
        # #inversion mutation
        # pos1 = randint(0, len(self.__repres) - 1)
        # pos2 = randint(0, len(self.__repres) - 1)
        # self.__repres[pos1:pos2] = self.__repres[pos2:pos1:-1]
        #=======================================================================
        
    def __str__(self):
        return '\nChromo: ' + str(self.__repres) + ' has fit: ' + str(self.__fitness)
    
    def __repr__(self):
        return self.__str__()
    
    def __eq__(self, c):
        return self.__repres == c.__repres and self.__fitness == c.__fitness
    
    
    