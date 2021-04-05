package geometries;

public abstract class RadialGeometry implements Geometry{
    final double _radius;

    public RadialGeometry(double radius) {
        _radius = radius;
    }

    public double getRadius() {
        return _radius;
    }
}
