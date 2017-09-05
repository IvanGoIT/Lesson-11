/**
 * Created by baizor on 05.09.17.
 */
public class Something implements Runnable {
    public int x;

    public Something(int x) {
        this.x = x;
    }

    @Override
    public void run() {
        System.out.println("something выполняется " + x);
    }
}
