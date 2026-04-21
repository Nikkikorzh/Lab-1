package org.example;

public final class PolarPoint {
    private final double radius;
    private final double angle;

    public PolarPoint(double radius, double angle) {
        this.radius = radius;
        this.angle = angle;
    }
    public double getRadius() {
        return radius;
    }

    public double getAngle() {
        return angle;
    }

    public static  PolarPoint fromCartesian(CartesianPoint2D p) {
        double radius = Math.sqrt(Math.pow(p.getX(),2) + Math.pow(p.getY(),2));
        double angle = Math.atan2(p.getY(),p.getX());
        return new PolarPoint(radius,angle);
    }

    public static double distance(PolarPoint p1, PolarPoint p2) {
        return Math.sqrt(
                Math.pow(p1.getRadius(), 2) +
                        Math.pow(p2.getRadius(), 2) -
                        2 * p1.getRadius() * p2.getRadius() *
                                Math.cos(p1.getAngle() - p2.getAngle())
        );
    }
}
