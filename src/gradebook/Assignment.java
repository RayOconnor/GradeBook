package gradebook;

/**
 * class to represent assignments
 * allen.ele@husky.neu.edu, patel.pre@husky.neu.edu, roconnorc@ccs.neu.edu
 * @author Elena Allen, Premal Patel, Ray O'Connor
 * @version 2014-04-11
 */
public class Assignment {

    String name;
    double totalPossible;
    double weight;
    
    public Assignment(String name, double totalPossible, double weight) {
        this.name = name;
        this.totalPossible = totalPossible;
        this.weight = weight;
    }
    
}

