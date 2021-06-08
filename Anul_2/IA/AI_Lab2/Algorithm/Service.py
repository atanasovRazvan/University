'''
Created on Mar 17, 2020

@author: Razvan
'''

class Service:
    
    def __init__(self, repo):
        self.repo = repo
        
    def get_adlist(self):
        return self.repo.getAdiacencyList()
    
    def TSP(self, city):
        
        visited = [0] * (self.repo.size+1)
        visited[0]=1
        visited[city]=1
        scity = city
        
        result = 0
        road = [city]
        
        while True:
            minimum = 1000000
            count = 1
            for i in self.get_adlist()[city-1]:
                if minimum >= i and i!=0 and visited[count] == 0:
                    nextcity = count
                    minimum = i
                count = count + 1
            visited[nextcity] = 1
            city = nextcity
            result = result + minimum
            road.append(city)
            ok = 1
            for i in visited:
                if i==0:
                    ok = 0
            if ok==1:
                break
        
        result = result + self.get_adlist()[city-1][scity-1]
        return road, result
    
    def printTSPresult(self, city):
        road, result = self.TSP(city)
        f = open("C:\\Users\\Razvan\\eclipse-workspace\\AI_Lab2\\Data\\output.txt", "w")
        strr=""
        strr = strr + str(len(road))
        strr = strr + "\n"
        for i in road:
            strr = strr + str(i) + ","
        strr=strr[:-1]
        strr = strr + "\n"
        strr = strr + str(result)
        f.write(strr)
        f.close()
        
    def shortestPath(self):
        
        visited = [0] * (self.repo.size+1)
        visited[0]=1
        visited[self.repo.start]=1
        road = [self.repo.start]
        result = 0
        
        while visited[self.repo.finish] == 0:
            minimum = 10000
            count = 1
            for i in self.get_adlist()[self.repo.start-1]:
                if minimum >= i and i!=0 and visited[count] == 0:
                    nextcity = count
                    minimum = i
                count = count + 1
            
            if self.get_adlist()[self.repo.start-1][self.repo.finish-1] < minimum + self.get_adlist()[nextcity-1][self.repo.finish-1]:
                result = result + self.get_adlist()[self.repo.start-1][self.repo.finish-1]
                road.append(self.repo.finish)
                break
            
            visited[nextcity] = 1
            self.repo.start = nextcity
            result = result + minimum
            road.append(nextcity)
        
        return road, result
    
    def printShortestPath(self):
        road, result = self.shortestPath()
        f = open("C:\\Users\\Razvan\\eclipse-workspace\\AI_Lab2\\Data\\output.txt", "a")
        strr="\n"
        strr = strr + str(len(road))
        strr = strr + "\n"
        for i in road:
            strr = strr + str(i) + ","
        strr=strr[:-1]
        strr = strr + "\n"
        strr = strr + str(result)
        f.write(strr)
        f.close()
            
            
