package primitives;

public class Ray {
    final  Point3D _pOrigin;
    final Vector _direction;

    public Ray(Point3D pOrigin, Vector direction) {
        _pOrigin = pOrigin;
        _direction = direction.normalized();
    }

    public Point3D getP0() {
        return _pOrigin;
    }

    public Vector getDirection() {
        return new Vector(_direction._head);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _pOrigin.equals(ray._pOrigin) && _direction.equals(ray._direction);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "_pOrigin=" + _pOrigin +
                ", _direction=" + _direction +
                '}';
    }
}
