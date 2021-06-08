'''
Created on Mar 26, 2020

@author: Razvan
'''

# define the function
def readFile(fileName):
    f = open(fileName, "r")
    net = {}
    n = int(f.readline())
    net['noNodes'] = n
    mat = []
    for i in range(n):
        mat.append([])
        line = f.readline()
        elems = line.split(" ")
        for j in range(n):
            mat[-1].append(int(elems[j]))
    net["mat"] = mat 
    degrees = []
    noEdges = 0
    for i in range(n):
        d = 0
        for j in range(n):
            if (mat[i][j] == 1):
                d += 1
            if (j > i):
                noEdges += mat[i][j]
        degrees.append(d)
    net["noEdges"] = noEdges
    net["degrees"] = degrees
    f.close()
    return net

import networkx as nx

def readGmlFile(fileName):
    
    if fileName == "krebs.gml":
        graph = nx.read_gml(fileName, label='id')
    else:
        graph = nx.read_gml(fileName)
    
    net = {}  
    net['noNodes'] = graph.number_of_nodes()
    net['noEdges'] = graph.number_of_edges()
    
    mat = []
    mat = nx.to_numpy_matrix(graph)
    
    net["mat"] = mat 
    degrees = []
    noEdges = 0
    
    for i in range(net['noNodes']):
        d = 0
        for j in range(net['noNodes']):
            if (mat.item((i,j)) == 1):
                d += 1
            if (j > i):
                noEdges += mat.item((i,j))
        degrees.append(d)
    net["degrees"] = degrees
    return net  
    
    

param = readGmlFile("lesmis.gml")

MIN = 0
MAX = param['noNodes']

def modularity2(communities): 
    noNodes = param['noNodes']
    mat = param['mat']
    degrees = param['degrees']
    noEdges = param['noEdges']  
    M = 2 * noEdges
    Q = 0.0
    for i in range(0, noNodes):
        for j in range(0, noNodes):
            if (communities[i] == communities[j]):
               Q += (mat[i][j] - degrees[i] * degrees[j] / M)
    return Q * 1 / M

def modularity(communities): 
    noNodes = param['noNodes']
    mat = param['mat']
    degrees = param['degrees']
    noEdges = param['noEdges']  
    M = 2 * noEdges
    Q = 0.0
    for i in range(0, noNodes):
        for j in range(0, noNodes):
            if (communities[i] == communities[j]):
               Q += (mat.item((i,j)) - degrees[i] * degrees[j] / M)
    return Q * 1 / M

from fcOptimisation.RealChromosome import Chromosome
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
            c = Chromosome(self.__problParam)
            self.__population.append(c)
    
    def evaluation(self):
        for c in self.__population:
            c.fitness = self.__problParam['function'](c.repres)
            
    def bestChromosome(self):
        best = self.__population[0]
        for c in self.__population:
            if (c.fitness > best.fitness):
                best = c
        return best
        
    def worstChromosome(self):
        best = self.__population[0]
        for c in self.__population:
            if (c.fitness < best.fitness):
                best = c
        return best

    def selection(self):
        pos1 = randint(0, self.__param['popSize'] - 1)
        pos2 = randint(0, self.__param['popSize'] - 1)
        if (self.__population[pos1].fitness > self.__population[pos2].fitness):
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
            if off.fitness > worst.fitness:
                for i in range(self.__param['popSize']):
                    if self.__population[i] == worst:
                        self.__population[i] = off
                        break
    
#from fcOptimisGA.BinChromosome import Chromosome
from random import seed 

seed(1)

# initialise de GA parameters
gaParam = {'popSize' : 100, 'noGen' : 100}
# problem parameters
problParam = {'min' : MIN, 'max' : MAX, 'function' : modularity, 'noNodes' : MAX}

# store the best/average solution of each iteration (for a final plot used to anlyse the GA's convergence)
allBestFitnesses = []
allAvgFitnesses = []
generations = []


ga = GA(gaParam, problParam)
ga.initialisation()
ga.evaluation()

bestOfBestChromo = Chromosome(problParam)
from collections import Counter
for g in range(gaParam['noGen']):
    #plotting preparation
    bestSolX = ga.bestChromosome().repres
    bestSolY = ga.bestChromosome().fitness
    generations.append(g)

    #logic alg
    #ga.oneGenerationElitism()
    ga.oneGenerationSteadyState()
    
    bestChromo = ga.bestChromosome()
    if bestOfBestChromo.fitness < bestChromo.fitness:
        bestOfBestChromo = bestChromo

    print('Generation: ' + str(g) + '; nr. comunitati: ' + str(len(Counter(bestChromo.repres).keys())) + '; is: x = ' + str(bestChromo.repres) + ' fitness = ' 
          + str(bestChromo.fitness))

print('\n')
print("No. communities: " + str(len(Counter(bestOfBestChromo.repres).keys())))
print("Best solution: " + str(bestOfBestChromo.repres) + '\n')
print("Best fitness: " + str(bestOfBestChromo.fitness) + '\n')
    
import numpy as np 
import matplotlib.pyplot as plt 
import warnings 

warnings.simplefilter('ignore')

A=np.matrix(param["mat"])
G=nx.from_numpy_matrix(A)
pos = nx.spring_layout(G)  # compute graph layout
plt.figure(figsize=(10, 10))  # image is 8 x 8 inches 
nx.draw_networkx_nodes(G, pos, node_size=200, cmap=plt.cm.RdYlBu)
nx.draw_networkx_edges(G, pos, alpha=0.3)
plt.show(G)


#%%

# plot a particular division in communities

A=np.matrix(param["mat"])
G=nx.from_numpy_matrix(A)
pos = nx.spring_layout(G)  # compute graph layout
plt.figure(figsize=(10, 10))  # image is 8 x 8 inches 
nx.draw_networkx_nodes(G, pos, node_size = 200, cmap = plt.cm.RdYlBu, node_color = bestOfBestChromo.repres)
nx.draw_networkx_edges(G, pos, alpha = 0.3)
plt.show(G)
    
    
# BEST DOLPHINS 0.52 (300 pop 300 gen)
# BEST KARATE 0.41 (300 pop 300 gen)
# BEST FOOTBALL 0.601 (300 pop 300 gen)
# BEST KREBS 0.6009 (300 pop 300 gen)
# BEST LESMIS 0.546 (300 pop 300 gen)
# BEST ADJNOUN 0.258 (300 pop 300 gen)
    