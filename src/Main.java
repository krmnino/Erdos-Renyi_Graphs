public class Main {
	
	public static void main(String[] args) {
		Graph g  = new Graph(5, 0.6);
		
		int[][] adj_mtrx = {{0,0,0,1,0},{0,0,0,1,1},{0,0,0,0,0},{1,1,0,0,1},{0,1,0,1,0}};
		
		int[][] adj_mtrx2 = {	{0,1,0,0,0},
								{1,0,1,0,0},
								{0,1,0,1,0},
								{0,0,1,0,1},
								{0,0,0,1,0}};
		Graph z = new Graph(adj_mtrx);
		
		System.out.println(z.toString());
		System.out.println(z.largest_connected_bfs(3));
	}
}
