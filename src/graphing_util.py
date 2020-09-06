import matplotlib.pyplot as plt
import numpy as np
import sys
import os
import warnings

def parse_file(path):
    c_val = np.array([])
    percent = np.array([])
    input_data = open(str(path))
    for i, line in enumerate(input_data):
        if(line == '\n'):
            break
        temp = line.split(',')
        c_val = np.append(size, int(line.split(',')[0]))
        percent = np.append(swaps, int(line.split(',')[1]))   
    os.remove(path)
    return (c_val, percent)

def plot_graph(c_val, percent):
    plt.clf()
    plt.plot(c_val, percent,'ko', size, swaps, 'b')
    plt.title('C value vs Percent of Graphs with t or more connected nodes')
    plt.xlabel('C value')
    plt.ylabel('Percent')
    plt.grid()
    plt.show()

warnings.filterwarnings('ignore')
sort_algo = sys.argv[1]
path = sys.argv[2]
data = parse_file(path)
plot_grapgh(data[0], data[1])
