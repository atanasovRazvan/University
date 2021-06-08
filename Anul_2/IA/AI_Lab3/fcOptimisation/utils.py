'''
Created on Mar 25, 2020

@author: Razvan
'''

from random import uniform
from numpy.random.mtrand import randint

def generateNewValue(lim1, lim2):
    return randint(lim1, lim2)

def binToInt(x):
    val = 0
    # x.reverse()
    for bit in x:
        val = val * 2 + bit
    return val