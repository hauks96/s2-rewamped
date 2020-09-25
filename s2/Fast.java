package s2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.MergeX;

import javax.management.InvalidAttributeValueException;
import java.util.Arrays;

public class Fast {
    private final Point [] points;
    private final Point [] iter_points;

    public Fast(int n) throws InvalidAttributeValueException {
        if(n<4) throw new InvalidAttributeValueException("n must be at least 4");
        this.points = new Point[n];
        this.iter_points = new Point[n];
        addPoints();
    }

    public void addPoints(){
        In in = new In();
        for(int i=0; i<this.points.length; i++){
            int x = in.readInt(), y = in.readInt();
            this.points[i] = new Point(x, y);
            this.iter_points[i] = new Point(x, y);
        }
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


    public void getCollinear(){
        Point invoking_point;
        for (Point iter_point : this.iter_points) {
            invoking_point = iter_point;
            sortSlopePoints(invoking_point);
            findEqualSlopes(invoking_point);
        }
    }

    public void findEqualSlopes(Point invoking_point){
        double end_slope, start_slope;
        int start, end;
        start=1; end=2;
        boolean is_dupe;
        // Setting initial slope
        is_dupe = this.points[start].compareTo(invoking_point)>0;
        start_slope = this.points[start].slopeTo(invoking_point);// The current slope we are comparing to
        while(end<this.points.length){
            // If the end slope is the same as the start slope we have collinear points
            end_slope = this.points[end].slopeTo(invoking_point); // The current slope we are iterating over

            if (end_slope!=start_slope){
                if(end-start>=3 &&!is_dupe){
                    printPoints(start, end, invoking_point);
                }
                start = end;
                is_dupe = this.points[start].compareTo(invoking_point)<0;
            }
            end++;
            if (this.points[end].compareTo(invoking_point)<0){
                is_dupe = true;
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
        }
        fast.getCollinear();
    }
}