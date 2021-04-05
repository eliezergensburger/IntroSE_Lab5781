package primitives;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    @Test
    void testDotProduct() {
    }

    @Test
    void testCrossProduct() {

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals( v1.length() * v3.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
//        try {
//            v1.crossProduct(v2);
//            fail("crossProduct() for parallel vectors does not throw an exception");
//        } catch (IllegalArgumentException e) {
//            System.out.println("GOOD: a Vector with Point(0,0,0) cannot be created");
//        }
        assertThrows(
                IllegalArgumentException.class,
                ()-> v1.crossProduct(v2),
                "crossProduct() for parallel vectors does not throw an exception"
                );
    }

    @Test
    void testLength() {
    }

    @Test
    void testLengthSquared() {
    }

    @Test
    void testNormalized() {
    }

    @Test
    void testNormalize() {
        Vector v = new Vector(3.5, -5, 10);
        v.normalize();
        assertEquals(1, v.length(), 1e-10);

        assertThrows(IllegalArgumentException.class,
                () ->   new Vector(0, 0, 0),
                "head camnot be (0,0,0)");
    }
    @Test
    void testAdd() {
    }

    @Test
    void testSubtract() {
    }

    @Test
    void testScale() {
    }
}