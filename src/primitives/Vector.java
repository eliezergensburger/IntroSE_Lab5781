package primitives;

import static primitives.Point3D.ZERO;
import static primitives.Util.isZero;

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
     * @param head head of vector starting from origin Point(0.0.0)
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
     * @param v second vector
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
     * @return euclidean length squared of the vector
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

//      the following check is not necessary because there
//      cannot be a ZERO vector
//        //cannot divide by 0
//        if (length == 0)
//            throw new ArithmeticException("divide by Zero");

        double x = this._head._x._coord;
        double y = this._head._y._coord;
        double z = this._head._z._coord;

        this._head = new Point3D(x / length, y / length, z / length);

        return this;
    }

    /**
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
        if (isZero(scalar)) {
            throw new IllegalArgumentException("scaling factor == 0");
        }
        return new Vector(
                new Point3D(
                        scalar * _head._x._coord,
                        scalar * _head._y._coord,
                        scalar * _head._z._coord));
    }

    /**
     * @param axis
     * @param theta
     * @return
     * @author Yona Szmerla
     */
    public void rotateVector(Vector axis, double theta) {
        double x = this._head.getX();
        double y = this._head.getY();
        double z = this._head.getZ();

        double u = axis._head.getX();
        double v = axis._head.getY();
        double w = axis._head.getZ();

        double v1 = u * x + v * y + w * z;

        double thetaRad = Math.toRadians(theta);

        double xPrime = u * v1 * (1d - Math.cos(thetaRad))
                + x * Math.cos(thetaRad)
                + (-w * y + v * z) * Math.sin(thetaRad);

        double yPrime = v * v1 * (1d - Math.cos(thetaRad))
                + y * Math.cos(thetaRad)
                + (w * x - u * z) * Math.sin(thetaRad);

        double zPrime = w * v1 * (1d - Math.cos(thetaRad))
                + z * Math.cos(thetaRad)
                + (-v * x + u * y) * Math.sin(thetaRad);

        this._head = new Point3D(xPrime, yPrime, zPrime);
    }

}
