'''
Created on Apr 11, 2020

@author: Razvan
'''

from cmath import sqrt

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
            if filename == "Data/hardE.txt":
                mat[i].append( int ( sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) ).real))
            else:
                mat[i].append( ( sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) ).real))
    
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

from ACO import ACO

params = readFile("Data/berlin52.txt")

probParams = {"noAnts": 50, "generations": 50, "dynamic": 10}
params['pheromone'] = []
params['oldPheromoneRate'] = 0.5
params['q0'] = 0.01
params['alpha'] = 5
params['beta'] = 3

aco = ACO(params, probParams)
aco.initialize()
aco.initializePheromone()

bestOfBestAnt = None

for i in range(1, probParams['generations']):
    aco.explore()
    bestAnt = aco.bestAnt()
    print ("Generation " + str(i) + " best ant distance: " + str(bestAnt.distance()) + " | road: " + str(bestAnt.path()))

    if bestOfBestAnt == None or bestAnt.distance() < bestOfBestAnt.distance():
        bestOfBestAnt = bestAnt

    if i % probParams['dynamic'] == 0:
        aco.deleteEdge()


    aco.initialize()

print ("Cea mai inteligenta furnicuta: " + str(bestOfBestAnt.distance()) + " with path " + str(bestOfBestAnt.path()))


#Best berlin: 7548.9927
#Optimal berlin: 7544.3659
