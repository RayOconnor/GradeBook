package gradebook;

import java.util.Comparator;

/**
 * comparator class for comparing assignments
 * allen.ele@husky.neu.edu, patel.pre@husky.neu.edu, roconnorc@ccs.neu.edu
 * @author Elena Allen, Premal Patel, Ray O'Connor
 * @version 2014-04-11
 */

public class AssignmentComparator implements Comparator<Assignment> {


    /**
     * compares the two given Students and returns a negative if 
     * the second student's username is comes after alphabetically
     * 
     * @param s1 the first student to be compared
     * @param s2 is the second integer to be compared
     * @return a negative if s2 is greater than s1
     * 
     */
    public int compare(Assignment a1, Assignment a2) {

        return a1.name.compareTo(a2.name);
    }

    

}
