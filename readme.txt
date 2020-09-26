/**********************************************************************
 *  Pattern Recognition readme.txt template
 **********************************************************************/

Name: Ægir Máni Hauksson             
Email: aegir19@ru.is     
Hópur:Solo

Username of the student submitting to Mooshak: aegir19@ru.is

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
 ---------------------------------
    150
    200
    300
    400
    800
   1600
   3200
   6400
  12800


Brute:    ~ N^4/8

Sorting:  ~N^2*Log(N)




/**********************************************************************
 *  Theoretical   Give the order of growth of the worst-case running
 *                time of your programs as a function of N. Justify
 *                your answer briefly.
 **********************************************************************/

Brute:

Sorting:



/**********************************************************************
 *  Known bugs / limitations. For example, if your program prints
 *  out different representations of the same line segment when there
 *  are 5 or more points on a line segment, indicate that here.
 **********************************************************************/



/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/



/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/




/**********************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **********************************************************************/







/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
