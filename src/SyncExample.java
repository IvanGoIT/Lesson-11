import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by baizor on 05.09.17.
 */
public class SyncExample {
    static final Semaphore SEMAPHORE = new Semaphore(1);

    static List<Integer> array = new ArrayList<>();
    static {
        for(int i = 0; i < 5; i++)
            array.add(i + 1);
    }

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // начало работы с листом
                // --------------------------------
                try {
                    // зарезервировали слот
                    SEMAPHORE.acquire();
                    System.out.println("[1]+");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int lastIndex = array.size() - 1;
                System.out.println("[1]Взят индекс последнего элемента листа");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("[1]Поток проснулся и пытается взять по индексу значение из листа");
                int value = array.get(lastIndex);

                // освобождаем слот
                SEMAPHORE.release();
                System.out.println("[1]-");
                // --------------------------------
                // конец работы с листом


                System.out.println("[1]Последний элемент листа = " + value);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("[2]Поток засыпает");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("[2]Поток проснулся");

                // начало работы с листом
                // --------------------------------
                try {
                    // зарезервировали слот
                    SEMAPHORE.acquire();
                    System.out.println("[2]+");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                array.remove(array.size() - 1);
                SEMAPHORE.release();
                System.out.println("[2]-");
                // --------------------------------
                // конец работы с листом
                System.out.println("[2]Поток удалил элемент из листа");
            }
        }).start();
    }
}
