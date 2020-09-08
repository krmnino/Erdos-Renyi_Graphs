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
	
	public int largest_connected_component(int t) {
		//Treat each node in graph G as a root.
		for(int i = 0; i < this.adj_matrix.length; i++) {
			int node_count = 0;
			Queue<Integer> queue = new LinkedList<>();
			boolean[] visited = new boolean[this.adj_matrix.length]; 
			queue.add(i);
			visited[i] = true;
			//Start BFS algorithm here, while the node queue is not empty
			while(!queue.isEmpty()) {
				//Dequeue node (index) and make it current node
				int curr_node = queue.remove();
				//Loop through adjacency matrix row at index [node]
				for(int j = 0; j < this.adj_matrix.length; j++) {
					//If the current entry in row is equal to 1 and is was not visited before...
					if(this.adj_matrix[curr_node][j] == 1 && !visited[j]) {
						//Add it to the queue and mark it as visited.
						//Each enqueued element is pushed with the node's depth value
						queue.add(j);
						visited[j] = true;
						node_count++;
					}
				}
				if(node_count >= t) {
					return 1;
				}
			}
		}
		return 0;
	}
	
	//Recursive DFS algorithm
	private void dfs(int curr_node, int[][] visited) {
		//Set current node as visited
		//NOTE: this won't truncate possible paths if two or more nodes at the same level are adjacent (edge between them)
		//	which was the problem I found when I implemented BFS to solve this problem.
		visited[curr_node][0] = 1;
		//Loop through each possible adjacent node in the adjacency matrix
		for(int i = 0; i < this.adj_matrix.length; i++) {
			//If there is an adjacent node and hasn't been visited, then record its level and call dfs() again.
			if(this.adj_matrix[curr_node][i] == 1 && visited[i][0] != 1) {
				visited[i][1] = visited[curr_node][1] + 1;
				dfs(i, visited);
			}
		}
	}
	
	//Function wrapper for DFS function. Takes threshold (integer) as parameter for minimum path length
	//Returns 1 if threshold is surpassed, else return 0.
	public int largest_connected_dfs(int t) {
		int longest_path = 0;
		//Needs to loop through all existing nodes in graph and treat them as starting points
		for(int i = 0; i < this.adj_matrix.length; i++) {
			//Create a new visited 2D array to store visit history and level data
			int[][] visited = new int[this.adj_matrix.length][2];
			//Call recursive DFS function
			dfs(i, visited);
			//Compares longest path recorded with the visited level array
			//If there is a datapoint equal or greater than threshold, return 1, else just update the longest path record
			for(int j = 0; j < visited.length; j++) {
				if(visited[j][1] > longest_path) {
					longest_path = visited[j][1];
				}
				if(longest_path >= t) {
					return 1;
				}
			}
			//Print this line after looping through one row in the adjacency matrix just to show the path length from a given node.
			//Comment it if it is annoying.
			//System.out.println("Longest possible path from Node [" + i + "]: " + longest_path);
		}
		//After traversing the whole graph, if the threshold was not met then just return 0.
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