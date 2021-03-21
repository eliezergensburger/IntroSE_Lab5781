package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Cylinder extends Tube {   // implements Geometry
    final double _height;

    public Cylinder(Ray axisRay, double radius,double height) {
        super(axisRay,radius);
        _height = height;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        //todo rethink the all thing
        List<Point3D> result = super.findIntersections(ray);
        if(result != null){
            Point3D p = result.get(0);
            Vector v = p.subtract(_axysRay.getP0());
           //todo
        }
        //todo do the caps
        return result;
    }
}
