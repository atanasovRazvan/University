'''
Created on Mar 17, 2020

@author: Razvan
'''

from Algorithm.Entity import BetweenTowns

class Repository:
    
    def __init__(self, filename):
        self.repo = []
        self.size = 0
        self.start = 0
        self.finish = 0
        self.filename = filename
        
    def load(self):
        f = open(self.filename, "r")
        x = f.readline()
        self.size = int(x)
        for i in range(int(x)):
            count = 1
            for j in f.readline().strip().split(","):
                muchie = BetweenTowns(i+1, count, int(j))
                self.repo.append(muchie)
                count = count + 1
        self.start = int(f.readline())
        self.finish = int(f.readline())
        f.close()
        
    def get_start(self):
        return self.start
    
    def get_finish(self):
        return self.finish
            
    def getAdiacencyList(self):
        lista = []
        f = open(self.filename, "r")
        x = f.readline()
        for i in range(int(x)):
            lista.append([])
            for j in f.readline().strip().split(","):
                lista[i].append(int(j))
        return lista
            
            