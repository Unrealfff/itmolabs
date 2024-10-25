import re
from unittest import TestCase
import unittest


"""8-{O"""
def pattern(s:str) -> int:
    ptr = r'8-{O'
    return len(re.findall(ptr, s))


class TestPattern(TestCase):
    def test_1(self):
        s = "8-{O} - just 1 smile"
        ans = 1
        self.assertEqual(pattern(s), ans)

    def test_2(self):
        s = "8-{O8-{O} - just 2 smiles"
        ans = 2
        self.assertEqual(pattern(s), ans)

    def test_3(self):
        s = "80-{O} -{ 8-{O8-{O8-{O8-{O- just 4 smiles"
        ans = 4
        self.assertEqual(pattern(s), ans)

    def test_4(self):
        s = "8-}O 0-{O 8{O 8-{0  8 -{O  8- {O  8-{ O  - just 0 smiles"
        ans = 0
        self.assertEqual(pattern(s), ans)

    def test_5(self):
        s = "8-{O O{-8 8-{O8-{O8-{O8-{O8-{O8-{O8-{O8-{O8-{O8-{O          8-{O           \n oowieuhrgiheouiboiweh 87639856787372654 {{{}{}{}{}{}{}}} /n/n\n 8-{O\n - just 13 smiles"
        ans = 13
        self.assertEqual(pattern(s), ans)


if __name__ == "__main__":
    unittest.main()