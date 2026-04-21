package org.example;

public final class CartesianPoint3D {
    private final double x;
    private final double y;
    private final double z;

    public CartesianPoint3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public static CartesianPoint3D fromSpherical(SphericalPoint p) {
        double x = p.getRadius() * Math.sin(p.getPolarAngle()) * Math.cos(p.getAzimuth());
        double y = p.getRadius() * Math.sin(p.getPolarAngle()) * Math.sin(p.getAzimuth());
        double z = p.getRadius() * Math.cos(p.getPolarAngle());
        return new CartesianPoint3D(x, y, z);
    }

    public static double distance(CartesianPoint3D p1, CartesianPoint3D p2) {
        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;
        double dz = p2.z - p1.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

}
