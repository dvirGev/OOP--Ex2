import api.GeoLocation;

public class MyGeoLocaion implements GeoLocation {
    double x, y, z;

    public MyGeoLocaion(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

}
