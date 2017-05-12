import java.util.Random;

public class PercolationStats{
//	Percolation object;
//	PercolationQuick objectQ;
	//public PercolationStats(int N, int T, String speed){
	//Do i need this? or can i use percolations matrix?
		/*int[][] matrix = new int[n][n];
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix.length-1; j++){
				matrix[i][j] = 0;
			}
		} */

		//randomly select an i and a j
		//obtain the p* estimate using number of openCells
	public static void main (String[] args){
		//int N = StdIn.readInt();
		//int T = StdIn.readInt();
		//String input = StdIn.readLine();
		//String[] arr = input.split(" ");
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		String speed = args[2];
		Percolation object = new Percolation(N);
		PercolationQuick objectQ = new PercolationQuick(N);
		int openCells;
		int count = 0;
		Stopwatch time = new Stopwatch();
		double[] thresholdArray = new double[T];
		double[] times = new double[T];

		if(speed.equals("fast")){
			while(count < T){
				openCells = 0;
				time = new Stopwatch();
				while(!object.percolates()){
					Random rand = new Random();
					int randi = rand.nextInt(N) + 0;
					int randj = rand.nextInt(N) + 0;

					if(!object.isOpen(randi, randj)){
						object.open(randi, randj);
						openCells++;
					}
			//keep elapsing the time and adding it on
					
				}
				times[count] = time.elapsedTime();
				double p = ((double) openCells)/(N*N);
				thresholdArray[count] = p;
				count++;
				object = new Percolation(N);
			}
		}

		else if(speed.equals("slow")){
			while(count < T){
				openCells = 0;
				time = new Stopwatch();
				while(!objectQ.percolates()){
					Random rand = new Random();
					int randi = rand.nextInt(N) + 0;
					int randj = rand.nextInt(N) + 0;

					if(!objectQ.isOpen(randi, randj)){
						objectQ.open(randi, randj);
						openCells++;
					}
			//keep elapsing the time and adding it on
				}
				times[count] = time.elapsedTime();
				double p = (double) openCells/(N*N);
				thresholdArray[count] = p;
				count++;
				objectQ = new PercolationQuick(N);
			}
		}

		double mean; 
		mean = StdStats.mean(thresholdArray);
		double stdDev;
		stdDev = StdStats.stddev(thresholdArray);
		double meanTime;
		meanTime = StdStats.mean(times);
		double stdDevTime;
		stdDevTime = StdStats.stddev(times);
		double ttime = 0;
		for(int i = 0; i < times.length-1; i++){
			ttime = ttime + meanTime;
		}
		System.out.println("mean threshold=" + mean);
		System.out.println("std dev=" + stdDev);
		System.out.println("time=" + ttime);
		System.out.println("mean time=" + meanTime);
		System.out.println("stddev time=" + stdDevTime);

	}
	
}
