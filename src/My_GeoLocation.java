import api.GeoLocation;

public class My_GeoLocation implements GeoLocation {
    private double x, y, z;

    public My_GeoLocation(String geo) {
        String[] arrOfStr = geo.split(",");
        this.x = Double.parseDouble(arrOfStr[0]);
        this.y = Double.parseDouble(arrOfStr[1]);
        this.z = Double.parseDouble(arrOfStr[2]);
    }

    public My_GeoLocation(GeoLocation other) {
        this.x = other.x();
        this.y = other.y();
        this.z = other.z();
    }

    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    @Override
    public double z() {
        return z;
    }

    @Override
    public double distance(GeoLocation g) {
        double x = Math.pow(g.x() - this.x, 2);
        double y = Math.pow(g.y() - this.y, 2);
        double z = Math.pow(g.z() - this.z, 2);
        return Math.sqrt(x + y + z);
    }
    @Override
    public String toString() {
        return "X = " + x + ", Y = " + y + ", Z = " + z;
    }
}
