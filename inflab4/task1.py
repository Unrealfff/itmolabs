from collections import deque
def parse1():
    with open("task1.xml", 'w') as dst:
        with open("data.yaml", 'r') as src:
            dst.write('<?xml version="1.0" encoding="UTF-8"?>\n')
            data = src.readlines()
            steck = deque()
            new_day = True
            for i in data:
                for j in range(len(i)):
                    if i[j] == ':' and i[j + 1] == '\n':
                        teg = i[:j].strip()
                        if len(steck) > 1:
                            dst.write(f"</{steck.pop()}>\n")
                        #dst.write(f"</{steck.pop()}>\n")
                        dst.write(f"<{teg}>\n")
                        steck.append(teg)
                        new_day = True
                        break
                    elif i[j] == '-':
                        teg = steck.pop()
                        if len(steck) > 0 and not new_day:
                            dst.write(f"</{teg}>\n")
                            dst.write(f"<{teg}>\n")
                        new_day = False
                        steck.append(teg)
                        #steck.append(teg)
                    elif i[j] not in "\n :" and ": " in i:
                        inf = i.strip().split(": ")
                        if '-' in inf[0]:
                            inf[0] = inf[0][inf[0].find('-') + 2:]
                        dst.write(f"<{inf[0]}> {inf[1]} </{inf[0]}>\n")
                        break
            while len(steck) > 0:
                dst.write(f"</{steck.pop()}>\n")

if __name__ == "__main__":
    parse1()