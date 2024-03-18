package ru.mark.planets.models;

import ru.mark.planets.Application;

import java.awt.*;
import java.util.Objects;

/**
 * Космическое тело.
 */
public class Body {
    /**
     * Порядковый номер тела.
     */
    private int id;
    /**
     * Масса.
     */
    public final double mass;
    /**
     * Вектор скорости.
     */
    public Vector speed;
    /**
     * Вектор ускорения.
     */
    public Vector accelerate;
    /**
     * Координата по x.
     */
    public double x;
    /**
     * Координата по y.
     */
    public double y;

    public Body(int id,
                double mass,
                Vector speed,
                Vector accelerate,
                double x,
                double y) {
        this.id = id;
        this.mass = mass;
        this.speed = speed;
        this.accelerate = accelerate;
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics graphics, Color color) {
        graphics.setColor(color);
        int tempX = (int) (Math.round(Application.COEFFICIENT * x + Application.SIZE / 2));
        int tempY = (int) (Math.round(Application.COEFFICIENT * y + Application.SIZE / 2));
        graphics.fillOval(tempX, tempY, 5, 5);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Body body = (Body) o;
        return id == body.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
