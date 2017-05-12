import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.*;

public class PercolationVisualizer{
	private int n; //this is my variable n
	private Percolation objectP;
	public PrintWriter pw;	
	File fout = new File("visualMatrix.txt");
	public PercolationVisualizer(int n){
		try{
		pw = new PrintWriter("visualMatrix.txt");	
		} catch(Exception e){
			System.out.println("File error");
		}
		this.n = n; //my n references stdin's n
		objectP = new Percolation(n);
	}

//public static void writeFile() throws IOException{
	//FileWriter fw = new FileWriter("visualMatrix.txt");
	//fw.write();
	//fw.close();
//}

public void intermediateSteps(int x,int y){
	objectP.open(x,y);
	for(int k = 0; k < n; k++){
		for(int z = 0; z < n; z++){
			objectP.isFull(z,k);
		}	
	}
	int count = (n*n)-n;
	for(int j = 1; j <= n; j++){
		System.out.println();
		pw.write("\n");
		pw.flush();
		for(int i = 0; i < n; i++){
			System.out.print(objectP.matrix[count]);
			System.out.print(" ");
			pw.write(objectP.matrix[count] + " ");
			pw.flush();
			count++;
		}
		count = (n*n)-(j+1)*n;
	}
	pw.write("\n");
	pw.flush();
}

/*public void FileWrite(String textLine) throws IOException{
	//FileWriter write = new FileWriter(visualMatrix.txt, 
	private static PrintWriter pw = new PrintWriter(new OutputStreamWriter(, "UTF-8"), true);
	pw.println(textLine);

}
*/


public static void main(String[] args){
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	PercolationVisualizer q = new PercolationVisualizer(n);	
	System.out.println(n);
	q.pw.write(n + "\n");
	q.pw.flush();
	while(scanner.hasNext()){
		int x = scanner.nextInt();
		int y = scanner.nextInt();
	/*	for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
			if(q.objectP.isFull(i,j)){
				int spot = i*n+j;
				q.objectP.matrix[spot] =2;
			}
			}
		}
		*/
		q.intermediateSteps(x,y);//goes thru and returns matrix at that step
		System.out.println();
		q.pw.write("\n");
		q.pw.flush();
	}	
}

}
