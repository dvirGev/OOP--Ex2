import api.GeoLocation;

/**
 * represents a geo location <x,y,z>, (aka Point3D data).
 */
public class MyGeoLocation implements GeoLocation {
    private double x, y, z;

    //constructor
    public MyGeoLocation(String geo) {
        String[] arrOfStr = geo.split(",");
        this.x = Double.parseDouble(arrOfStr[0]);
        this.y = Double.parseDouble(arrOfStr[1]);
        this.z = Double.parseDouble(arrOfStr[2]);
    }

    //copy constructor
    public MyGeoLocation(GeoLocation other) {
        this.x = other.x();
        this.y = other.y();
        this.z = other.z();
    }

    //getter
    @Override
    public double x() {
        return x;
    }

    //getter
    @Override
    public double y() {
        return y;
    }

    //getter
    @Override
    public double z() {
        return z;
    }

    //simple function to calculate distance from other GeoLocation
    @Override
    public double distance(GeoLocation g) {
        double x = Math.pow(g.x() - this.x, 2);
        double y = Math.pow(g.y() - this.y, 2);
        double z = Math.pow(g.z() - this.z, 2);
        return Math.sqrt(x + y + z);
    }

    //to string
    @Override
    public String toString() {
        return "X = " + x + ", Y = " + y + ", Z = " + z;
    }
}
