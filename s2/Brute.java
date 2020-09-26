package s2;

import edu.princeton.cs.algs4.*;
import javax.management.InvalidAttributeValueException;

public class Brute {
    private final Point [] points;

    public Brute(int n) throws InvalidAttributeValueException {
        if(n<4) throw new InvalidAttributeValueException("n must be at least 4");
        this.points = new Point[n];
    }

    public void sortPoints(){
        QuickX.sort(this.points);
    }

    public void printPoints(Point [] pts){
        Out out = new Out();
        out.println(pts[0].toString()+" -> "+pts[1].toString()+" -> "+pts[2].toString()+" -> "+pts[3].toString());
    }

    public void bruteForce(){
        int i, j, k, l;
        int len = this.points.length;
        sortPoints();
        double s1,s2,s3;
        for(i=0;i<len; i++){ // Point 1: this.points[i]
            for(j=i+1;j<len; j++){ // Point 2: this.points[j]
                s1=this.points[i].slopeTo(this.points[j]); // Slope 1: point 1 -> point 2
                for(k=j+1;k<len; k++){ // Point 3: this.points[k]
                    s2=this.points[j].slopeTo(this.points[k]); // Slope 2: point 2 -> point 3
                    for(l=k+1;l<len; l++){ // Point 4: this.points[l]
                        s3=this.points[k].slopeTo(this.points[l]); // Slope 3: point 3 -> point 4
                        if((s1==s2 && s1==s3)){
                            printPoints(new Point[]{this.points[i], this.points[j], this.points[k], this.points[l]});
                        }
    }}}}}

    public static void main(String [] args) throws InvalidAttributeValueException {
        In in = new In();
        int n = in.readInt();
        Brute brute = new Brute(n);
        //var stopwatch = new Stopwatch();
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            brute.points[i] = new Point(x, y);
        }
        brute.bruteForce();
        /*var timespent = stopwatch.elapsedTime();
        StdOut.println("Time: " + timespent);*/
    }
}
