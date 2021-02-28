import java.util.Arrays;

public class Main {

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;
    static Float[] ARR = new Float[SIZE];

    public static void main(String[] args) throws InterruptedException {


        fillArrayWithFormula(ARR);
        fillArrayWithFormulaWithThreads(ARR);

    }

    public static void fillArrayWithUnits(Float[] arr) {

        Float n = 1.0f;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = n;
        }
    }

    public static void fillArrayWithFormula(Float[] arr) {

        fillArrayWithUnits(arr);

        long t1 = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - t1);

    }

    public static void printArray(Float[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void fillArrayWithFormulaWithThreads(Float[] arr) throws InterruptedException {
        Float[] arr1 = Arrays.copyOfRange(arr, 0, HALF);
        Float[] arr2 = Arrays.copyOfRange(arr, HALF, SIZE);

        fillArrayWithUnits(arr);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr1.length; i++) {
                    arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr2.length; i++) {
                    arr2[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
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

    }
}