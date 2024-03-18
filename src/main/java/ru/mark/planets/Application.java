package ru.mark.planets;

import ru.mark.planets.models.Body;
import ru.mark.planets.models.Vector;

import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

public class Application {
    private static final Random RND = new SecureRandom();
    public static final double MIN_MASS = Math.pow(10, 9);
    public static final double MAX_MASS = Math.pow(10, 12);

    public static final double MIN_SPEED = Math.pow(10, 2);
    public static final double MAX_SPEED = Math.pow(10, 4);

    public static final double MAX_RADIUS = Math.pow(10, 13);

    public static final int SIZE = 800;
    public static final double COEFFICIENT = Application.SIZE / (2 * MAX_RADIUS);

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

        try {
            SwingUtilities.invokeAndWait(() -> {
                Window window = new Window(count);
                window.setLocationRelativeTo(null);
                window.setBounds(10, 10, SIZE, SIZE);
                window.setLayout(null);
                window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                window.setVisible(true);
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't successfully complete");
        }
    }

    private static final class Window extends JFrame {
        /**
         * Количество планет.
         */
        private final int count;
        /**
         * Планеты.
         */
        private List<Body> bodies;

        public Window(int count) {
            super("Планеты");
            this.count = count;
            init();
            getRootPane().setBackground(Color.BLUE);
            setBackground(Color.WHITE);

            JButton button = new JButton("press me");
            button.setBounds(0, 0, 120, 25);
            add(button);
            button.addActionListener(e -> {
                //Рисуем первоначальные позиции.
                Graphics graphics = getGraphics();
                graphics.setColor(Color.RED);
                //Рисуем центр.
                graphics.fillOval(Application.SIZE / 2, Application.SIZE / 2, 20, 20);
                for (Body body : bodies) {
                    body.paint(graphics, Color.BLACK);
                }

                //Запускаем поток для прорисовки движения.
                java.util.Timer timer = new java.util.Timer();
                timer.schedule(new MyTimerTask(graphics, bodies), 0);
            });
        }

        private void init() {
            bodies = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Body body = new Body(i,
                        RND.nextDouble(MIN_MASS, MAX_MASS),
                        new Vector(RND.nextDouble(MIN_SPEED, MAX_SPEED), RND.nextDouble(MIN_SPEED, MAX_SPEED)),
                        new Vector(RND.nextDouble(0, 10_000), RND.nextDouble(0, 10_000)),
                        RND.nextDouble(-MAX_RADIUS, MAX_RADIUS),
                        RND.nextDouble(-MAX_RADIUS, MAX_RADIUS)
                );
                bodies.add(body);
            }
        }
    }

    static class MyTimerTask extends TimerTask {
        private Graphics graphics;
        private List<Body> bodies;
        //Временной интервал, за который рассчитывается движение.
        private static final int t = 10;

        public MyTimerTask(Graphics graphics, List<Body> bodies) {
            this.graphics = graphics;
            this.bodies = bodies;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    return;
                }
                movePlanets();
            }
        }

        private void movePlanets() {
            //Пробегаемся по всем телам.
            for (Body currentBody : bodies) {
                currentBody.paint(graphics, Color.WHITE);

                Vector newSpeed = new Vector(0, 0);
                Vector newAccelerate = new Vector(0, 0);
                //Пробегаемся по всем телам, которые не совпадают с текущим, т.е. перебираем все остальные.
                for (Body anotherBody : bodies) {
                    if (!anotherBody.equals(currentBody)) {
                        //Выбрали другую планету.
                        //Здесь надо посчитать все действующие силы и вычислить новый вектор скорости.
                        //TODO

                    }
                }
                ///ИМИТАЦИЯ изменений. Необходимо удалить как только будут нормально реализованы вычисления
                newSpeed.x = newSpeed.x + RND.nextDouble(-1000000000, 1000000000);
                newSpeed.y = newSpeed.y + RND.nextDouble(-1000000000, 1000000000);

                newAccelerate.x = newAccelerate.x + RND.nextDouble(-10000000, 10000000);
                newAccelerate.x = newAccelerate.x + RND.nextDouble(-10000000, 10000000);
                ///ИМИТАЦИЯ изменений. Необходимо удалить как только будут нормально реализованы вычисления


                currentBody.y = currentBody.y + newSpeed.y * t + newAccelerate.y * t * t / 2; //x+vt+(at^2)/2.
                currentBody.x = currentBody.x + newSpeed.x * t + newAccelerate.x * t * t / 2;
                currentBody.paint(graphics, Color.BLACK);
            }
        }
    }
}
