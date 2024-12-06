from collections import deque


def level(s:str):
    for i in range(len(s)):
        if s[i] != ' ':
            return i

def parse_dict(s) -> dict:
    lvl = level(s[0])
    res = dict()
    i = 0
    while i < len(s):
        if ":\n" in s[i]:
            key = s[i].strip()[:s[i].strip().find(":")]
            val = [s[i]]
            j = i + 1
            while j < len(s) and level(s[j]) > lvl:
                val.append(s[j])
                i += 1
                j += 1
            res[key] = parse(val, False)
        else:
            key = s[i].strip()[:s[i].strip().find(":")]
            val = s[i].strip()[s[i].strip().find(":") + 1:]
            res[key] = val.strip()
        i += 1
    return res
        

def parse_list(s) -> list:
    lvl = level(s[0])
    res = list()
    iter = 0
    for i in range(len(s)):
        if lvl == level(s[i]):
            tmp = s[i].replace('-', f'{iter}:', 1)
            if tmp != s[i]:
                iter += 1
                s[i] = tmp[:tmp.find(':') + 1] + "\n"
                s.insert(i + 1, (' ' * lvl) + tmp[tmp.find(':') + 1:])
    tempdict = parse_dict(s)
    for i in range(len(tempdict)):
        res.append(tempdict[str(i)])
    return res


def parse(mas, __first=True):
    s = mas[1]
    if '-' in s:
        if not __first:
            return parse_list(mas[1:])
        return {mas[0][:mas[0].find(':')]: parse_list(mas[1:])}
    elif ':' in s:
        if not __first:
            return parse_dict(mas[1:])
        return {mas[0][:mas[0].find(':')]: parse_dict(mas[1:])}


def unparse_dict(tree:dict, name) -> str:
    res = str()
    for i in tree.keys():
        if type(tree[i]) != type(list()):
            res += f"<{i}>"
            res += unparse(tree[i], i, False)
            res += f"</{i}>\n"
        else:
            res += unparse(tree[i], i, False)
    return res

def unparse_list(tree:list, name) -> str:
    res = str()
    for i in range(len(tree)):
        res += f"<{name}>\n"
        res += unparse(tree[i], i, False)
        res += f"</{name}>\n"
    return res


def unparse(tree, name=None, __first=True) -> str:
    res = str()
    if type(tree) == type(dict()):
        res = unparse_dict(tree, name)
    elif type(tree) == type(list()):
        res = unparse_list(tree, name)
    else:
        return tree
    if __first:
        with open("task4.xml", 'w') as f:
            f.write('<?xml version="1.0" encoding="UTF-8"?>\n')
            f.write(res)
    return res

def parse4():
    with open("data.yaml", 'r') as src:
        data = src.readlines()
        tree = parse(data) #tree["timetable"]["вторник"]
        unparse(tree)

if __name__ == "__main__":
    parse4()