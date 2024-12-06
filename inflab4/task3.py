import re
from collections import deque


def big(s:str) -> bool:
    ptr = r':\n'
    return len(re.findall(ptr, s)) >= 1

def dash(s:str) -> bool:
    ptr = r'\-'
    return len(re.findall(ptr, s)) >= 1
def parse3():
    with open("task3.xml", 'w') as dst:
        with open("data.yaml", 'r') as src:
            dst.write('<?xml version="1.0" encoding="UTF-8"?>\n')
            stack = deque()
            data = src.readlines()
            first_day = True
            for i in data:
                if big(i):
                    if len(stack) > 1:
                        teg = stack.pop()
                        dst.write(f"</{teg}>\n")
                    first_day = True
                    s = i.strip()
                    a = re.match(r"\w+", s)
                    teg = s[a.start():a.end()]
                    stack.append(teg)
                    #dst.write(f'</{stack.pop()}>\n')
                    dst.write(f'<{teg}>\n')
                else: 
                    if dash(i):
                        s = i.strip()[2:]
                        if not first_day:
                            teg = stack.pop()
                            dst.write(f"</{teg}>\n")
                            dst.write(f"<{teg}>\n")
                            stack.append(teg)
                        first_day = False
                    else:
                        s = i.strip()
                    inf = re.split(r': ', s)
                    dst.write(f"<{inf[0]}>{inf[1]}</{inf[0]}>\n")
            while stack:
                dst.write(f"</{stack.pop()}>\n")

if __name__ == "__main__":
    parse3()