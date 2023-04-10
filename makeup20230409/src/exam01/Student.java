package exam01;

public class Student {

    /** 멤버 변수 (속성) */
    int id;         /** 학번 */
    String name;    /** 학생명 */
    String subject; /** 전공과목 */

    void study() {
        System.out.println(name + "이"+subject+"를"+"공부한다");
    }
}
