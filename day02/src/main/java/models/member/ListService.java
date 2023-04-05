package models.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListService {
    @Autowired
//    @Qualifier("mDao2") /** 충돌시 문제해결할때 사용 어떤걸 주입받는지 명시 해준다 */
    private MemberDao memberDao;
    public ListService() {}

    /** 주석 처리해도 사용 가능 */
//    public ListService(MemberDao memberDao) {
//        this.memberDao = memberDao;
//    }

    public ListService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void print() {
        List<Member> members = memberDao.gets();
        members.stream().forEach(System.out::println);
    }
}
