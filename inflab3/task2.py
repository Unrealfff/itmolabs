import re
from unittest import TestCase
import unittest


def pattern(s:str) -> int:
    ptr = r"[ёуеыаоэяиюЁУЕЫАОЭЯИЮ]"
    return len(re.findall(ptr, s))

def harakiri(haiku:str) -> str:
    parts = re.split(r'/', haiku)
    if len(parts) != 3:
        print("Не хайку. Должно быть 3 строки.")
        return "Не хайку. Должно быть 3 строки."
    if pattern(parts[0]) == pattern(parts[2]) and pattern(parts[0]) == 5 and pattern(parts[1]) == 7:
        print("Хайку!")
        return "Хайку!"
    print("Не хайку.")
    return "Не хайку."




class TestPattern(TestCase):
    def setUp(self) -> None:
        self.ans = ["Хайку!", "Не хайку. Должно быть 3 строки.", "Не хайку."]
        return super().setUp()
    
    def test_1(self):
        s = "Вечер за окном. / Еще один день прожит. / Жизнь скоротечна..."
        self.assertEqual(harakiri(s), self.ans[0])

    def test_2(self):
        s = "Просто текст"
        self.assertEqual(harakiri(s), self.ans[1])

    def test_3(self):
        s = "Как вишня расцвела! / Она с коня согнала / И князя-гордеца."
        self.assertEqual(harakiri(s), self.ans[2])

    def test_4(self):
        s = "Вечер за окном. / Еще один день прожит. / Жизнь скоротечна...".upper()
        self.assertEqual(harakiri(s), self.ans[0])

    def test_5(self):
        s = "Ноеслиестьвкарманепачкасигарет/значитвсёнетакужплохо/насегодняшнийдень"
        self.assertEqual(harakiri(s), self.ans[2])


if __name__ == "__main__":
    unittest.main()