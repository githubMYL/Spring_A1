package config;

import models.member.InfoService;
import models.member.JoinService;
import models.member.ListService;
import models.member.MemberDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.format.DateTimeFormatter;

/** 스프링 컨테이너 */
/** Bean 을 사용해서 객체를 알려주고 관리한다 */
@Configuration
//@Import(AppCtx2.class)  /** 여러가지 값을 넣을때 중괄호를 사용해서 배열로 넣을 수 있으며 한개일때는 그냥 넣는다 */
public class AppCtx {
    @Bean
    @Qualifier("mDao1")
    public MemberDao memberDao() {
        return new MemberDao();
    }
   /**
    *  @Bean
    @Qualifier("mDao2")
    public MemberDao memberDao2() {
        return new MemberDao();
    }
    */
    @Bean
    public JoinService joinService() {
        return new JoinService();
    }
    @Bean
    public ListService listService() {
        return new ListService();
    }

    @Bean
    public InfoService infoService() {
        return new InfoService(memberDao());
    }

    @Bean
    public DateTimeFormatter dateTimeFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

        return formatter;
    }
}
