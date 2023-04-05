package models.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    @Autowired  /** 스프링이 대신해서 객체를 주입해 준다 */
//    @Qualifier("mDao1") /** 충돌시 문제해결할때 사용 어떤걸 주입받는지 명시 해준다 */
    private MemberDao memberDao;


    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void join(Member member) {
        memberDao.insert(member);
    }
}
