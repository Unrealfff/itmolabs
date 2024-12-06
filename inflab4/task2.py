import xmltodict
import yaml 
from yaml import CLoader as Loader

def parse2():
    with open("data.yaml", 'r') as f:
        tree = yaml.load(f, Loader)
    with open("task2.xml", 'w') as f:
        f.write(xmltodict.unparse(tree, pretty=True))

if __name__ == "__main__":
    parse2()