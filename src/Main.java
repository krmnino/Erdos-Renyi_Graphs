import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		/* TESTING
		int[][] adj_mtrx1 = {	{0,1,0,0,0},
								{1,0,1,0,0},
								{0,1,0,1,0},
								{0,0,1,0,1},
								{0,0,0,1,0}};
		
		int[][] adj_mtrx2 = {	{0,1,1,0,0,0,1},
								{1,0,1,1,0,0,0},
								{1,1,0,1,1,1,0},
								{0,1,1,0,0,0,0},
								{0,0,1,0,0,0,0},
								{0,0,1,0,0,0,0},
								{1,0,0,0,0,0,0}};
		
		Graph test = new Graph(adj_mtrx1);
	    System.out.println(test.largest_connected_dfs(5));
		*/
		int n = 40;
		double p;
		double[] c = {0.2, 0.4, 0.6, 0.8, 1.0, 1.2,	1.4, 1.6, 1.8, 2.0, 2.2, 2.4, 2.6, 2.8, 3.0};
		String stats = "";
		//Loop through each c value
		for(int i = 0; i < c.length; i++) {
			//Calculate the p number as the current value divided by the number of nodes (40)
			p = c[i] / (double)n;
			//Counter that records number of graphs that meets the threshold
			int threshold_met = 0;
			//Create the 500 graphs and check if they have at least 30 connected components
			for(int j = 0; j < 500; j++) {
				Graph g  = new Graph(n, p);
				//If the graph meets the threshold, then increase by 1
				if(g.largest_connected_component(30) == 1) {
					threshold_met++;
				}
			}
			//Write string representing tuple <c_value,percent>
			stats += c[i] + "," + threshold_met / 500.0 + "\n";
		}
		
		//Export statistics to stats.csv
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
