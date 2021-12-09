#Assiment 2 - making a Directed Graph
<br /> 
##*In this assignment we were asked to program a directed Graph,checking Properties about it with some algorithms and draw it with Gui.*

-------------------------------------------------------------------------------------------------

#Input Files
To make the graph running, the program need to get input about the verticals, and the edges the graph contains.

- we get from the consumer interfaces of the Graph, vertical data, Coordinate position of vertical, edge properties and algorithms of the graph .
- this information imported to the program with Json FIle, verticals named: "Nodes" and edges named:"Edges".
<photo>
  
-------------------------------------------------------------------------------------------------

#our code
The main idea was to use the exclusive id that every node have and to access him with complexity if o(1) with Hashmap.<br />
The graph will save all the relevant data about each vertical and edge in the graph,<br /> so we can check some algorithm with a better complexity.
-------------------------------------------------------------------------------------------------
##classes
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
5. **MyDirectedGraphAlgorithm** - this class get MyDirectedGraph and can calculate the next list of algorithems:
- If the graph is connected.
- A shorted path between 2 verticals.
- The ideal center of the graph.
- Tsp problem for a group of verticals in the graph
  <br />
  -------------------------------------------------------------------------------------------------
## Gui
This classes generate a windows that show the graph with all its properties.<br />
<photo>
you can load to the graph with Json file that define the verticals and edges of the graph, and it will load and show it on the screen.
<br /> on the window we have buttons of two different subjects we can use on the graph:
- Graph algorithm buttons
- changing Graph Properties buttons
<photo>
-------------------------------------------------------------------------------------------------

Algorithms 
-------------------------------------------------------------------------------------------------
##UML Diagram

-------------------------------------------------------------------------------------------------
Algorithms Results
-------------------------------------------------------------------------------------------------
## how to run the program


![this is an image](https://static.scientificamerican.com/blogs/cache/file/1127EF51-F6B3-40BE-84D591F0CBD9F254_source.png?w=590&h=800&82189013-244C-4E7C-8DBA061961637719)









