import java.io.PrintWriter;
import java.util.Arrays;
import java.util.*;

/**
 * Created by Nikitha on 2/19/17.
 */
public class Fast {
    private Point pt;
    private int size;
    public PrintWriter pw;

    Point[] arr;
    Point[] copy; //copy of arr (this will be the sorted by angles one)
    // ArrayList<Float> storedAngles = new ArrayList();

    public Fast(int size) {
        try {
            pw = new PrintWriter("visualPoints.txt");
        } catch (Exception e) {
            System.out.println("File error");
        }
        this.size = size;
        arr = new Point[size];
        int index = 0;
        while (!(StdIn.isEmpty())) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            pt = new Point(x, y);
            arr[index] = pt;
            index++;
        }
        Arrays.sort(arr); //TEST1
    }

    public void makeAngles() { //for recursion, make parameter int p. Base case is N.
        int count = 0; // = N
        Point temp;
        copy = arr.clone(); //copy of arr (this will be the sorted by angles one)
        int linearCounter = 1; //counts how many points including reference pt are collinear
        while (count < size) { //runs N times (b/c N different reference points)
            linearCounter = 1;
            int j = 0; //edge case variable
            //sort the angleArray according to angle using arrays.sort and comparator
            Arrays.sort(copy, arr[count].BY_SLOPE_ORDER); //angle in reference to 2 other points
            // if (count == 0) {
            //  for (int k = 0; k < size; k++) {
            //      System.out.println("p value:" + count + "copyx: " + copy[k].getX() + "copyy: " + copy[k].getY() + "copyangle: " + copy[k].angle);
            //  }
            // }
            for (int i = 0; i < size - 1; i++) {  //changed DIS
                if (i == 0) {
                    if ((copy[0].getX() == arr[count].getX()) && (copy[0].getY() == arr[count].getY())) {
                    } else {
                        while (copy[j].angle == 0) { //looks thru all the 0 degrees
                            if ((copy[j].getX() == arr[count].getX()) && (copy[j].getY() == arr[count].getY())) {
                                //swap copy j with copy[0]
                                temp = copy[0];
                                copy[i] = copy[j];
                                copy[j] = temp;
                            }
                            j++;
                        }
                    }
                } else if (((i + 1) < (size - 1))) { //as long as i+1 < size-1; (normal not edge case do like did b4)
                    if (copy[i].angle == copy[i + 1].angle) {
                        linearCounter++;
                    } else if (linearCounter >= 3) { //we've found a collinear line
                        int startingIndex = (i + 1) - linearCounter;
                        int endIndex = i;
                        lexoSort(endIndex, startingIndex, linearCounter);
                        linearCounter = 1;
                       // break;
                    }
                    else { linearCounter = 1; }
                } else {
                    if (copy[i].angle == copy[i + 1].angle) {
                        linearCounter++;
                        i++;
                    }
                    if (linearCounter >= 3) {
                        int startingIndex = (i + 1) - linearCounter;
                        int endIndex = i;
                        lexoSort(endIndex, startingIndex, linearCounter);
                        linearCounter = 1;
                       // break;
                    }
                    else {linearCounter = 1; }
                }
            }
        copy = arr.clone();
        count++;
        }

    }

    public void lexoSort(int end, int start, int colon) {
        ArrayList<Point> list = new ArrayList<Point>();
        list.add(copy[0]);
        int count = start;
        while (count <= end) {
            list.add(copy[count]);
            //Collections.sort(list);
            count++;
        }
        if(list.get(0).compareTo(list.get(1)) != -1) {
            list.clear();
        }
        else{
            System.out.print(colon + 1 + ":");
            pw.write(colon + 1 + ":");
            pw.flush();
            for (int index = 0; index < list.size(); index++) {
                System.out.print("(" + list.get(index) + ")");
                pw.write("(" + list.get(index) + ")");
                pw.flush();
                //System.out.println("reference pt x: " + arr[count].getX());
                if (index != list.size() - 1) {
                    System.out.print(" -> ");
                    pw.write(" -> ");
                    pw.flush();
                }
            }
            System.out.println();
            pw.write("\n");
            pw.flush();
        }
    }

    public static void main(String[] args) {
        Fast f = new Fast(StdIn.readInt());
        f.makeAngles();
    }
}
