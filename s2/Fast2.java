package s2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.QuickX;

import javax.management.InvalidAttributeValueException;
import java.util.Arrays;

public class Fast2 {
    private final Point [] points;
    private final Point [] iter_points;

    public Fast2(int n) throws InvalidAttributeValueException {
        if(n<4) throw new InvalidAttributeValueException("n must be at least 4");
        this.points = new Point[n];
        this.iter_points = new Point[n];
    }

    public void sortSlopePoints(Point p0){
        // Takes at most 2N space while sorting
        // Takes NlogN time
        MergeX.sort(this.points, p0.SLOPE_ORDER);
    }

    public void printPoints(int start, int end, Point invoking_point){
        Arrays.sort(this.points, start, end);
        StringBuilder out_str = new StringBuilder();
        out_str.append(invoking_point.toString());
        out_str.append(" -> ");
        for (int i=0; i<end-start; i++){
            out_str.append(this.points[start+i].toString());
            if(i<end-start-1) out_str.append(" -> ");
        }
        Out out = new Out();
        out.println(out_str);
    }

    public void sortIterPoints(){
        QuickX.sort(this.iter_points);
    }


    public void getCollinear(){
        sortIterPoints(); // Sorting points that we will iterate over
        for (Point iter_point : this.iter_points) {
            sortSlopePoints(iter_point); // Sort the second array according to slopes they make with iter_point
            findEqualSlopes(iter_point); // Find slopes that form lines of at least 4
        }
    }

    public void findEqualSlopes(Point invoking_point) {
        int counter=1; // Counts how many slopes in a row are the same
        double slope1, slope2;
        slope1 = this.points[1].slopeTo(invoking_point);
        // DUPLICATES: Invoking point should always we the smallest point. Otherwise it's a duplicate.
        // If returns less than 0, current point is smaller than invoking point
        boolean is_dupe = this.points[1].compareTo(invoking_point)<0;
        for(int i=2; i<this.points.length; i++){
            slope2 = this.points[i].slopeTo(invoking_point);
            // If the slopes are equal they are form a line together
            if(slope1==slope2){
                // If current point is smaller than invoking point we have a duplicate
                if(this.points[i].compareTo(invoking_point)<0){
                    is_dupe = true;
                }
                counter++;

                // If i=N-1 and the slopes are equal, the line never gets printed
                // We therefore check specifically for that case
                if(i==this.points.length-1){
                    if(counter>=3 &&!is_dupe){
                        printPoints(i-counter+1, i+1, invoking_point);
                    }
                }
            }
            // If the slopes are not equal, we no longer have a point in the collinear set
            else {
                // If the count of points is 3 or more we have at least 4 points forming a line
                if(counter>=3 &&!is_dupe){
                    printPoints(i-counter, i, invoking_point);
                }
                slope1 = this.points[i].slopeTo(invoking_point); // set new comparison slope to current slope
                is_dupe = this.points[i].compareTo(invoking_point)<0; // true if current point is smaller than inv.p
                counter=1; // Reset count
            }
        }
    }

    public static void main(String [] args) throws InvalidAttributeValueException {
        In in = new In();
        int n = in.readInt();
        Fast2 fast2 = new Fast2(n);
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            fast2.points[i] = new Point(x, y);
            fast2.iter_points[i]= new Point(x, y);
        }
        fast2.getCollinear();
    }
}
