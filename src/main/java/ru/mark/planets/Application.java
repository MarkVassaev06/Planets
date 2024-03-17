package ru.mark.planets;

import ru.mark.planets.models.Body;
import ru.mark.planets.models.Vector;

import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application {
    private static final Random RND = new SecureRandom();
    public static final double MIN_MASS = Math.pow(10, 9);
    public static final double MAX_MASS = Math.pow(10, 12);

    public static final double MIN_SPEED = Math.pow(10, 2);
    public static final double MAX_SPEED = Math.pow(10, 4);

    public static final double MIN_RADIUS = Math.pow(10, 2);
    public static final double MAX_RADIUS = Math.pow(10, 13);

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Не указано количество планет");
            System.exit(0);
        }
        int count = Integer.parseInt(args[0]);
        if (count < 2) {
            System.out.println("количество планет должно быть больше 1");
            System.exit(0);
        }

        Window window = new Window(count);
        window.setLocationRelativeTo(null);
        window.setBounds(10, 10, WIDTH, HEIGHT);
        window.setLayout(null);
        window.setVisible(true);
    }

    private static final class Window extends JFrame {
        private final int count;
        private List<Body> bodies;

        public Window(int count) {
            super("Планеты");
            this.count = count;
            init();
            JButton button = new JButton("press me");
            button.setBounds(0, 0, 120, 25);
            add(button);
            button.addActionListener(e -> {
                Graphics graphics = getGraphics();
                graphics.setColor(Color.BLACK);
                for (Body body : bodies) {
                    graphics.fillOval((int) Math.round(Application.WIDTH * body.x / MAX_RADIUS + WIDTH / 2),
                            (int) Math.round(Application.HEIGHT * body.y / MAX_RADIUS + HEIGHT / 2),
                            5, 5);
                }
            });
        }

        private void init() {
            bodies = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Body body = new Body(RND.nextDouble(MIN_MASS, MAX_MASS),
                        new Vector(RND.nextDouble(MIN_SPEED, MAX_SPEED), RND.nextDouble(MIN_SPEED, MAX_SPEED)),
                        new Vector(RND.nextDouble(0, 10_000), RND.nextDouble(0, 10_000)),
                        RND.nextDouble(-MAX_RADIUS, MAX_RADIUS),
                        RND.nextDouble(-MAX_RADIUS, MAX_RADIUS)
                );
                bodies.add(body);
            }
        }

    }
}
