package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Tube implements Geometry {
    final Ray _axysRay;
    final double _radius;

    public Tube(Ray axysRay, double radius) {
        _axysRay = axysRay;
        _radius = radius;
    }

    @Override
    public Vector getNormal(Point3D p) {
       //TODO
        Point3D  P0 = _axysRay.getP0();
        Vector v = _axysRay.getDirection();

        Vector P0_P = p.subtract(P0);

        double t = alignZero(v.dotProduct(P0_P));

        if(isZero(t)){
            return P0_P.normalize();
        }

        Point3D o = P0.add(v.scale(t));

        if(p.equals(0)){
            throw new IllegalArgumentException("point cannot be on the tube axis");
        }

        Vector n = p.subtract(o);
        return n.normalized();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
