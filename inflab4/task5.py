from task4 import parse4
from task3 import parse3
from task2 import parse2
from task1 import parse1
import time

def test_time(f):
    start = time.time()
    for i in range(100):
        f()
    print(time.time() - start)

test_time(parse1)
test_time(parse2)
test_time(parse3)
test_time(parse4)