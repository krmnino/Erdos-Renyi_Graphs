import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class Graph {
	private int[][] adj_matrix;
	
	public Graph(int[][] input) {
		if(input.length != input[0].length) {
			System.out.println("Adjacency Matrix must be square!");
		}
		else {
			this.adj_matrix = input;
		}
	}
	
	public Graph(int n, double p) {
		if(n <= 1 || (0 > p && p > 1)) {
			System.out.println("Invalid parameters. Make sure n > 1 and 0 <= p <= 1");
		}
		else {
			this.adj_matrix = new int[n][n];
			Random rand = new Random();
			for(int i = 0; i < this.adj_matrix.length; i++) {
				for(int j = 0; j < this.adj_matrix[i].length; j++) {
					if(i < j && rand.nextDouble() < p) {
						this.adj_matrix[i][j] = 1;
						this.adj_matrix[j][i] = 1;
					}
				}
			}
			
		}
	}
	
	public int largest_connected_bfs(int t) {
		int height;
		for(int i = 0; i < this.adj_matrix.length; i++) {
			Queue<Integer> queue = new LinkedList<>();
			Set<Integer> visited = new HashSet<>();
			queue.add(i);
			visited.add(i);
			height = 0;
			boolean pushed = false;
			while(!queue.isEmpty()) {
				int curr_node = queue.remove();
				for(int j = 0; j < this.adj_matrix.length; j++) {
					if(this.adj_matrix[curr_node][j] == 1 && !visited.contains(j)) {
						queue.add(j);
						visited.add(j);
						pushed = true;
					}
				}
				if(pushed) {
					height++;
					if (height >= t) { 
						return 1; 
					}
					pushed = false;
				}
			}
			System.out.println("Longest possible path from Node [" + i + "]: " + height);
			
		}
		return 0;
	}
	
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
