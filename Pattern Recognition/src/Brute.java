/**
 * Created by njagadis on 2/8/17.
 */

import java.util.Arrays;
import java.io.PrintWriter;

public class Brute {
    private Point pt;
    private int size;
    public PrintWriter pw;
    //File fout = new File("visualPoints.txt");

    Point[] arr;
    public Brute(int size) {
        try{
            pw = new PrintWriter("visualPoints.txt");
        } catch(Exception e){
            System.out.println("File error");
        }
        this.size = size;
        arr = new Point[size];
        int index = 0;
        while(!(StdIn.isEmpty())) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            pt = new Point(x,y);
            arr[index] = pt;
            index++;
        }
        Arrays.sort(arr);
    }

    //examines 4 points at a time and checks if collinear. if true, then print
    public void printCollinear() {
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        for(i = 0; i < arr.length; i++){
            //System.out.println("hello i");
            for(j = i+1; j < arr.length; j++){
                //System.out.println("helloj");
                for(k = j+1; k < arr.length; k++){
                    if(pt.areCollinear(arr[i], arr[j], arr[k])) { //if 3 points aren't collinear
                        for (l = k + 1; l < arr.length; l++) {
                            //System.out.println("hello L");
                            if (pt.areCollinear(arr[i], arr[j], arr[k], arr[l])) { //are points collinear
                                System.out.print("4:"); //if so, print (first the # of points)
                                pw.write("4:");
                                pw.flush();
                                sortArray(arr[i], arr[j], arr[k], arr[l]); //call sort function
                            }
                        }
                    }
                }
            }
        }
    }

    //sort collinear Points in ascending order lexographically
    public void sortArray(Point a, Point b, Point c, Point d) {
        Point[] sorted = {a, b, c, d};
        /*implement bubble sort
        boolean swapped = true;
        int j = 0;
        Point tmp;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < sorted.length - j; i++) {
                if (sorted[i].compareTo(sorted[i + 1]) == 1) { //this is greater than that
                    tmp = sorted[i];
                    sorted[i] = sorted[i + 1];
                    sorted[i + 1] = tmp;
                    swapped = true;
                }
            }
        }
        */
        Arrays.sort(sorted);

        //prints all 4 elements in sorted array
        for(int k = 0; k < 4; k++) {
            System.out.print("(" + sorted[k].getX() + ", " + sorted[k].getY() + ")");
            pw.write("(" + sorted[k].getX() + ", " + sorted[k].getY() + ")");
            pw.flush();
            if(k+1 < 4){
                System.out.print(" -> ");
                pw.write(" -> ");
                pw.flush();
            }
            else {
                System.out.println();
                pw.write("\n");
                pw.flush();
            }
        }
    }

    public static void main(String[] args){
        Brute b = new Brute(StdIn.readInt());
        b.printCollinear();

        /*int size = StdIn.readInt();
        Point[] arr = new Point[size];
        int index = 0;
        while(!(StdIn.isEmpty())) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            Point pt = new Point(x,y);
            arr[index] = pt;
            index++;
        }
        */
       // b.printCollinear(arr);

        //for(int i = 0; i < size; i++) {
           // System.out.println(arr[i]);
        //
    }


}
