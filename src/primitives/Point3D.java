package primitives;

/**
 * basic Point for RayTracing project in 3D
 *
 * @author Eliezer
 */
public class Point3D {
    final Coordinate _x;
    final Coordinate _y;
    final Coordinate _z;

    //static Point3D for origin point (0,0,0)
    public final static Point3D ZERO = new Point3D(0d, 0d, 0d);

    /**
     * primary constructor for Point3D
     * @param x coordinate value for X axis
     * @param y coordinate value for Y axis
     * @param z coordinate value for Z axis
     */
    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    /**
     *
     * @param o Object (basicaly another Point3d) to compare
     * @return true or false accordingly
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) && _y.equals(point3D._y) && _z.equals(point3D._z);
    }

    @Override
    public String toString() {
        return "(" + _x + "," + _y + "," + _z + ')';
    }

    //getters
    public double getX() {
        return _x._coord;
    }
    public double getY() {
        return _y._coord;
    }
    public double getZ() {
        return _z._coord;
    }

    /**
     * @param other
     * @return (x2 - x1)^2 + (y2-y1)^2 + (z2-z1)^2
     */
    public double distanceSquared(Point3D other) {
        final double x1 = _x._coord;
        final double y1 = _y._coord;
        final double z1 = _z._coord;

        final double x2 = other._x._coord;
        final double y2 = other._y._coord;
        final double z2 = other._z._coord;

        return ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)) + ((z2 - z1) * (z2 - z1));
    }

    /**
     * @param point3D
     * @return euclidean distance between 2  3D points
     */
    public double distance(Point3D point3D) {
        return Math.sqrt(distanceSquared(point3D));
    }

    /**
     *
     * @param pt2 Point3d from whom we create the Vector
     *            pointing toward the actual Point3d instance
     * @return
     */
    public Vector subtract(Point3D pt2) {
        if (pt2.equals(this)) {
            throw new IllegalArgumentException("cannot create Vector to Point(0,0,0)");
        }
        return  new Vector(new Point3D(
                _x._coord - pt2._x._coord,
                _y._coord - pt2._y._coord,
                _z._coord - pt2._z._coord
        ));
    }

    public Point3D add(Vector v) {
        return new Point3D(
                this._x._coord + v._head._x._coord,
                this._y._coord + v._head._y._coord,
                this._z._coord + v._head._z._coord);
    }

}


