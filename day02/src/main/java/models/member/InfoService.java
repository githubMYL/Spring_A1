package models.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class InfoService {
    /*@Autowired
    private Optional<MemberDao> opt;*/
    private MemberDao memberDao;
    public InfoService(MemberDao memberDao){
        this.memberDao = memberDao;
    }
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    public void print(String userId) {
        Member member = memberDao.get(userId);


        if (formatter != null) {
            String dateStr = formatter.format(member.getRegDt());
            member.setReDtStr(dateStr);
        }

        System.out.println(member);
    }

//    @Autowired(required = false)    /** 객체가 없으면 아예 호출을 안함 */
    @Autowired
    public void setFomatter(@Nullable DateTimeFormatter formatter) {    /** 호출하고 null값을 호출한다*/
        this.formatter = formatter;
    }
}
