import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Vertex implements Comparable<Vertex>
{
	public String name;
	public Edge[] neighbor;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex previous;
	public Vertex(String argument) { name = argument; }
	public String toString() { return name; }
	public int compareTo(Vertex other)
	{
		return Double.compare(minDistance, other.minDistance);
	}
}

class Edge
{
	public Vertex target;
	public double weight;
	public Edge(Vertex next, double distance)
	{ target = next; weight = distance; }
}

public class DijkstraShortestPath
{
	public static void computePaths(Vertex source)
	{
		source.minDistance = 0.;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			for (Edge e : u.neighbor)
			{
				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);
					v.minDistance = distanceThroughU ;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}
	
	public static void printMatrix(){
		int[][] graph={
			//   1 2 3 4 5 6 7 8 9 10
				{0,1,2,3,0,0,0,19,0,0},//o
				{1,0,0,10,30,20,0,0,0,90},//1
				{2,0,0,5,0,1,30,2,0,0},//2
				{3,10,5,0,55,26,35,0,86,0},//3
				{0,30,0,55,0,0,26,0,0,87},//4
				{0,0,1,26,0,0,76,26,0,0},//5
				{0,20,30,35,26,76,0,0,25,59},//6
				{19,0,2,0,0,26,0,0,80,200},//7
				{0,0,0,86,0,0,25,80,0,61},//8
				{0,90,0,0,87,0,59,200,61,0} //9
		};
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				System.out.print(graph[i][j]+",");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static List<Vertex> shortestPathTo(Vertex target)
	{
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);
		Collections.reverse(path);
		return path;
	}

	public static void main(String[] args)
	{
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");
		Vertex v4 = new Vertex("v4");
		Vertex v5 = new Vertex("v5");
		Vertex v6 = new Vertex("v6");
		Vertex v7 = new Vertex("v7");
		Vertex v8 = new Vertex("v8");
		Vertex v9 = new Vertex("v9");
		Vertex v10 = new Vertex("v10");

		v1.neighbor = new Edge[]{new Edge(v2, 1),new Edge(v3, 2),new Edge(v4, 3),new Edge(v8, 19)};
		v2.neighbor = new Edge[]{new Edge(v1, 1),new Edge(v4, 10),new Edge(v5, 30),new Edge(v7, 20),new Edge(v10, 90)};
		v3.neighbor = new Edge[]{new Edge(v1, 2),new Edge(v4, 5),new Edge(v6, 1),new Edge(v7,30),new Edge(v8, 2) };
		v4.neighbor = new Edge[]{new Edge(v1, 3),new Edge(v2,10),new Edge(v3, 5),new Edge(v5, 55),new Edge(v6, 26),new Edge(v7, 35),new Edge(v9, 86)};
		v5.neighbor = new Edge[]{new Edge(v2, 30),new Edge(v4,55),new Edge(v7, 26),new Edge(v10, 87)};
		v6.neighbor = new Edge[]{new Edge(v3, 1),new Edge(v4,26),new Edge(v7, 76),new Edge(v8, 26)};
		v7.neighbor = new Edge[]{new Edge(v2,20),new Edge(v3, 30),new Edge(v4, 35),new Edge(v5, 26),new Edge(v6, 76),new Edge(v9, 25),new Edge(v10,59)};
		v8.neighbor = new Edge[]{new Edge(v1, 19),new Edge(v3,2),new Edge(v6, 26),new Edge(v9, 80),new Edge(v10, 200)};
		v9.neighbor = new Edge[]{new Edge(v4, 86),new Edge(v7,25),new Edge(v8, 80),new Edge(v10, 61)};
		v10.neighbor = new Edge[]{new Edge(v2, 90),new Edge(v5,87),new Edge(v7, 59),new Edge(v9, 61),new Edge(v8, 200)};
		Vertex[] vertices = { v1, v2, v3, v4,v5,v6,v7,v8,v9,v10 };
		computePaths(v1);
		System.out.println("The adjcency matrix of the graph is:");
		printMatrix();
		System.out.println();
		for (Vertex v : vertices)
		{
			System.out.println("The shortest path from v1 to "+v+" is:");
			List<Vertex> path = shortestPathTo(v);
			System.out.println(path);
		}
	}
}