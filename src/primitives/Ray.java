package primitives;

import java.util.List;

import static primitives.Util.isZero;

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

   public Point3D getPoint(double delta ){
        if (isZero(delta)){
            return  _pOrigin;
        }
        return _pOrigin.add(_direction.scale(delta));
    }

    /**
     * find the closest Point to Ray origin
     * @param pointsList intersections point List
     * @return closest point
     */
    public Point3D findClosestPoint(List<Point3D> pointsList){
        Point3D result =null;
        double closestDistance = Double.MAX_VALUE;

        if(pointsList== null){
            return null;
        }

        for (Point3D p: pointsList) {
            double temp = p.distance(_pOrigin);
            if(temp < closestDistance){
                closestDistance =temp;
                result =p;
            }
        }

        return  result;
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
