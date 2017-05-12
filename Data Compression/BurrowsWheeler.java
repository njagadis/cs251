import java.util.*;
import java.util.Comparator;

public class BurrowsWheeler {
    static String s;
    static int counter;
    // apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    public static void encode() {
        s = BinaryStdIn.readString();
        int len = s.length(); //length of word
        StringCompare[] startInd = new StringCompare[len]; //Object array of startInd and size
        s += s; //string is now abcdabcd
        for (int i = 0; i < len; i++) {
            startInd[i] = new StringCompare(i, len); //doesn't store the string itself
        }

        //sort the startInd array
        Arrays.sort(startInd, new Comparator<StringCompare>() { //says sorting objects
            @Override
            public int compare(StringCompare o1, StringCompare o2) {
              //  String s1 = s.substring(o1.getLoc(), o1.getLoc() + len);
               // String s2 = s.substring(o2.getLoc(), o2.getLoc() + len);
              //  return s1.compareTo(s2); //this is the string's compareTo function, not the object's.
                return o1.compareTo(o2);
            }
        });
        //^pso said that this was the reason my code was taking so long to run, specifically substring

        String t = new String();
        //make the t string/encoded string
        for (int j = 0; j < len; j++) {
            t += s.charAt((startInd[j].getLoc()) + (len - 1));
        }
        for(int d = 0; d < len; d++) {
            if(startInd[d].getLoc() == 0) {
                BinaryStdOut.write(d);
                //System.out.print(d);
            }
        }
        BinaryStdOut.write(t);
        //System.out.print(t);
        BinaryStdOut.flush();
    }

        /****WORKING BUT SPACE INEFFICIENT CODE****
         String[] ogSuffixes = new String[s.length()];
        ogSuffixes[0] = s;
        for(int i = 1; i < s.length(); i++){ //add a new val to the array at each increment
                ogSuffixes[i] = rotateString(ogSuffixes[i - 1]);
        }
        Arrays.sort(ogSuffixes);
        System.out.println(Arrays.binarySearch(ogSuffixes, s)); //logn, returns an int(index)

        String t = new String();
        //make the t string (the encoded string)
        for(int k = 0; k < s.length(); k++) {
            //each index.charat(length-1)
            t += ogSuffixes[k].charAt(s.length()-1);
        }

        System.out.println(t);

    }
         public static String rotateString(String s) {
        //System.out.println(s);
        String[] charArr;
        String updatedString = new String();
        //string to array of characters
        charArr = s.split("(?!^)");

        //rotate suffixes
        int j;
        String temp = charArr[0];
        for (j = 0; j < s.length() - 1; j++) {
            updatedString += charArr[j + 1];
        }
        updatedString += temp;
        return updatedString;
    }
    */

    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode()
    {
        //you're reading in T, that's the encoded string.
        int l = BinaryStdIn.readInt();
        String t = BinaryStdIn.readString();
        StringBuilder tcpy = new StringBuilder(t);

        String[] tArr;
        tArr = t.split("(?!^)");
        Arrays.sort(tArr); //sortedT

        int[] next = new int[t.length()];

        //fills next array with -1's
        for(int k = 0; k < next.length; k++){
            next[k] = -1;
        }
        //creates the next array
        for(int i = 0; i < next.length; i++){
            int passVal = tcpy.indexOf(tArr[i]);
            if(checkRepeats(next, passVal)){
                tcpy.setCharAt(counter, 'ø');
                next[i] = tcpy.indexOf(tArr[i]);
            }
            else{
                next[i] = tcpy.indexOf(tArr[i]);
                //make sure to fill every null with a 0
            }
        }
        for(int count = 0; count < next.length; count++){
            l = next[l];
            BinaryStdOut.write(t.charAt(l));
            //System.out.println(t.charAt(l));
        }
        BinaryStdOut.flush();

    }

    public static boolean checkRepeats(int[] next, int passVal){
        //loop thru the next array and see if the index is in there
        boolean fl = false;
        for(int i = 0; i < next.length; i++){
            if(next[i] == passVal){ //CHANGED
                counter = passVal;
                return true;
            }
            else
                fl = false;
        }
        return fl;
    }
    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args)
    {
        //BurrowsWheeler obj = new BurrowsWheeler();
        if(args[0].equals("-")){ //not singular quote?
            BurrowsWheeler.encode();
        }
        else if(args[0].equals("+")){
            BurrowsWheeler.decode();
        }

    }
}