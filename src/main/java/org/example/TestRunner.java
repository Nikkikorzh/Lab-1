package org.example;

public class TestRunner {

    private static final double EPS = 1e-6;

    public static void runAllTests() {
        test2DConversion();
        test3DConversion();
        test2DDistance();
        test3DDistance();
    }

    private static void test2DConversion() {
        System.out.println("2D тест-конверсія");

        PolarPoint p1 = new PolarPoint(5, Math.PI / 3);
        System.out.printf("  Вихідна полярна:     r=%.4f, θ=%.4f%n", p1.getRadius(), p1.getAngle());

        CartesianPoint2D cart = CartesianPoint2D.fromPolar(p1);
        System.out.printf("  Декартова:         x=%.4f, y=%.4f%n", cart.getX(), cart.getY());

        PolarPoint p2 = PolarPoint.fromCartesian(cart);
        System.out.printf("  Назад до полярної: r=%.4f, θ=%.4f%n", p2.getRadius(), p2.getAngle());

        assertEquals(p1.getRadius(), p2.getRadius(), "Радіус");
        assertEquals(p1.getAngle(), p2.getAngle(), "Кут");
    }

    private static void test3DConversion() {
        System.out.println("3D тест-конверсія");

        SphericalPoint s1 = new SphericalPoint(10, Math.PI / 4, Math.PI / 3);
        System.out.printf("  Вихідна сферична:    r=%.4f, az=%.4f, θ=%.4f%n", s1.getRadius(), s1.getAzimuth(), s1.getPolarAngle());

        CartesianPoint3D cart = CartesianPoint3D.fromSpherical(s1);
        System.out.printf("  Декартова:         x=%.4f, y=%.4f, z=%.4f%n", cart.getX(), cart.getY(), cart.getZ());

        SphericalPoint s2 = SphericalPoint.fromCartesian(cart);
        System.out.printf("  Назад до сферичної: r=%.4f, az=%.4f, θ=%.4f%n", s2.getRadius(), s2.getAzimuth(), s2.getPolarAngle());

        assertEquals(s1.getRadius(), s2.getRadius(), "Радіус");
        assertEquals(s1.getAzimuth(), s2.getAzimuth(), "Азімут");
        assertEquals(s1.getPolarAngle(), s2.getPolarAngle(), "Полярний кут");
    }

    private static void test2DDistance() {
        System.out.println("2D тест-відстані");

        CartesianPoint2D p1 = new CartesianPoint2D(0, 0);
        CartesianPoint2D p2 = new CartesianPoint2D(3, 4);

        double dist = CartesianPoint2D.distance(p1, p2);
        assertEquals(5.0, dist, "Декартова відстань");

        PolarPoint pp1 = new PolarPoint(0, 0);
        PolarPoint pp2 = new PolarPoint(5, Math.atan2(4, 3));

        double polarDist = PolarPoint.distance(pp1, pp2);
        assertEquals(5.0, polarDist, "Полярна відстань");
    }

    private static void test3DDistance() {
        System.out.println("3D тест-відстані");

        SphericalPoint s1 = new SphericalPoint(10, 0, Math.PI / 2);
        SphericalPoint s2 = new SphericalPoint(10, Math.PI / 2, Math.PI / 2);

        double chord = SphericalPoint.distanceChord(s1, s2);
        double arc = SphericalPoint.distanceArc(s1, s2);

        System.out.println("Хорда = " + chord);
        System.out.println("Дуга = " + arc);
    }

    private static void assertEquals(double expected, double actual, String name) {
        if (Math.abs(expected - actual) < EPS) {
            System.out.println(name + " Добре");
        } else {
            System.out.println(name + " Помилка: очікувається=" + expected + " актуальна=" + actual);
        }
    }
}
