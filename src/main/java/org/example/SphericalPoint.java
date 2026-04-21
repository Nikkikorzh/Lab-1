package org.example;

public final class SphericalPoint {
    private final double radius;
    private final double azimuth;
    private final double polarAngle;

    public SphericalPoint(double radius, double azimuth, double polarAngle) {
        this.radius = radius;
        this.azimuth = azimuth;
        this.polarAngle = polarAngle;
    }

    public double getRadius() {
        return radius;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public double getPolarAngle() {
        return polarAngle;
    }

    public static SphericalPoint fromCartesian (CartesianPoint3D p) {
        double radius = Math.sqrt(Math.pow(p.getX(),2) + Math.pow(p.getY(),2) + Math.pow(p.getZ(),2));
        double azimuth = Math.atan2(p.getY(),p.getX());
        if (radius < 1e-12) {
            throw new IllegalArgumentException("Точка на початку");
        }
        double polarAngle = Math.acos(p.getZ()/radius);
        return new SphericalPoint(radius,azimuth,polarAngle);
    }

    public static double distanceArc(SphericalPoint p1, SphericalPoint p2) {
        if (Math.abs(p1.getRadius() - p2.getRadius()) > 1e-9) {
            throw new IllegalArgumentException("Радіуси повинні бути оданкові");
        }

        double cosGamma =
                Math.sin(p1.getPolarAngle()) * Math.sin(p2.getPolarAngle()) *
                        Math.cos(p1.getAzimuth() - p2.getAzimuth()) +
                        Math.cos(p1.getPolarAngle()) * Math.cos(p2.getPolarAngle());

        double gamma = Math.acos(cosGamma);

        return p1.getRadius() * gamma;
    }

    public static double distanceChord(SphericalPoint p1, SphericalPoint p2) {
        double cosGamma =
                Math.sin(p1.getPolarAngle()) * Math.sin(p2.getPolarAngle()) *
                        Math.cos(p1.getAzimuth() - p2.getAzimuth()) +
                        Math.cos(p1.getPolarAngle()) * Math.cos(p2.getPolarAngle());

        return Math.sqrt(
                Math.pow(p1.getRadius(), 2) +
                        Math.pow(p2.getRadius(), 2) -
                        2 * p1.getRadius() * p2.getRadius() * cosGamma
        );
    }
}
