'''
Created on Apr 1, 2020

@author: Razvan
'''
from cmath import sqrt

#readfiles

def parser(filename):
    f = open(filename, "r")
    net = {}
    coords = []
    _ = f.readline()
    _ = f.readline()
    _ = f.readline()
    x = f.readline()
    
    if filename == "Data/hardE.txt":
        _, _, dim = x.split()
    else:
        _, dim = x.split()
    net['noNodes'] = int(dim)
    
    x = f.readline()
    x = f.readline()
    x = f.readline()
    
    while x != "EOF":
        _, coordx, coordy = x.split()
        coords.append([float(coordx), float(coordy)])
        x = f.readline()
    
    mat = []
    for i in range(net['noNodes']):
        mat.append([])
        for j in range(net['noNodes']):
            if i == j:
                mat[i].append(0)
                continue
            x1 = coords[i][0] 
            x2 = coords[j][0]
            y1 = coords[i][1]
            y2 = coords[j][1]
            mat[i].append( int( sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) ).real))
    
    net['noNodes'] = net['noNodes']-1
    net['mat'] = mat
    f.close()
    return net

def readFile(filename):
    if filename == "Data/berlin52.txt" or filename == "Data/hardE.txt":
        return parser(filename)
    f = open(filename, "r")
    net={}
    x = f.readline()
    net['noNodes'] = int(x)-1
    mat=[]
    for i in range(net['noNodes']+1):
        mat.append([])
        for j in f.readline().strip().split(","):
            mat[i].append(int(j))
    net['mat'] = mat
    f.close()
    return net

param = readFile("Data/mediumF.txt");
MIN = 0
MAX = param['noNodes']

#function
def function(lista):
    cost = 0
    prev = 0
    for el in lista:
        cost = cost + param['mat'][prev][el]
        prev = el;
    return cost + param['mat'][prev][0]

from RealChromosome import Chromosome
from random import randint

class GA:
    def __init__(self, param = None, problParam = None):
        self.__param = param
        self.__problParam = problParam
        self.__population = []
        
    @property
    def population(self):
        return self.__population
    
    def initialisation(self):
        for _ in range(0, self.__param['popSize']):
            c = Chromosome(self.__problParam, param['mat'])
            self.__population.append(c)
    
    def evaluation(self):
        for c in self.__population:
            c.fitness = self.__problParam['function'](c.repres)
            
    def bestChromosome(self):
        best = self.__population[0]
        for c in self.__population:
            if (c.fitness < best.fitness):
                best = c
        return best
        
    def worstChromosome(self):
        best = self.__population[0]
        for c in self.__population:
            if (c.fitness > best.fitness):
                best = c
        return best

    def selection(self):
        pos1 = randint(0, self.__param['popSize'] - 1)
        pos2 = randint(0, self.__param['popSize'] - 1)
        if (self.__population[pos1].fitness < self.__population[pos2].fitness):
            return pos1
        else:
            return pos2 
        
    
    def oneGeneration(self):
        newPop = []
        for _ in range(self.__param['popSize']):
            p1 = self.__population[self.selection()]
            p2 = self.__population[self.selection()]
            off = p1.crossover(p2)
            off.mutation()
            newPop.append(off)
        self.__population = newPop
        self.evaluation()

    def oneGenerationElitism(self):
        newPop = [self.bestChromosome()]
        for _ in range(self.__param['popSize'] - 1):
            p1 = self.__population[self.selection()]
            p2 = self.__population[self.selection()]
            off = p1.crossover(p2)
            off.mutation()
            newPop.append(off)
        self.__population = newPop
        self.evaluation()
        
    def oneGenerationSteadyState(self):
        for _ in range(self.__param['popSize']):
            p1 = self.__population[self.selection()]
            p2 = self.__population[self.selection()]
            off = p1.crossover(p2)
            off.mutation()
            off.fitness = self.__problParam['function'](off.repres)
            worst = self.worstChromosome()
            if off.fitness < worst.fitness:
                for i in range(self.__param['popSize']):
                    if self.__population[i] == worst:
                        self.__population[i] = off
                        break
                    
                    
from random import seed 

seed(9)

# initialise de GA parameters
gaParam = {'popSize' : 100, 'noGen' : 100}
# problem parameters
problParam = {'min' : MIN, 'max' : MAX, 'function' : function, 'noNodes' : MAX}

# store the best/average solution of each iteration (for a final plot used to anlyse the GA's convergence)

ga = GA(gaParam, problParam)
ga.initialisation()
ga.evaluation()

bestOfBestChromo = Chromosome(problParam, param['mat'])
bestOfBestChromo.fitness = 10000000
from collections import Counter
for g in range(gaParam['noGen']):
    #plotting preparation
    bestSolX = ga.bestChromosome().repres
    bestSolY = ga.bestChromosome().fitness
    #logic alg
    #ga.oneGenerationElitism()
    ga.oneGenerationSteadyState()
    
    bestChromo = ga.bestChromosome()
    if bestOfBestChromo.fitness > bestChromo.fitness:
        bestOfBestChromo = bestChromo

    print('Generation: ' + str(g) + '; Repres: ' + str(bestChromo.repres) + '; fitness = ' + str(bestChromo.fitness))

print('\n')
print("Best solution: " + str(bestOfBestChromo.repres))
print("Best fitness: " + str(bestOfBestChromo.fitness))

#BEST BERLIN 8253

