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
        c_val = np.append(c_val, float(line.split(',')[0]))
        percent = np.append(percent, float(line.split(',')[1]))   
    return (c_val, percent)

def plot_graph(c_val, percent):
    plt.figure(figsize=(12,8))
    plt.plot(c_val, percent,'ko', c_val, percent, 'b')
    plt.title('C value vs Percent of Graphs with t or more connected nodes')
    plt.xlabel('C value')
    plt.ylabel('Percent')
    plt.grid()
    plt.show()

warnings.filterwarnings('ignore')
path = sys.argv[1]
data = parse_file(path)
plot_graph(data[0], data[1])
