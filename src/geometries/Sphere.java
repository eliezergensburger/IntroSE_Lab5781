package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Sphere implements Geometry {
    final Point3D _center;
    final double _radius;

    public Sphere(Point3D center, double radius) {
        _center = center;
        _radius = radius;
    }

    public Point3D getCenter() {
        return _center;
    }

    public double getRadius() {
        return _radius;
    }

    @Override
    public Vector getNormal(Point3D p) {
        Vector p0_p = p.subtract(_center);
        return p0_p.normalize();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D P0 = ray.getP0();
        Vector v = ray.getDirection();

        if (P0.equals(_center)) {
            return List.of(_center.add(v.scale(_radius)));
        }

        Vector U = _center.subtract(P0);

        double tm = alignZero(v.dotProduct(U));

        double d = alignZero(Math.sqrt(U.lengthSquared() -tm*tm));

        // no intersections : the ray direction is above the sphere
        if(d>=_radius){
            return null;
        }

        double th = alignZero(Math.sqrt(_radius*_radius - d*d));

        double t1 = alignZero(tm -th);
        double t2 = alignZero( tm + th);

        if(t1 > 0 && t2 > 0){
            Point3D P1 = P0.add(v.scale(t1));
            Point3D P2 = P0.add(v.scale(t2));

            return List.of(P1,P2);
        }
        if(t1 > 0){
            Point3D P1 = P0.add(v.scale(t1));
            return List.of(P1);
        }
        if(t2 > 0){
            Point3D P2 = P0.add(v.scale(t2));
            return List.of(P2);
        }
        return null;
    }
}
