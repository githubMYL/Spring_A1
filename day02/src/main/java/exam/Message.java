package exam;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Message implements InitializingBean, DisposableBean {
    public void send(String msg) {
        System.out.printf("전송 메세지 : %s%n", msg);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("AfterPropertySet!!!");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Destroy!!");    /** ctx. close() 가 실행될때 실행됨 */
    }
}
