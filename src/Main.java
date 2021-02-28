import java.util.Arrays;

public class Main {

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;
    static Float[] ARR = new Float[SIZE];

    public static void main(String[] args) throws InterruptedException {


        fillArrayWithFormula(ARR);
        fillArrayWithFormulaWithThreads(ARR);

    }


    public static void fillArrayWithFormula(Float[] arr) {

        Arrays.fill(arr, 1.0f);

        long t1 = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - t1);
        System.out.println("элемент середины \n");
        System.out.println(arr[HALF]);

    }


    public static void fillArrayWithFormulaWithThreads(Float[] arr) throws InterruptedException {
        Arrays.fill(arr, 1.0f);

        Float[] arr1 = Arrays.copyOfRange(arr, 0, HALF);
        Float[] arr2 = Arrays.copyOfRange(arr, HALF, SIZE);

        Thread thread1 = new Thread(new Runnable() {
            @Override

            public void run() {
                for (int i = 0; i < HALF; i++) {
                    arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < HALF; i++) {
                    arr2[i] = (float) (arr2[i] * Math.sin(0.2f + (HALF + i) / 5) * Math.cos(0.2f + (HALF + i) / 5) * Math.cos(0.4f + (HALF + i) / 2));
                }
            }
        });
        long t1 = System.currentTimeMillis();

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.arraycopy(arr1, 0, arr, 0, HALF);
        System.arraycopy(arr2, 0, arr, HALF, HALF);
        System.out.println(System.currentTimeMillis() - t1);
        System.out.println("элемент середины ");
        System.out.println(arr[HALF]);

    }
}