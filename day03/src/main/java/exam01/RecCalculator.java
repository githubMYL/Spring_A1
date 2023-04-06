package exam01;

public class RecCalculator implements Calculator {
    /** 재귀적 방식 */
    public long factorial(long num) {

        if (num == 0) {
            return 1;
        }

        return num * factorial(num - 1);

    }
}