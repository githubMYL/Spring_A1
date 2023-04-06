package exam01;

public class ProxyCalculator implements Calculator {
    /** Proxy 방식 */
    /** 디자인패턴 - 데코레이터 패턴 */
    private Calculator calculator;

    public ProxyCalculator(Calculator calculator){
        this.calculator = calculator;
    }
    @Override
    public long factorial(long num) {

        long startTime = System.nanoTime();         /** 시작시간 - 공통기능 */

        long result = calculator.factorial(num);    /** 핵심기능 (대신 수행) */

        long endTime = System.nanoTime();           /** 끝난시간 - 공통기능 */
        System.out.printf("걸린 시간 :%d%n", endTime - startTime);

        return result;
    }
}
