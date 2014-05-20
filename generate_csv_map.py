__author__ = "Nick"

import csv
import sys
import random

MAP_WIDTH = 200
MAP_HEIGHT = 200
DEFAULT_TILE = 1999
DUG_TILE = 2
CHANCE_TO_SPAWN_MINER = 0.08
FILENAME = "game/static.csv"
DEFAULT_SEED = 7363839
MAX_MINERS = 400

seed = float(sys.argv[1]) if len(sys.argv) > 1 else DEFAULT_SEED
random.seed(seed)

# Initializes the map to the default tile
map = [[DEFAULT_TILE for i in range(MAP_WIDTH)] for i in range(MAP_HEIGHT)]

class Directions(object):
    UP = 0
    DOWN = 1
    RIGHT = 2
    LEFT = 3

directions = [Directions.UP, Directions.DOWN, Directions.RIGHT, Directions.LEFT]

class Miner(object):
    x = None
    y = None
    active = True

    def __init__(self, x, y):
        self.x = x
        self.y = y

    def pick_cell(self, miners):
        if not self.active:
            return
        choices = list(directions)
        random.shuffle(choices)
        has_dug = False
        for dir in choices:
            if dir == Directions.UP:
                new_y = self.y - 1
                if new_y >= 0 and map[self.x][new_y] == DEFAULT_TILE:
                    self.y = new_y
                    has_dug = True
                    break
            elif dir == Directions.DOWN:
                new_y = self.y + 1
                if new_y < MAP_HEIGHT and map[self.x][new_y] == DEFAULT_TILE:
                    self.y = new_y
                    has_dug = True
                    break
            elif dir == Directions.LEFT:
                new_x = self.x - 1
                if new_x >= 0 and map[new_x][self.y] == DEFAULT_TILE:
                    self.x = new_x
                    has_dug = True
                    break
            elif dir == Directions.RIGHT:
                new_x = self.x + 1
                if new_x < MAP_WIDTH and map[new_x][self.y] == DEFAULT_TILE:
                    self.x + new_x
                    has_dug = True
                    break
        number = random.random()
        print("Random chance: " + str(number))
        if not has_dug:
            self.active = False
        else:
            map[self.x][self.y] = DUG_TILE
            if number < CHANCE_TO_SPAWN_MINER:
                new_miner_x = self.x
                new_miner_y = self.y
                for dir in choices:
                    if dir == Directions.UP:
                        new_y = new_miner_y - 1
                        if new_y >= 0:
                            new_miner_y = new_y
                            break
                    elif dir == Directions.DOWN:
                        new_y = new_miner_y + 1
                        if new_y < MAP_HEIGHT:
                            new_miner_y = new_y
                            break
                    elif dir == Directions.LEFT:
                        new_x = new_miner_x - 1
                        if new_x >= 0:
                            new_miner_x = new_x
                            break
                    elif dir == Directions.RIGHT:
                        new_x = new_miner_x + 1
                        if new_x < MAP_WIDTH:
                            new_miner_x = new_x
                            break
                print("Spawning miner at: " + str(new_miner_x) + ", " + str(new_miner_y))
                if map[new_miner_x][new_miner_y] == DEFAULT_TILE:
                    map[new_miner_x][new_miner_y] = DUG_TILE
                miners.append(Miner(new_miner_x, new_miner_y))

    def __repr__(self):
        return "(" + str(self.x) + ", " + str(self.y) + ")"

miners = [Miner(int(MAP_WIDTH / 2), int(MAP_HEIGHT / 2))]

while True:
    if len(miners) >= MAX_MINERS:
        break;
    num_miners = len(miners)
    print("Num Miners: " + str(num_miners))

    for i in range(0, num_miners):
        miners[i].pick_cell(miners)

# Writes the file
with open(FILENAME, "w") as csvfile:
    for row in map:
        for column in row:
            csvfile.write(str(column) + ',' + str(column) + ',')
        csvfile.write("\n")
        for column in row:
            csvfile.write(str(column) + ',' + str(column) + ',')
        csvfile.write("\n")