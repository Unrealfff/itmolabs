import math


def find_root(a, b, f, e=0):
    if a > b or f(a) * f(b) > 0:  
        raise ValueError
    
    mid = (b + a) / 2
    midf = f(mid)
    if abs(b - a) < 2 * e or midf == 0:
        return mid
    if f(a) * midf < 0:
        return find_root(a, mid, f, e)
    else:
        return find_root(mid, b, f, e)

        
if __name__ == "__main__":
    def f(x):
        return math.atan(1/x) - x**2
    
    print("x0 =", find_root(0.00001, 1, f, 1E-10))