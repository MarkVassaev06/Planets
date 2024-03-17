package ru.mark.planets.models;

public class Body {
    public double mass;
    public Vector speed;
    public Vector accelerate;
    public double x;
    public double y;

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
