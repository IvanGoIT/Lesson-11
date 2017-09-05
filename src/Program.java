import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Program {

    public static boolean isMainThread() {
        return Thread.currentThread().getId() == 1;
    }

    public static void main(String[] args) {
        // Способ первый
        Something something = new Something(0);

        // Способ второй (популярный)
        Runnable something2 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("something2 выполняется " + Thread.currentThread().getId());
            }
        };

        System.out.println("Запускаем потоки");
        for(int i = 0; i < 10; i++) {
            final int x = i;

            // Способ один
            Something runnable = new Something(x);
            new Thread(runnable).start();

            // Способ два (популярный)
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // поток спит 1000 миллисекунд (1 секунда)
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("это поток номер " + x);
                }
            }).start();
        }

        // Демонстрация смены приоритетов потока
        Thread thread = new Thread(something);
        thread.setPriority(7);
        thread.start();
        thread.setPriority(2);

        System.out.println("Потоки запущены");
    }
}
