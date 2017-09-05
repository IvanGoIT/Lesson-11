/**
 * Created by baizor on 05.09.17.
 */
public class DeadLockExample {

    static final Object obj1 = new Object();
    static final Object obj2 = new Object();

    public static void main(String[] args) {
        System.out.println("Программа запустилась");

        new Thread(() -> {
            System.out.println("[1] блокирует 1");
            synchronized (obj1) {
                System.out.println("[1] заблокировал 1");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("[1] блокирует 2");
                synchronized (obj2) {
                    System.out.println("[1] заблокировал 2");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        new Thread(() -> {
            System.out.println("[2] блокирует 2");
            synchronized (obj2) {
                System.out.println("[2] заблокировал 2");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("[2] блокирует 1");
                synchronized (obj1) {
                    System.out.println("[2] заблокировал 1");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
