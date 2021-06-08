'''
Created on Mar 17, 2020

@author: Razvan
'''
from Algorithm.Repository import Repository
from Algorithm.Service import Service

def Main():
    
    repo = Repository("C:\\Users\\Razvan\\eclipse-workspace\\AI_Lab2\\Data\\input.txt")
    repo.load()
    srv = Service(repo)
    srv.printTSPresult(1)
    srv.printShortestPath()
    
    
Main()