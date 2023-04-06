package exam01;

public class Ex01 {
    public static void main(String[] args) {

        long startTime = System.nanoTime();

        ImplCalculator cal1 = new ImplCalculator();
        long result1 = cal1.factorial(10);
        System.out.printf("cal1=%d%n", result1);

        long endTime = System.nanoTime();
        System.out.printf("반복문방식 걸린시간 : %d%n", endTime - startTime);

        long startTime1 = System.nanoTime();
        RecCalculator cal2 = new RecCalculator();
        long result2 = cal2.factorial(10);
        System.out.printf("cal2=%d%n", result2);
        long endTime1 = System.nanoTime();
        System.out.printf("재귀적방식 걸린시간 : %d%n", endTime1 - startTime1);
    }
}