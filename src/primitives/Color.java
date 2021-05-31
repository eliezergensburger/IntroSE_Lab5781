package primitives;

/**
 * Wrapper class for java.jwt.Color The constructors operate with any
 * non-negative RGB values. The colors are maintained without upper limit of
 * 255. Some additional operations are added that are useful for manipulating
 * light's colors
 *
 * @author Dan Zilberstein
 */
public class Color {
    /**
     * The internal fields tx`o maintain RGB components as double numbers from 0 to
     * whatever...
     */
    private final double _r;
    private final double _g;
    private final double _b;

    public static final Color BLACK = new Color(0d,0d,0d);
    public static final Color BLUE = new Color(0, 0, 255);
    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color YELLOW = new Color(255, 255, 0);
    public static final Color CYAN = new Color(0, 255, 255);
    public static final Color MAGENTA = new Color(255, 0, 255);
    public static final Color ORANGE = new Color(255, 200, 0);
    public static final Color PINK = new Color(255, 175, 175);
    public static final Color DARKGREY = new Color(64, 64, 64);
    public static final Color GREY = new Color(128, 128, 128);
    public static final Color LIGHTGREY = new Color(192, 192, 192);
    public static final Color WHITE = new Color(255, 255, 255);



    /**
     * Constructor to generate a color according to RGB components Each component in
     * range 0..255 (for printed white color) or more [for lights]
     *
     * @param r Red component
     * @param g Green component
     * @param b Blue component
     */
    public Color(double r, double g, double b) {
        if (r < 0 || g < 0 || b < 0)
            throw new IllegalArgumentException("Negative color component is illegal");
       _r = r;
       _g = g;
       _b = b;
    }

    /**
     * Copy constructor for Color
     *
     * @param other the source color
     */
    public Color(Color other) {
        _r = other._r;
        _g = other._g;
        _b = other._b;
    }

    /**
     * Constructor on base of java.awt.Color object
     *
     * @param other java.awt.Color's source object
     */
    public Color(java.awt.Color other) {
        _r = other.getRed();
        _g = other.getGreen();
        _b = other.getBlue();
    }


    /**
     * Color getter - returns the color after converting it into java.awt.Color
     * object During the conversion any component bigger than 255 is set to 255
     *
     * @return java.awt.Color object based on this Color RGB components
     */
    public java.awt.Color getColor() {
        int ir = (int) _r;
        int ig = (int) _g;
        int ib = (int) _b;
        return new java.awt.Color(Math.min(ir, 255), Math.min(ig, 255), Math.min(ib, 255));
    }

    /**
     * Operation of adding this and one or more other colors (by component)
     *
     * @param colors one or more other colors to add
     * @return new Color object which is a result of the operation
     */
    public Color add(Color... colors) {
        double rr = _r;
        double rg = _g;
        double rb = _b;
        for (Color c : colors) {
            rr += c._r;
            rg += c._g;
            rb += c._b;
        }
        return new Color(rr, rg, rb);
    }

    /**
     * Scale the color by a scalar
     *
     * @param k scale factor
     * @return new Color object which is the result of the operation
     */
    public Color scale(double k) {
        if (k < 0)
            throw new IllegalArgumentException("Can't scale a color by a negative number");
        return new Color(_r * k, _g * k, _b * k);
    }

    /**
     * Scale the color by (1 / reduction factor)
     *
     * @param k reduction factor
     * @return new Color object which is the result of the operation
     */
    public Color reduce(double k) {
        if (k < 1)
            throw new IllegalArgumentException("Can't scale a color by a by a number lower than 1");
        return new Color(_r / k, _g / k, _b / k);
    }

}
