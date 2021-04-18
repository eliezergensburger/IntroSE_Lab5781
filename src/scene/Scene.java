package scene;

import geometries.Geometries;
import primitives.Color;

public class Scene {
    private final String _name;

    private Color _background;
    private Color _ambientlight;
    private Geometries _geometries = null;

    public Scene(String name) {
        _name = name;
    }

    //chaining methods
    public Scene setBackground(Color background) {
        _background = background;
        return  this;
    }

    public Scene setAmbientlight(Color ambientlight) {
        _ambientlight = ambientlight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        _geometries = geometries;
        return  this;
    }
}
