from math import sqrt
class Complex:
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.module = sqrt((x**2) + (y**2))

    def getModule(self): return self.module
    
    def getX(self): return self.x
    
    def getY(self): return self.y
    
    def __mul__(self, a:complex):
        return Complex(self.getX() * a.getX() - self.getY() * a.getY(), self.getX() * a.getY() + self.getY() * a.getX())
    
    def __add__(self, a:complex):
        return Complex(self.getX() + a.getX(), self.getY() + a.getY())
    
    def __str__(self):
        return f"({self.x}; {self.y})"
    
    def __pow__(self, a:int):
        if a == 2:
            return self * self
        else:
            raise RuntimeError
        

class MolSet:
    def __init__(self, m:int):
        self.m = m
        
    def isRunnung(self, a:Complex):
        return a.getModule() <= 2
    
    def generate(self, c:Complex):
        mapa = {0: Complex(0, 0)}
        for i in range(1, self.m + 1):
            mapa[i] = (mapa[i - 1] ** 2) + c
            if not self.isRunnung(mapa[i]):
                return False
        return True
    
if __name__ == "__main__":
    pass