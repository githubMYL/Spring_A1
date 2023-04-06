package exam01;

public class ImplCalculator implements Calculator {
    /** 반복문 방식 */
    public long factorial(long num) {

        int total = 1;
        for (int i = 1; i <= num; i++) {
            total *= i;
        }

        return total;

    }
}
