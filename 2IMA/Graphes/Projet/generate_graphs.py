import networkx as nx
import matplotlib.pyplot as plt
from random import randint

graphs = []
for i in range(100, 1000+1, 100):
    graphs.append(nx.gn_graph(i))
graphs.append(nx.gn_graph(10000))

s = "#use \"projet.ml\"\n\n"

for i, graph in enumerate(graphs) :
    name = "ge" + str(graph.number_of_nodes())

    s += "let " + name + " = Dag.create ();;\n"

    s += "\n"
    for i, node in graph.nodes_iter(data=True):
        node['name'] = "v" + str(i)
        node['ressource'] = randint(1, 2)
        node['cout'] = randint(1, 3)

    for i, node in graph.nodes(data=True):
        s += "let " + node['name'] + " = Dag.createv (\"" \
            + node['name'] \
            + "\", " + str(node['ressource']) \
            + ", " + str(node['cout']) \
            + ".);;\n"

    s += "\n"
    for i, node in graph.nodes(data=True):
        s += "Dag.add_vertex " + name + " " + node['name'] + ";;\n"

    s += "\n"
    for edge in graph.edges_iter():
        node1 = graph.node[edge[0]]
        node2 = graph.node[edge[1]]
        s += "Dag.add_edge " + name + " " + node1['name'] + " " + node2['name'] + ";;\n"

    s += "\n"
    s += "Dag.dot_output " + name + " \"" + name + ".dot\" ;;"

    s += "\n"

#print(s)

file = open('dag_generate.ml', 'w')
file.write(s)
file.close()

#nx.draw(graphs[0])
#plt.show()