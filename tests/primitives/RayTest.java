package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testFindClosestPoint() {
        Ray ray = new Ray(new Point3D(0,0,10), new Vector(1, 10, -100));
        List<Point3D> list = new LinkedList<Point3D>();
        list.add(new Point3D(1, 1, -100));
        list.add(new Point3D(-1, 1, -99));
        list.add(new Point3D(0, 2, -10));
        list.add(new Point3D(0.5, 0, -100));
        assertEquals(list.get(2),ray.findClosestPoint(list));

    }
}