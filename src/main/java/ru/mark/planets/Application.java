package ru.mark.planets;

import javax.swing.*;
import java.security.SecureRandom;
import java.util.Random;

public class Application {
    private static final Random RND = new SecureRandom();

    public static void main(String[] args) {
        if (args.length < 2){
            System.out.println("Не указано количество планет");
            System.exit(0);
        }
        int count = Integer.parseInt(args[0]);
        if (count < 2){
            System.out.println("количество планет должно быть больше 1");
            System.exit(0);
        }
        for (int i = 0; i < count; i++) {

        }
    }

    private static final class Window extends JFrame {
        public Window(){
            super("Планеты");
        }

    }
}
