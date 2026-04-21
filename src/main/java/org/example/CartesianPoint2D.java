package org.example;

public final class CartesianPoint2D {
    private final double x;
    private final double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public CartesianPoint2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static CartesianPoint2D fromPolar(PolarPoint p) {
        double x =  p.getRadius() * Math.cos(p.getAngle());
        double y =  p.getRadius() * Math.sin(p.getAngle());
        return new CartesianPoint2D(x,y);
    }

    public static double distance(CartesianPoint2D p1, CartesianPoint2D p2) {
        return Math.sqrt(
                Math.pow(p2.getX() - p1.getX(), 2) +
                        Math.pow(p2.getY() - p1.getY(), 2)
        );
    }


}
