import re
from unittest import TestCase
import unittest

"""
Yes, I know this is terrible, don't remind me
.
.
.
i did ths task at 19:25 25 oct and i just want to sleep because I have matan at 8 20 tomorrow
"""
def is_ad(s):
    ptr = r"\w+ой\b|\w+ий\b|\w+ого\b|\w+его\b|\w+ому\b|\w+ему\b|\w+ым\b|\w+им\b|\w+ом\b|\w+ем\b|\w+ая\b|\w+ья\b|\w+ую\b|\w+ью\b|\w+ей\b|\w+ое\b|\w+ее\b|\w+ье\b|\w+ые\b|\w+ие\b|\w+ых\b|\w+их\b|\w+им\b|\w+ым\b|\w+ыми\b|\w+ими\b|\w+ый\b|\w+ий\b"
    k = re.finditer(ptr, s)
    for i in k:
        print(s[i.start():i.end()])
    return re.finditer(ptr, s)

def harakiri(s, n) -> str:
    s = s.lower()
    wordsiter = is_ad(s)
    res_s = s
    ptr = r"ой$|ий$|ого$|его$|ому$|ему$|ым$|им$|ом$|ем$|ая$|ья$|ую$|ью$|ей$|ое$|ее$|ье$|ые$|ие$|ых$|их$|им$|ым$|ыми$|ими$|ий$|ый$"
    selecteded_roots = dict()
    for i in wordsiter:
        #print(i.start(), i.end())
        root_sup = re.search(ptr, s[i.start():i.end()])
        root = s[i.start():i.end()][:root_sup.start()]
        rootm = re.search(ptr, s[i.start():i.end()])
        if root in selecteded_roots:
            selecteded_roots[root].append((i.start(), i.end(), rootm.start(), rootm.end()))
        else:
            selecteded_roots[root] = [(i.start(), i.end(), rootm.start(), rootm.end())]
    it = 0
    for i in selecteded_roots.keys():
        if len(selecteded_roots[i]) > 1 and len(selecteded_roots[i]) >= n:
            for j in range(len(selecteded_roots[i])):
                """print(res_s[selecteded_roots[i][n - 1][0] + it:selecteded_roots[i][n - 1][1] + it])
                res_s1 = res_s[:selecteded_roots[i][j][0] + it] + res_s[selecteded_roots[i][n - 1][0] + it:selecteded_roots[i][n - 1][1] + it] + res_s[selecteded_roots[i][j][1] + it:]
                it += len(res_s1) - len(res_s)
                res_s =  res_s1"""
                res_s = res_s.replace(s[selecteded_roots[i][j][0]:selecteded_roots[i][j][1]], s[selecteded_roots[i][n - 1][0]:selecteded_roots[i][n - 1][1]])

    print(selecteded_roots, it)
    return res_s


class TestPattern(TestCase):
    def test_1(self):
        s = """Футбольный клуб «Реал Мадрид» является 15-кратным обладателем главного
футбольного европейского трофея – Лиги Чемпионов. Данный турнир организован
Союзом европейских футбольных ассоциаций (УЕФА). Идея о континентальном
футбольном турнире пришла к журналисту Габриэлю Ано в 1955 году. мемного мемнее"""
        ans = """Футбольного клуб «Реал Мадрид» является 15-кратным обладателем главного
футбольного европейских трофея – Лиги Чемпионов. Данный турнир организован
Союзом европейских футбольного ассоциаций (УЕФА). Идея о континентальном
футбольного турнире пришла к журналисту Габриэлю Ано в 1955 году. мемнее мемнее""".lower()
        self.assertEqual(harakiri(s, 2), ans)

    def test_2(self):
        s = "просто текст, красивый мост, красивая зелёная бабочка на зелёном ковре"
        ans = "просто текст, красивый мост, красивый зелёная бабочка на зелёная ковре"
        self.assertEqual(harakiri(s, 1), ans)

    def test_3(self):
        s = "интересный интересная интересное интересную  интересные"
        ans = "интересное интересное интересное интересное  интересное"
        self.assertEqual(harakiri(s, 3), ans)

    def test_4(self):
        s = "человеческий умный красивый умелый"
        ans = "человеческий умный красивый умелый"
        self.assertEqual(harakiri(s, 2), ans)

    def test_5(self):
        s = "интересный интересная интересное интересную  интересные"
        ans = "интересный интересная интересное интересную  интересные"
        self.assertEqual(harakiri(s, 6), ans)


if __name__ == "__main__":
    unittest.main()
    '''#t1 = 'футбольный'
    #print(re.findall(r"ой$|ий$|ого$|его$|ому$|ему$|ым$|им$|ом$|ем$|ая$|ья$|ую$|ью$|ей$|ое$|ее$|ье$|ые$|ие$|ых$|их$|им$|ым$|ыми$|ими$|ий$|ый$", t1))
    text = """Футбольный клуб «Реал Мадрид» является 15-кратным обладателем главного
футбольного европейского трофея – Лиги Чемпионов. Данный турнир организован
Союзом европейских футбольных ассоциаций (УЕФА). Идея о континентальном
футбольном турнире пришла к журналисту Габриэлю Ано в 1955 году."""
    print(harakiri(text, 2))'''
