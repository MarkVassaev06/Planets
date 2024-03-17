package ru.mark.planets.models;

public class Body {
    private double mass;
    private Vector speed;
    private Vector accelerate;
    private double x;
    private double y;

    public Body(double mass,
                Vector speed,
                Vector accelerate,
                double x,
                double y) {
        this.mass = mass;
        this.speed = speed;
        this.accelerate = accelerate;
        this.x = x;
        this.y = y;
    }
}
