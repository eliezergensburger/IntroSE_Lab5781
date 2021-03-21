package primitives;

import static primitives.Point3D.ZERO;

/**
 * Vector in 3D for RayTracing
 *
 * @author Eliezer
 */
public class Vector {
    Point3D _head;

    /**
     * primary  constructor for Vector class
     *
     * @param head
     */
    public Vector(Point3D head) {
        if (head.equals(ZERO)) {
            throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
        }
        _head = head;
    }

    /**
     * constructor for Vector class
     *
     * @param x value for X Coordinate of the head Point
     * @param y value for Y Coordinate of the head Point
     * @param z value for Z Coordinate of the head Point
     */
    public Vector(double x, double y, double z) {
        this(new Point3D(x, y, z));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }

    @Override
    public String toString() {
        return "{" + _head + '}';
    }

    /**
     * get accessor for head Point
     *
     * @return a new Point3D corresponding to the head
     */
    public Point3D getHead() {
        //return _head;
        return new Point3D(_head._x._coord, _head._y._coord, _head._z._coord);
    }

    /**
     * dot product between two vectors (scalar product)
     *
     * @param v the right vector of U.V
     * @return scalre value of dot product
     */
    public double dotProduct(Vector v) {
        double u1 = _head._x._coord;
        double u2 = _head._y._coord;
        double u3 = _head._z._coord;

        double v1 = v._head._x._coord;
        double v2 = v._head._y._coord;
        double v3 = v._head._z._coord;

        return (u1 * v1 + u2 * v2 + u3 * v3);

    }

    /**
     * Cross product (vectorial product)
     *
     * @param v
     * @return new Vector resulting from cross product
     * @link https://en.wikipedia.org/wiki/Cross_product
     */
    public Vector crossProduct(Vector v) {
        double u1 = _head._x._coord;
        double u2 = _head._y._coord;
        double u3 = _head._z._coord;
        double v1 = v._head._x._coord;
        double v2 = v._head._y._coord;
        double v3 = v._head._z._coord;

        return new Vector(new Point3D(
                u2 * v3 - u3 * v2,
                u3 * v1 - u1 * v3,
                u1 * v2 - u2 * v1
        ));
    }

    /**
     * @return length using Pythagoras
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * @return
     */
    public double lengthSquared() {
        double u1 = _head._x._coord;
        double u2 = _head._y._coord;
        double u3 = _head._z._coord;

        return u1 * u1 + u2 * u2 + u3 * u3;
    }


    /**
     * creating a new Vector corresponding to the current values normalized
     *
     * @return new Vector normalized
     */
    public Vector normalized() {
        Vector result = new Vector(_head);
        result.normalize();
        return result;
    }

    /**
     * normalizing the current Vector
     *
     * @return this Vector normalized
     */
    public Vector normalize() {
        double length = this.length();

        //cannot divide by 0
        if (length == 0)
            throw new ArithmeticException("divide by Zero");

        double x = this._head._x._coord;
        double y = this._head._y._coord;
        double z = this._head._z._coord;

        this._head = new Point3D(x / length, y / length, z / length);

        return this;
    }

    /**
     *
     * @param v Vector
     * @return new Vector (u+v)
     */
    public Vector add(Vector v) {
        double x = _head._x._coord + v._head._x._coord;
        double y = _head._y._coord + v._head._y._coord;
        double z = _head._z._coord + v._head._z._coord;

        return new Vector(new Point3D(x, y, z));
    }

    /**
     *
     * @param v Vector
     * @return new Vector(u-v)
     */
    public Vector subtract(Vector v) {
        double x = _head._x._coord - v._head._x._coord;
        double y = _head._y._coord - v._head._y._coord;
        double z = _head._z._coord - v._head._z._coord;

        return new Vector(new Point3D(x, y, z));
    }

    /**
     * creating a new Vector corresponding to the actual one
     * scaled by scaling factor
     *
     * @param scalar scaling factot
     */
    public Vector scale(double scalar) {
        if(Double.compare(scalar,0d)== 0){
            throw new IllegalArgumentException("scaling factor == 0");
        }
        return new Vector(
                new Point3D(
                        scalar * _head._x._coord,
                        scalar * _head._y._coord,
                        scalar * _head._z._coord));
    }

}
