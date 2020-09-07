import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		/* TESTING
		int[][] adj_mtrx2 = {	{0,1,0,0,0},
								{1,0,1,0,0},
								{0,1,0,1,0},
								{0,0,1,0,1},
								{0,0,0,1,0}};
		
		int[][] adj_mtrx = {	{0,1,1,0,0,0,1},
								{1,0,1,1,0,0,0},
								{1,1,0,1,1,1,0},
								{0,1,1,0,0,0,0},
								{0,0,1,0,0,0,0},
								{0,0,1,0,0,0,0},
								{1,0,0,0,0,0,0}};
		
		Graph test = new Graph(adj_mtrx);
	    System.out.println(test.largest_connected_dfs(5));
		*/
		int n = 40;
		double p;
		double[] c = {0.2, 0.4, 0.6, 0.8, 1.0, 1.2,	1.4, 1.6, 1.8, 2.0, 2.2, 2.4, 2.6, 2.8, 3.0};
		String stats = "";
		for(int i = 0; i < c.length; i++) {
			p = c[i] / (double)n;
			int threshold_met = 0;
			for(int j = 0; j < 500; j++) {
				Graph g  = new Graph(n, p);
				if(g.largest_connected_dfs(20) == 1) {
					threshold_met++;
				}
			}
			stats += c[i] + "," + threshold_met / 500.0 + "\n";
		}
		try 
		{
			BufferedWriter txtfile = new BufferedWriter(new FileWriter("stats.csv"));
			txtfile.write(stats);
			txtfile.close();
			System.out.println("Stats saved in stats.csv.");
		}
		catch(IOException e)
		{
			System.out.println("Could not write to file.");
		}	
	}
}
