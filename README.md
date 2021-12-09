# Making a Directed Graph
<br /> 


![this is an image](https://i.ibb.co/G5ynrVT/0-hdx0-WTASo-HI5jy-ZJ.jpg)  <br />
### In this assignment we were asked to program a directed Graph,checking Properties about it with some algorithms and draw it with Gui. 



# Input Files <br />
To make the graph running, the program need to get input about the verticals, and the edges the graph contains.

- we get from the consumer interfaces of the Graph, vertical data, Coordinate position of vertical, edge properties and algorithms of the graph .
- this information imported to the program with Json FIle, verticals named: "Nodes" and edges named:"Edges".
<photo>
  


# our code <br />
The main idea was to use the exclusive id that every node have and to access him with complexity if o(1) with Hashmap.<br />
The graph will save all the relevant data about each vertical and edge in the graph, so we can check some algorithm with a better complexity.

## classes <br />
1. **MyGeoLocation** - this class saved The Location of the node.
   <br /><br />
2. **MyNodeData** - this class save information about each vertical:
   - its exclusive id.
    - MyGeoLocation information.
    - HashMap for all the edges from this node to another.
    - HashMap for all the edges that getting to this node.                
      <br />
3. **MyEdgeData** - contains to every edge The id source, id detention and the wight of the edge.<br /><br />
4. **MyDirectedGraph** - contains List of the nodes in the graph(MyNodeData) and all the edges(MyEdgeDate).<br /><br />
5. **MyDirectedGraphAlgorithm** - this class get MyDirectedGraph and can calculate the next list of algorithms:
- If the graph is connected.
- A shorted path between 2 verticals.
- The ideal center of the graph.
- Tsp problem for a group of verticals in the graph
  <br />
  
## Gui <br />
This classes generate a windows that show the graph with all its properties.<br />
<photo>
you can load to the graph with Json file that define the verticals and edges of the graph, and it will load and show it on the screen.
<br />
![this is an image](https://i.ibb.co/gDh9Rj5/image.png)  <br /> 

on the window we have buttons of two different subjects we can use on the graph:
- Graph algorithm buttons
- changing Graph Properties buttons
<br />

![this is an image](https://i.ibb.co/6mP87Nw/image.png)  <br />



## Algorithms<br />

The algorithms we execute:
- **IsConnected** - in this algorithm we want to check is we can get to each vertices from another one. <br />
  for checking it we choose randomly one of the vertical on the graph and do BFS(Breadth-first-search) and check if the number of different Node we can arrive equal to the number of the node in the graph. if its True, we make G transpose graph and run BFS again.
  if its work again its mean that we can arrive from this node to each other one and from each node to our chosen node.
    

- **ShortPathDis** - this algoritem check what is the best lower wight we can spend to arive from a vertex to another one (if they are connect).
  we use Dijkstra algorithem to find the wight and using Dynamic programing to save already all the wights from this node to the another connect one. <br /><br />
- **ShortPath** - return the station that make the shortest Path. we save it when we run the Dijkstra algorithm. <br /><br />
- **Dijkstra** - this algorithm get src to check the path from it to all other nodes. first it check if the this List off values is exist and updated. if isn't we run the algorithem and save the stations that represent the shortest path between each node from our src input.<br /><br />
- **Center** - find the Node on the graph that will arrive fastest to the most distance from him. <br />
we doing Dijkstra on each node and save the max dis from it. thaen we check the node with the lowest max weight. <br /><br />

- **tsp** -it will find the best way to do circle on group of nodes in the graph. 
  this solution algorithem will be in factorial complicity Time and can't be calculated for more to 15 nodes. <br />
  then we execute a greedy algorithm that check the nearest next node from my current node.
  i am saving the permutation for each starting node and find the node that will be the best to start from.
  for finding the lowest weight between all two nodes in the group, we need to run Dijkstra to this node (if it never calculate it yet).
  


## UML Diagram <br />
bkakak<br />

##Algorithms Results<br />
![this is an image](https://i.ibb.co/yftKZ9d/result.png)
## how to run the program <br />

example of Graph Json file you can find in the [data folder](https://github.com/dvirGev/OOP--Ex2/tree/main/data)  <br />












