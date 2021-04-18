package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane implements FlatGeometry {

    final Point3D _q0;
    final Vector _normal;

    /**
     * getter Q0 field
     * @return reference to the q) point of the plane
     */
    public Point3D getQ0() {
        return _q0;
    }

    /**
     * getter of _normal field
     * @deprecated use {@link Plane#getNormal(Point3D)} with null values as parameter
     * @return refrence to normal vector of the plane
     */
    @Deprecated
    public Vector getNormal() {
        return _normal;
    }

    /**
     * TODO explanations here
     * @param p0
     * @param normal vector for the normal (will bwe normalized automatically)
     */
    public Plane(Point3D p0, Vector normal) {
        _q0 = p0;
        _normal = normal.normalized();
    }

    /**
     * Constructor of Plane from 3 points on its surface
     * the points are ordered from right to left
     * forming an arc in right direction
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _q0 = p1;
//        //TODO check direction of vectors
//        Vector U = p1.subtract(p2);
//        Vector V = p3.subtract(p2);

        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);

        Vector N = U.crossProduct(V);

        N.normalize();

        //right hand rule
        _normal = N;
    }

    /**
     * getNormal implementation of Geometry interface
     * @param point dummy point not use for flat geometries
     *              should be assigned null value
     * @return normal to the plane
     */
    @Override
    public Vector getNormal(Point3D point) {
        return _normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_q0=" + _q0 +
                ", _normal=" + _normal +
                '}';
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D P0 = ray.getP0();
        Vector v = ray.getDirection();

        Vector n = _normal;

        if(_q0.equals(P0)){
            return  null;
        }

        Vector P0_Q0 = _q0.subtract(P0);

       //numerator
        double nP0Q0  = alignZero(n.dotProduct(P0_Q0));

        //
        if (isZero(nP0Q0 )){
            return null;
        }

        //denominator
        double nv = alignZero(n.dotProduct(v));

        // ray is lying in the plane axis
        if(isZero(nv)){
            return null;
        }

        double  t = alignZero(nP0Q0  / nv);

        if (t <=0){
            return  null;
        }

        Point3D point = ray.getPoint(t);

        return List.of(point);
    }
}
