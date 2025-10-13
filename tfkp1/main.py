from set import MolSet, Complex
import pygame
from pygame import gfxdraw
import time

class Main:
    def __init__(self, width:int, height:int, scale:int, m:int, moveX, moveY):
        pygame.init()
        self.screen = pygame.display.set_mode((width, height))
        pygame.display.set_caption("mandelbrot set")
        self.midX = moveX
        self.midY = moveY
        self.startX = self.midX - (width // 2)
        self.endX = (width // 2) + self.midX + 1
        self.startY = self.midY - (height // 2)
        self.endY = self.midY + 1 + (height // 2)
        self.scale = scale
        self.width = width
        self.moveX = moveX
        self.moveY = moveY
        self.width = width
        self.height = height
        self.mandelbrot = MolSet(m)

    def draw(self):
        WHITE = (255, 255, 255)
        BLACK = (0, 0, 0)
        self.screen.fill((0, 0, 0))
        print(self.startX/self.scale, self.endX/self.scale, self.startY/self.scale, self.endY/self.scale)
        print(self.moveX, self.moveY)
        print(self.startX, self.endX, self.startY, self.endY)
        #gfxdraw.pixel(self.screen, 300, 300, (255, 255, 255))

            

        
        for i in range(self.startX, self.endX + 1):
            ys = set()
            for j in range(self.startY, self.endY + 1):
                if (i, j) in ys or self.mandelbrot.generate(Complex(i / self.scale, j / self.scale)):
                    #print("aaa")
                    gfxdraw.pixel(self.screen, i - self.moveX + (self.width//2), j - self.moveY + (self.height//2), WHITE)
                    #gfxdraw.pixel(self.screen, i - self.moveX + (self.width//2), -j - self.moveY + (self.height//2), WHITE)
                    ys.add((i, -j))
            pygame.display.update()

    def start(self):
        self.draw()
        running = True
        while running:
            time.sleep(1)
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    running = False
        pygame.quit()

if __name__ == "__main__":
    main = Main(1920, 1200, 600*9, 40, 500, 3500)
    main.start()
    #mainhearz = Main(1920, 1200, 600, 100, 350+409, -594)
    #mainhearz.start()