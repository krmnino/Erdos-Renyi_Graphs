import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) {
		int[][] adj_mtrx = {{0,0,0,1,0},{0,0,0,1,1},{0,0,0,0,0},{1,1,0,0,1},{0,1,0,1,0}};
		int[][] adj_mtrx2 = {	{0,1,0,0,0},
								{1,0,1,0,0},
								{0,1,0,1,0},
								{0,0,1,0,1},
								{0,0,0,1,0}};
		//0-1-2-3-4
		Graph test = new Graph(adj_mtrx2);
		test.largest_connected_bfs(5);
		
		/*
		int n = 40;
		double p;
		double[] c = {0.2, 0.4, 0.6, 0.8, 1.0, 1.2,	1.4, 1.6, 1.8, 2.0, 2.2, 2.4, 2.6, 2.8, 3.0};
		for(int i = 0; i < c.length; i++) {
			p = c[i] / (double)n;
			int threshold_met = 0;
			for(int j = 0; j < 500; j++) {
				Graph g  = new Graph(n, p);
				if(g.largest_connected_bfs(30) == 1) {
					threshold_met++;
					System.out.println(threshold_met);
				}
			}
			System.out.println(c[i] + " - " + threshold_met / 500.0);
		}
		try {
			ProcessBuilder builder = new ProcessBuilder("python");
			Process proc = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(proc.getInputStream()));
	        String line;
	        while (true) {
	            line = r.readLine();
	            if (line == null) { break; }
	            System.out.println(line);
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}
