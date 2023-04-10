package main;

import config.AppCtx;
import lombok.extern.java.Log;
import models.member.Member;
import models.member.MemberDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


/** 로그를 출력하는 애노테이션 */
@Log
public class Ex01 {
    /** 메인 실행 메서드 */
    public static void main(String[] args) {

        /** 스프링컨테이너 설정 */
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppCtx.class);

        /** MemberDao 에 있는 @Bean 을 통해 MemberDao 클래스의 정보를 가져온다 */
        MemberDao memberDao = ctx.getBean(MemberDao.class);

        /** Member 객체 생성 및 DB에 들어갈 Id, Pw, Nm 세팅 */
//        Member member = new Member();
//        member.setUserId("user01");
//        member.setUserPw("123456");
//        member.setUserNm("사용자01");

        /** Member 추가 메서드 */
//        boolean result = memberDao.insert(member);
//        System.out.println("추가 성공 : " + result);

        /** Member 삭제 메서드 */
//        boolean result = memberDao.delete("user02");
//        System.out.println("삭제 성공 : " + result);

        /** Member 전체를 출력 하는 메서드 */
        List<Member> members = memberDao.gets();
        members.stream().forEach(System.out::println);

        /** 조건에 맞는 Member 를 조회하는 메서드 */
//        Member member = memberDao.get("user03");
//        if (member != null) {
//            log.info(member.toString());
//        }

        /** 총 Member 가 몇 인지 로그를 찍는 메서드 */
        long total = memberDao.getTotal();
        log.info("총 회원 수 : " + total);

        /** 스프링컨테이너를 닫는다 */
        ctx.close();
    }
}