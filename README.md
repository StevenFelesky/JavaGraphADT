# Java Graph ADT

Graph ADT implementation in Java.

WARNING: Memory hungry. Comment out graphs you do not wish to generate. 

## Graph Class
Graph is implemented as a hashmap where the key value is the node, and the value is the 
adjacency list of the node stored as a hashset. Hashmap and Hashset are used because the
order the nodes are stored in does not matter.

#### addNode(node)

addNode(node) takes a node, checks if it has already been added, and if it hasn't, adds
the node to the graph.

#### addDiEdge/addUnDiEdge(from, to)

addDi/UnDiEdge(from, to) takes to nodes, the node the edge is coming from and the node
the edge is going to. For undirected edges (UnDiEdge), two edges are added symmetrically.

#### everyNodeHasAnEdge()
The everyNodeHasAnEdge() method checks to see if every node has at least one edge.
It returns true if every node has an edge and false if not.

#### toString()
toString() returns the adjacency list of the graph.

### Computing Strongly Connected Components

Methods; SCC(), fillOrder(node, stack), transpose() and dfs(node), are used in computing
the strongly connected components of the graph. 

The strongly connected components are computed by Kosaraju's algorithm. Kosaraju's algorithm
works by computing DFS on a graph and storing the nodes by finish time in a stack. The graph
is transposed, or flipped, and then DFS is ran again from values popped from the stack.
Every node marked as visited on the second run of DFS is strongly connected with every 
other node to be marked as visited on that same DFS. Time complexity is given by O(N+E).

## Node Class
In this implementation of a Graph ADT nodes have a value and can be marked as visited
for the purposes of DFS as mentioned above. Nodes can be created with or without an initial
value and the value can be changed by getters and setters (getVal(), setVal(val)). The node
can be visited or de-visited by visit() and devisit().

## Author

Steven Felesky

11.12.2020