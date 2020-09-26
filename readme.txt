/**********************************************************************
 *  Pattern Recognition readme.txt template
 **********************************************************************/

Name: Ægir Máni Hauksson
Email: aegir19@ru.is     
Hópur: 30

Partner name: Hákon Hákonarson
Partner login: hakon19@ru.is
Partner hópur: 30

Username of the student submitting to Mooshak:  hakon19, aegir19

Hours to complete assignment (optional): 24



/**********************************************************************
 *  Step 1.  Explain *briefly* how you implemented brute force.
 *           Describe how you implemented compareTo() and the
 *           slopeTo() methods in the Point data type.
 **********************************************************************/
    1. Brute force implementation:
    Made 4 for loops. Within each of the for loops a point is available.
    Once we have two points we start making slopes between the points.
    If all 3 slopes are the same we have a line between the 4 points.

    2. compareTo()
    The behaviour of the compareTo method can be read directly from the project description.
    If the "this" has smaller y than "that"                   -> this is smaller   -> return -1
    If the "this" has equal y to "that" but a smaller x       -> this is smaller   -> return -1
    If the "this" has equal y to "that" and the x's are equal -> this is larger    -> return  1
    If the "this" has equal y to "that" and the x is larger   -> this is larger    -> return  1

    3. slopeTo()
    slope = delta y / delta x
    If we have 0 / 0   ->   negative infinity
    If we have x / 0   ->   positive infinity
    If we have 0 / x   ->   0
    If we have x / y   ->   x/y




/**********************************************************************
 *  Step 2.  Explain *briefly* how you implemented the sorting solution.
 *           What steps did you do to avoid printing permutations
 *           and subsegments?
 **********************************************************************/
    We start by sorting one list in order of Point size.
    We then iterate through that list, assigning each point in the list as the
    invoking point for a second list, that is sorted by the slope they make
    with the invoking point.

    With each iteration of the second list we go through it and check if any
    of the points next to each other have the same slope. If at least 3 points in
    a row have the same slope to the invoking point, we print the line.

    In Fast2, we avoided all such cases by not printing any collinear sets
    that have a point in them that is smaller than the invoking point. We
    did so by marking the collinear set as a duplicate if it ever was the case
    that the current point was the smaller than the invoking point.

    In Fast, we removed unwanted cases by not printing if any of the two current
    points being iterated over were smaller than the invoking point. This is
    different from Fast2 in the sense that we do not cancel printing it entirely
    but instead print whatever came before the if statement was true.



/**********************************************************************
 *  Empirical    Fill in the table below with actual running times in
 *  Analysis     seconds when reasonable (say 180 seconds or less).
 *               You can round to the nearest tenth of a second.
 *
 *  Estimate (using tilde notation) the running time (in seconds) of
 *  your two main functions as a function of the number of points N.
 *
 *  Explain how you derive any exponents.
 **********************************************************************/

    
      N       brute       sorting
 ----------------------------------
    150         0.1          0.0
    200         0.4          0.0
    300         0.6          0.1
    400         1.4          0.1
    800         27.4         0.2
   1600         942.3        0.5
   3200         32226        1.6
   6400         1.1*10^6     6.2
  12800         3.8*10^7     25.2


Brute:    ~ N^4/8

Sorting:  ~ N^2*Log(N)




/**********************************************************************
 *  Theoretical   Give the order of growth of the worst-case running
 *                time of your programs as a function of N. Justify
 *                your answer briefly.
 **********************************************************************/

    Brute:
        Order of growth of t: log(942.3/27.4)/log(1600/800) = 5.10
        Constant: 27.4/(800^5.10) = 4.285 * 10^(-14)
        T(n) = 4.285 * 10^(-14) * n^(5.1)

    Sorting:
        Order of growth of t: log(25.2/6.2)/log(12800/6400) = 2.02
        Constant: 6.2/(6400^2.02) = 1.27 * 10^(-7)
        T(n) = 1.27 * 10^(-7) * n^(2.02)


/**********************************************************************
 *  Known bugs / limitations. For example, if your program prints
 *  out different representations of the same line segment when there
 *  are 5 or more points on a line segment, indicate that here.
 **********************************************************************/
    The "this.points" list/array gets ruined after one run in Fast. You would
    have to save the points in a secondary array or re-read the points in, if
    you want to use them again.


/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/
    We got some help from a class mate. His name is Bjarmi Anes Eiðsson.
    He helped us when we were stuck with 1 wrong test in Fast, we needed
    to get around the point (20000, 0) when we were checking from the point
    (15000, 5000) in one of the test cases. He suggested removing the point
    when we didn't need it any more. But we settled on making the values (0,0).


/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/
    We only encountered one problem. We sorted the list of points by slope,
    and that usually worked. But when we started encountering bigger sets of
    points the there were points that were lexicographically smaller but
    still ended up in the sorted list. We didn't know how to sort the list
    better so we decided after comparing a point with all lexicographically
    larger points we "deleted" it by setting the point to (0,0). Because we
    figured because it starts comparing with the smallest point and then goes
    bigger. And since 0,0 is the smallest point possible we set the point to
    (0,0).


/**********************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **********************************************************************/
    We of course contributed differently but we can both agree that we
    probably could have done this assignment with out each other. Or at
    least within the same time frame. Because a lot of the time spent went
    into the implementation and figuring out all kinds of edge-cases and
    obstacles we did most of the work through discord and screen sharing by
    tweaking small pieces of code on one computer instead of implementing
    many functions that are doing simpler things on different systems.


/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
