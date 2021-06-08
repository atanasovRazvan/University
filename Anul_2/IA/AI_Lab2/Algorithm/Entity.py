'''
Created on Mar 17, 2020

@author: Razvan
'''
class BetweenTowns:
    
    def __init__(self, muchieStart, muchieFinal, cost):
        self.muchieStart = muchieStart
        self.muchieFinal = muchieFinal
        self.cost = cost

    def get_muchie_start(self):
        return self.muchieStart


    def get_muchie_final(self):
        return self.muchieFinal


    def get_cost(self):
        return self.cost


    def set_muchie_start(self, value):
        self.muchieStart = value


    def set_muchie_final(self, value):
        self.muchieFinal = value


    def set_cost(self, value):
        self.__cost = value
        
    def print(self):
        print(self.muchieStart, end=" ")
        print(self.muchieFinal, end=" ")
        print(self.cost)

    