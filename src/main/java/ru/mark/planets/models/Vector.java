package ru.mark.planets.models;

public class Vector {
    public double x;
    public double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Vector speed) {
        x = speed.x;
        y = speed.y;
    }

}
