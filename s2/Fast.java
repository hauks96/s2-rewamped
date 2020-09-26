package s2;

import edu.princeton.cs.algs4.*;

import javax.management.InvalidAttributeValueException;
import java.util.Arrays;

public class Fast {
    private final Point [] points;
    private final Point [] iter_points;

    public Fast(int n) throws InvalidAttributeValueException {
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
        Arrays.sort(this.points, start, end);  // Sort the points
        StringBuilder out_str = new StringBuilder();
        out_str.append(invoking_point.toString()); // Always the fist point
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
        // Iterate over all points in the newly sorted list
        // The list is sorted by slope size they make with invoking point
        for(int i=2; i<this.points.length; i++){

            // If the previous point is smaller than the invoking point we no longer need it for comparisons.
            // We set it to zero to make sure it doesn't interfere later
            if (this.points[i-1].compareTo(invoking_point)<0) {
                this.points[i-1] = new Point(0,0);
            }

            slope1 = this.points[i-1].slopeTo(invoking_point);
            slope2 = this.points[i].slopeTo(invoking_point);
            // If the slopes are equal they form a line together
            // If both current points are greater than the invoking point we have a valid line between the two points
            if(slope1==slope2 && this.points[i].compareTo(invoking_point)>0 && this.points[i-1].compareTo(invoking_point)>0){
                counter++;

                // If i=this.points.length-1 and the slopes are equal, the line never gets printed
                // We therefore check specifically for that case
                if(i==this.points.length-1){
                    if(counter>=3){
                        printPoints(i-counter+1, i+1, invoking_point);
                    }
                }
            }
            // If the slopes are not equal, we no longer have a point in the collinear set
            else {
                // If the count of points is 3 or more we have at least 4 points forming a line
                if(counter>=3){
                    printPoints(i-counter, i, invoking_point);
                }
                counter=1; // Reset count
            }
        }
    }

    public static void main(String [] args) throws InvalidAttributeValueException {
        In in = new In();
        int n = in.readInt();
        Fast fast = new Fast(n);
        var stopwatch = new Stopwatch();
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            fast.points[i] = new Point(x, y);
            fast.iter_points[i]= new Point(x, y);
        }
        fast.getCollinear();
        var timespent = stopwatch.elapsedTime();
        StdOut.println("Time: " + timespent);
    }
}

/*
if(slope1==slope2 && this.points[i].compareTo(invoking_point)>0 && this.points[i-1].compareTo(invoking_point)>0){
                // If current point is greater than invoking point and the slopes are equal
                counter++;

                // EDGE CASE: If i=this.points.length-1 and the slopes are equal, the line never gets printed
                // We therefore check specifically for that case
                if(i==this.points.length-1){
                    if(counter>=3){
                        printPoints(i-counter+1, i+1, invoking_point);
                    }
                }
            }
 */