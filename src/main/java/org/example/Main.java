package org.example;

public class Main {
    public static void main(String[] args) {
        TestRunner.runAllTests();

        System.out.println("\n-----------------\n");

        BenchmarkRunner.runAllBenchmarks();
    }
}