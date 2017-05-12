//import com.sun.org.apache.xpath.internal.SourceTree;
import java.util.Scanner;

/**
 * Created by njagadis on 1/25/17.
 */
public class Percolation {
    public int n; //this is my variable n
    public int[] matrix;
    private WeightedQuickUnionUF object;
    private int topRow;
    private int bottomRow;
	
    public Percolation(int n){
        object = new WeightedQuickUnionUF(n*n+2);
        this.n = n; // makes the n in this code reference stdin's n
        int[] matrix = new int[n*n];
        this.matrix = matrix; //do i need this?!!!!!
        for(int i = 0; i < matrix.length; i++){
                matrix[i] = 0;
            }
	topRow = n*n;
	bottomRow = n*n+1;
    }

    public void open(int x, int y){
        int spot; //will locate the correct place in the 1D array to open
        spot = x * this.n + y;
        //int i = (n*n)-n;
        boolean flag = false;
        int adjSpot1 = x*this.n + (y-1);
        int adjSpot2 = (x+1)*this.n + y;
        int adjSpot3 = x*this.n + (y+1);
        int adjSpot4 = (x-1)*this.n + y;
        //check if open and adjacent, then union it	
	   if (spot < n*n && spot >= (n*n)-n){ //if index is anywhere in between top column numbers
            matrix[spot] = 2;
            flag = true;
	    object.union(spot, topRow);		
        }
        else if (spot < (n*n)-n) { //if index isn't in top column numbers
		if(this.isFull(x,y)){
			matrix[spot] = 2;
			flag = true;
		}
            }
        if(flag == false) matrix[spot] = 1;



            if(matrix[spot] == 1 || matrix[spot] == 2) {
            //System.out.println("hello1 " + adjSpot1 + " " + adjSpot2 + " " + adjSpot3 + " " + adjSpot4);
	    if(adjSpot1 >= 0 && adjSpot1 <= (n*n)-1 && (y-1) >= 0){ 
            if(matrix[adjSpot1] == 1 || matrix[adjSpot1] == 2){
                object.union(spot, adjSpot1);
		//System.out.println("hello");
            }
	    }
	    if(adjSpot2 >= 0 && adjSpot2 <= (n*n)-1 && (x+1) < n){ 
            if(matrix[adjSpot2] == 1 || matrix[adjSpot2] == 2){
                object.union(spot, adjSpot2);
		//System.out.println("Hello2");
            }
	    }
	    if(adjSpot3 >= 0 && adjSpot3 <= (n*n)-1 && (y+1) < n){
            if(matrix[adjSpot3] == 1 || matrix[adjSpot3] == 2){
                object.union(spot, adjSpot3);
            }
	    }
	    if(adjSpot4 >= 0 && adjSpot4 <= (n*n)-1 && (x-1) >= 0){
            if(matrix[adjSpot4] == 1 || matrix[adjSpot4] == 2){
                object.union(spot, adjSpot4);
		//Madhuuu
	//	if (object.connected(adjSpot4, 0))
            }
	    }

	    //if bottom row open union with bottomRow
	    if(x == 0){
		object.union(spot, bottomRow);
	    }

        }
    }

    public boolean isOpen(int x, int y){
        int spot = x*this.n+y;
        if(matrix[spot] == 1 || matrix[spot] == 2){
            return true;
        }
        else return false;
    }

    public boolean isFull(int x, int y){
        //surface is last n items in my 1D array
        int spot = x*this.n+y;
	if(object.connected(spot, topRow)){
		matrix[spot] = 2;
		return true;
	}
	return false;
	/*for(int i = (n*n)-n; i <= (n*n)-1; i++){
		if(object.connected(spot, i)){
			matrix[spot] = matrix[i];
			return true;
		}
	}
       // if(matrix[spot] == 2) return true;
       	return false;
	*/
    }

    public boolean percolates(){
       /* for(int i = 0; i < n-1; i++){
            if(isFull(0, i)){ //this might be wrong!
                return true;
            }
        }
       return false;
       */
     //  int topRow = n * (n-1);
/*	for(int i = 0; i <= n-1; i++){
		if(isFull(0,i)){
			return true;
		}
	}
       // if(matrix[spot] == 2) return true;
       	return false;
	*/
	if(object.connected(topRow,bottomRow)){
		return true;
	}
	else return false;
 
    }

    public static void main(String[] args){
	/*Percolation p = new Percolation(3);
	p.open(0,0);
	System.out.println(p.percolates());
	p.open(1,0);
	System.out.println(p.percolates());
	p.open(2,0);
        System.out.println(p.percolates());
	*/
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	Percolation p = new Percolation(n);
	while(scanner.hasNext()){
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		p.open(x,y);
	}
       if(p.percolates()) {
            System.out.println("Yes");
        }
        else { System.out.println("No"); }
    }
}


