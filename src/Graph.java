import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class Graph {
	private int[][] adj_matrix;
	
	// Constructor that takes a 2D array. Used for testing purposes only!
	public Graph(int[][] input) {
		if(input.length != input[0].length) {
			System.out.println("Adjacency Matrix must be square!");
		}
		else {
			this.adj_matrix = input;
		}
	}
	
	//Graph constructor: takes n as number of nodes and p as the probability threshold of generating an edge
	public Graph(int n, double p) {
		//Validation checkpoint for n and p
		if(n <= 1 || (0 > p && p > 1)) {
			System.out.println("Invalid parameters. Make sure n > 1 and 0 <= p <= 1");
		}
		else {
			this.adj_matrix = new int[n][n];
			Random rand = new Random();
			for(int i = 0; i < this.adj_matrix.length; i++) {
				for(int j = 0; j < this.adj_matrix[i].length; j++) {
					//Only loop through the upper diagonal of the adjacency matrix so the graph is undirected
					if(i < j && rand.nextDouble() < p) {
						//If conditions met (random number greater than p) then set an edge between nodes (i,j) and (j,i)
						this.adj_matrix[i][j] = 1;
						this.adj_matrix[j][i] = 1;
					}
				}
			}
			
		}
	}
	
	//Use BFS algorithm to explore the graph. 
	//Loops through each node in the graph and treats it as a root to find the longest possible path.
	//Number t is just a threshold, if a path length is equal or larger than t, then return 1 immediately.
	//Else, just continue looping until we find something or return 0 if we explore through available node.
	public int largest_connected_bfs(int t) {
		int height;
		//Treat each node in graph G as a root.
		for(int i = 0; i < this.adj_matrix.length; i++) {
			Queue<Integer> queue = new LinkedList<>();
			Set<Integer> visited = new HashSet<>();
			queue.add(i);
			visited.add(i);
			height = 0;
			boolean pushed = false;
			//Start BFS algorithm here, while the node queue is not empty
			while(!queue.isEmpty()) {
				//Dequeue node (index) and make it current node
				int curr_node = queue.remove();
				//Loop through adjacency matrix row at index [node]
				for(int j = 0; j < this.adj_matrix.length; j++) {
					//If the current entry in row is equal to 1 and is was not visited before...
					if(this.adj_matrix[curr_node][j] == 1 && !visited.contains(j)) {
						//Add it to the queue and mark it as visited.
						//Also, set the "pushed" flag to true meaning that there is another layer in graph to visit later on!
						queue.add(j);
						visited.add(j);
						pushed = true;
					}
				}
				//Use the previous "pushed" flag to count layers. If it was set to true, then increase height counter
				if(pushed) {
					height++;
					//If the height is larger than t, then return 1
					if (height >= t) { 
						return 1; 
					}
					//Reset "pushed" flag back to false
					pushed = false;
				}
			}
			//Print this line after looping through one row in the adjacency matrix just to show the path length from a given node.
			//Comment it if it is annoying.
			System.out.println("Longest possible path from Node [" + i + "]: " + height);
		}
		return 0;
	}
	
	//String-ify the adjacency matrix for printing.
	public String toString() {
		String out = "";
		for(int i = 0; i < this.adj_matrix.length; i++) {
			for(int j = 0; j < this.adj_matrix[i].length; j++) {
				if(j != this.adj_matrix[i].length - 1) {
					out += this.adj_matrix[i][j] + " ";
				}
				else {
					out += this.adj_matrix[i][j] + "\n";
				}
			}
		}
		return out;
	}
}
