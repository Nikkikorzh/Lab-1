package org.example;

import java.util.Random;

public class BenchmarkRunner {

    private static final int N = 100_000;
    private static final int WARMUP = 3;
    private static final Random random = new Random();

    public static void runAllBenchmarks() {
        benchmark2D();
        benchmark3D();
    }

    private static void benchmark2D() {
        System.out.println("2D тест");

        PolarPoint[] p1 = new PolarPoint[N];
        PolarPoint[] p2 = new PolarPoint[N];
        CartesianPoint2D[] c1 = new CartesianPoint2D[N];
        CartesianPoint2D[] c2 = new CartesianPoint2D[N];

        for (int i = 0; i < N; i++) {
            p1[i] = new PolarPoint(random.nextDouble() * 100, random.nextDouble() * 2 * Math.PI);
            p2[i] = new PolarPoint(random.nextDouble() * 100, random.nextDouble() * 2 * Math.PI);
            c1[i] = CartesianPoint2D.fromPolar(p1[i]);
            c2[i] = CartesianPoint2D.fromPolar(p2[i]);
        }

        for (int w = 0; w < WARMUP; w++) {
            double sink = 0;
            for (int i = 0; i < N; i++) sink += PolarPoint.distance(p1[i], p2[i]);
            for (int i = 0; i < N; i++) sink += CartesianPoint2D.distance(c1[i], c2[i]);
            if (sink == Double.MIN_VALUE) System.out.println(sink);
        }


        double sink = 0;
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) sink += PolarPoint.distance(p1[i], p2[i]);
        long polarTime = System.nanoTime() - start;

        sink = 0;
        start = System.nanoTime();
        for (int i = 0; i < N; i++) sink += CartesianPoint2D.distance(c1[i], c2[i]);
        long cartTime = System.nanoTime() - start;

        if (sink == Double.MIN_VALUE) System.out.println(sink);
        System.out.println("Полярні :     " + polarTime + " нс");
        System.out.println("Декартові : " + cartTime + " нс");
    }

    private static void benchmark3D() {
        System.out.println("3D тест");

        SphericalPoint[] s1 = new SphericalPoint[N];
        SphericalPoint[] s2 = new SphericalPoint[N];
        CartesianPoint3D[] c1 = new CartesianPoint3D[N];
        CartesianPoint3D[] c2 = new CartesianPoint3D[N];

        for (int i = 0; i < N; i++) {
            double r = random.nextDouble() * 100;
            s1[i] = new SphericalPoint(r, random.nextDouble() * 2 * Math.PI, random.nextDouble() * Math.PI);
            s2[i] = new SphericalPoint(r, random.nextDouble() * 2 * Math.PI, random.nextDouble() * Math.PI);
            c1[i] = CartesianPoint3D.fromSpherical(s1[i]);
            c2[i] = CartesianPoint3D.fromSpherical(s2[i]);
        }

        for (int w = 0; w < WARMUP; w++) {
            double sink = 0;
            for (int i = 0; i < N; i++) sink += SphericalPoint.distanceChord(s1[i], s2[i]);
            for (int i = 0; i < N; i++) sink += SphericalPoint.distanceArc(s1[i], s2[i]);
            for (int i = 0; i < N; i++) sink += CartesianPoint3D.distance(c1[i], c2[i]);
            if (sink == Double.MIN_VALUE) System.out.println(sink);
        }

        double sink = 0;
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) sink += SphericalPoint.distanceChord(s1[i], s2[i]);
        long chordTime = System.nanoTime() - start;

        sink = 0;
        start = System.nanoTime();
        for (int i = 0; i < N; i++) sink += SphericalPoint.distanceArc(s1[i], s2[i]);
        long arcTime = System.nanoTime() - start;

        sink = 0;
        start = System.nanoTime();
        for (int i = 0; i < N; i++) sink += CartesianPoint3D.distance(c1[i], c2[i]);
        long cartTime = System.nanoTime() - start;

        if (sink == Double.MIN_VALUE) System.out.println(sink);
        System.out.println("Хорда:     " + chordTime + " нс");
        System.out.println("Дуга:      " + arcTime + " нс");
        System.out.println("Декартові: " + cartTime + " нс");
    }
}
