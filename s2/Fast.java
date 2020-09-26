package s2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.QuickX;

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
        Point invoking_point;
        sortIterPoints();
        for (Point iter_point : this.iter_points) {
            invoking_point = iter_point;
            sortSlopePoints(invoking_point);
            findEqualSlopes(invoking_point);
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
                if(this.points[i].compareTo(invoking_point)<0){
                    is_dupe = true;
                }
                counter++;

                // EDGE CASE: If i=this.points.length-1 and the slopes are equal, the line never gets printed
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
                slope1 = this.points[i].slopeTo(invoking_point);
                counter=1;
            }
        }
    }

    public static void main(String [] args) throws InvalidAttributeValueException {
        In in = new In();
        int n = in.readInt();
        Fast fast = new Fast(n);
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            fast.points[i] = new Point(x, y);
            fast.iter_points[i]= new Point(x, y);
        }
        fast.getCollinear();
    }
}

/*
    public void findEqualSlopes(Point invoking_point){
        double end_slope, start_slope;
        int start, end;
        start=0; end=1;
        boolean is_dupe;
        is_dupe = this.points[start].compareTo(invoking_point)>0; // Duplicate if point < invoking point
        // Setting initial slope
        start_slope = this.points[start].slopeTo(invoking_point);// The current slope we are comparing to
        while(end<this.points.length){
            end_slope = this.points[end].slopeTo(invoking_point);
            // If the end slope is the same as the start slope we have collinear points
            if(end_slope==start_slope){
                end++;
            }
            // If the end slope is NOT the same as the start slope we start with a new slope
            else{
                // First check if we got to at least 3 points with same slope to invoking point
                if(end-start>=3){
                    printPoints(start, end, invoking_point); // Print them
                }
                start=end; // Restart
                end++; // add to end
            }
        }
    }
 */
/*public void findEqualSlopes(Point invoking_point){
        double end_slope, start_slope;
        int start, end;
        start=1; end=2;
        boolean is_dupe;
        is_dupe = this.points[start].compareTo(invoking_point)<0; // Duplicate if point < invoking point
        // Setting initial slope
        start_slope = this.points[start].slopeTo(invoking_point);// The current slope we are comparing to
        while(end<this.points.length){
            // If the end slope is the same as the start slope we have collinear points

            end_slope = this.points[end].slopeTo(invoking_point); // The current slope we are iterating over

            if (end_slope!=start_slope){
                if(end-start>=3 &&!is_dupe){
                    printPoints(start, end, invoking_point);
                }
                start = end;
                start_slope = end_slope;
                is_dupe = this.points[start].compareTo(invoking_point)<0; // Duplicate if point < invoking point
            }
            if (this.points[end].compareTo(invoking_point)<0){
                is_dupe = true; // Duplicate if point < invoking point
            }
            end++;


            // If the last end++ also is in the collinear set THIS IS PROBLEM
            if(end==this.points.length-1){
                if(this.points[end].slopeTo(invoking_point)==start_slope){
                    if(end-start>=3 &&!is_dupe){
                        printPoints(start, end, invoking_point);
                    }
                }
            }
        }
    }*/