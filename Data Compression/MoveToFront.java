import java.util.*;

public class MoveToFront {
	// apply move-to-front encoding, reading from standard input and writing to standard output

    public static LinkedList<Character> createASCII(){
        LinkedList<Character> ascList = new LinkedList<Character>();
        for(char i = 0; i < 256; i++){
            ascList.add(i);
        }
        return ascList;
    }

    public static void encode()
	{
        char ch;
        LinkedList<Character> ascList = createASCII();
        while(!BinaryStdIn.isEmpty()) {
            ch = BinaryStdIn.readChar();
                //output index where each ch appears
                int index = ascList.indexOf(ch);
                BinaryStdOut.write((char)index); //CHECK FOR EDGE CASE
                //move ch to head
                char temp = ascList.remove(index);
                ascList.add(0, temp);
            //}
        }
        BinaryStdOut.flush();
	}
	// apply move-to-front decoding, reading from standard input and writing to standard output
	public static void decode()
	{
        LinkedList<Character> ascList = createASCII();
        while(!BinaryStdIn.isEmpty()) {
            char ch = BinaryStdIn.readChar();
            int ind = (int) ascList.remove(ch);
            BinaryStdOut.write((char)ind);
            ascList.add(0, (char) ind);
        }
        BinaryStdOut.flush();

    }
	// if args[0] is '-', apply move-to-front encoding
	// if args[0] is '+', apply move-to-front decoding
	public static void main(String[] args)
	{
        if(args[0].equals("-")){ //not singular quote?
            MoveToFront.encode();
        }
        else if(args[0].equals("+")){
            MoveToFront.decode();
        }
		
	}
}