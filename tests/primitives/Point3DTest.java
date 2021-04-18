package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    @Test
    void testDistance() {

       Point3D point0 = new Point3D(1, 1, -100);
       Point3D point1 = new Point3D(-1, 1, -99);
       Point3D point2 = new Point3D(0, 0, -100);
       Point3D point3 = new Point3D(0.5, 0, -100);
        double resultsquared;
        double result;

        resultsquared = point3.distanceSquared(new Point3D(0,0,-100));
        System.out.println(resultsquared);
        result = point3.distance(new Point3D(0,0,-100));
        System.out.println(result);
    }
}