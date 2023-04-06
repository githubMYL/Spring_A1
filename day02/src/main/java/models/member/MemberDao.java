package models.member;

import config.ManualBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component
@ManualBean
public class MemberDao {
    private static Map<String, Member> members = new HashMap<>();

    public void insert (Member member) {
        System.out.println("insert 메소드가 전달받은 파라메타 : " +member);
        member.setRegDt(LocalDateTime.now());
        members.put(member.getUserId(), member);


    }

    public Member get(String userId) {
        return members.get(userId);
    }

    public List<Member> gets() {

        List<Member> list = new ArrayList<>(members.values());

        return list;
    }
}
