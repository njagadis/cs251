/**
 * Created by Nikitha on 4/26/17.
 */

import java.util.Comparator;

public class StringCompare implements Comparator<StringCompare>{

    private final int length;
    private int location; //location of this

    public int getLoc(){
        return location;
    }

    public StringCompare(int location, int length){
        this.location = location;
        this.length = length;
    }

    public int compareTo(StringCompare that) {
        String str = BurrowsWheeler.s;
        for (int i = 0; i < (this.length); ) {
            if (str.charAt(this.location + i) < str.charAt(that.location + i)) {
                return -1;
            } else if (str.charAt(this.location + i) > str.charAt(that.location + i)) {
                return 1;
            } else if (str.charAt(this.location + i) == str.charAt(that.location + i)) {
                //increment to next letters in both strings
                i++;
            }
        }
        return 0;
    }


    @Override
    public int compare(StringCompare o1, StringCompare o2) {
        return 0;
    }
}
