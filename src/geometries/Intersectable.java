package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

public interface Intersectable {
    public List<Point3D> findIntersections(Ray ray);
}
