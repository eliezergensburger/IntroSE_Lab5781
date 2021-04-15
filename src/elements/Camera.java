package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {
    final private Point3D _p0;
    final private Vector _vTo;
    final private Vector _vUp;
    final private Vector _vRight;

    private double _distance;
    private double _width ;
    private double _height;

    private Camera (BuilderCamera builder){
        _p0 = builder._p0;
        _vTo =builder._vTo;
        _vUp=builder._vUp;
        _vRight= builder._vRight;
        _height= builder._height;
        _width=builder._width;
        _distance =builder._distance;
    }

    //Camera setter chaining methods
    public Camera setDistance(double distance) {
        _distance = distance;
        return this;
     }

    public Camera setViewPlaneSize(double width,double height) {
        _width = width;
        _height = height;
        return this;
    }

    //Camera getters methods
    public double getWidth() {
        return _width;
    }

    public double getHeight() {
        return _height;
    }

    // constructing a ray passing through pixel(i,j) of the view plane
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point3D Pc = _p0.add(_vTo.scale(_distance));

        double Rx = _width / nX;
        double Ry = _height / nY;

        Point3D Pij = Pc;

        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;

        if (isZero(Xj) && isZero(Yi)) {
            return new Ray(_p0, Pij.subtract(_p0));
        }
        if (isZero(Xj)) {
            Pij = Pij.add(_vUp.scale(Yi));
            return new Ray(_p0, Pij.subtract(_p0));
        }
        if (isZero(Yi)) {
            Pij = Pij.add(_vRight.scale(Xj));
            return new Ray(_p0, Pij.subtract(_p0));
        }

        Pij = Pij.add(_vRight.scale(Xj).add(_vUp.scale(Yi)));
        return new Ray(_p0, Pij.subtract(_p0));

    }

    /**
     * Builder Class for Camera
     */
    public static class BuilderCamera {
        final private Point3D _p0;
        final private Vector _vTo;
        final private Vector _vUp;
        final private Vector _vRight;

        private double _distance =10;
        private double _width =1;
        private double _height=1;

        public BuilderCamera setDistance(double distance) {
            _distance = distance;
            return this;
        }


        public BuilderCamera setViewPlaneWidth(double width) {
            _width = width;
             return  this;
        }

        public BuilderCamera setViewPlaneHeight(double height) {
            _height = height;
            return  this;
        }

        public Camera build(){
            Camera camera = new Camera(this);
            return  camera;
        }

        public BuilderCamera(Point3D p0, Vector vTo, Vector vUp) {
            _p0 = p0;

            if (!isZero(vTo.dotProduct(vUp))) {
                throw new IllegalArgumentException("vto and vup are not orthogonal");
            }

            _vTo = vTo.normalized();
            _vUp = vUp.normalized();

            _vRight = _vTo.crossProduct(_vUp);

        }
    }

}


