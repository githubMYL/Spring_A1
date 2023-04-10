package models.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/** AppCtx 의 ComponentScan 의 설정범위안에 있기에 MemberDao 클래스 하위의 메서드들이 빈을 생성하여
    ApplicationContext 에 등록함 */
@Component
/** 이 위치에 작성함으로 클래스 하위에 있는 전체 모든 메서드에 트랜잭션상태 적용 */
@Transactional
public class MemberDao {


    /** JavaDataBaseConnection 의 약자인듯! 영어 뜻 그대로 자바랑 DB랑 연결해준다 */
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 회원정보 추가
     *
     * @param member
     */
//    @Transactional  /** 이 메서드에만 적용 */
//    public long insert(Member member) {
//        String sql = "INSERT INTO MEMBER (userNo, userId, userPw, userNm)" +
//                        "VALUES (SEQ_MEMBER.nextval, ?, ?, ?)";
//        /** 11:27분 람다식으로 변경 */
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        int cnt = jdbcTemplate.update( c ->{
//                PreparedStatement pstmt = c.prepareStatement(sql, new String[]{"userNo"});
//                pstmt.setString(1, member.getUserId());
//                pstmt.setString(2, member.getUserPw());
//                pstmt.setString(3, member.getUserNm());
//                return pstmt;
//
//        }, keyHolder);
//
//        Number keyValue = keyHolder.getKey();   /** 증감번호 - long, int */
//        long userNo = keyValue.longValue();
//
//        return userNo;
//    }

    /** Insert into member 회원이 추가되면 참을 반환을 콘솔창에 띄운다 */
    public boolean insert(Member member) {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        String sql = "INSERT INTO MEMBER (USERNO, USERID, USERPW, USERNM) " +
                "VALUES (SEQ_MEMBER.nextval, ?, ?, ?)";
        int cnt = jdbcTemplate.update(sql, member.getUserId(), member.getUserPw(), member.getUserNm());
        // cnt - 반영된 레코드 갯수 -> 0개 이상이면 성공 ?????????

        return cnt > 0;
    }

    /**
     * 회원정보를 아이디로 조회
     *
     * @param userId
     * @return
     */
    public Member get(String userId){
        try {
            String sql = "SELECT * FROM MEMBER WHERE userId = ?";
            Member member = jdbcTemplate.queryForObject(sql, this::memberMapper, userId);

            return member;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 회원 목록 조회
     *
     * @return
     */
//    public List<Member> gets() {
//        String sql = "SELECT * FROM MEMBER";
//        List<Member> members = jdbcTemplate.query(sql, new RowMapper<Member>() {
//            @Override
//            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//                Member member = new Member();
//                member.setUserNo(rs.getLong("USERNO"));
//                member.setUserId(rs.getString("USERID"));
//                member.setUserPw(rs.getString("USERPW"));
//                member.setUserNm(rs.getString("USERNM"));
//                member.setRegDt(rs.getTimestamp("REGDT").toLocalDateTime());
//                return member;
//            }
//        });
//        return members;
//    }
    /** 람다으로 변환 */
    public List<Member> gets() {
        String sql = "SELECT * FROM MEMBER";
        List<Member> members = jdbcTemplate.query(sql, this::memberMapper);

        return members;
    }

    /** DB 에서 받아온 정보를 세팅해준다 */
    private Member memberMapper(ResultSet rs, int i) throws SQLException {
        Member member = new Member();
        member.setUserNo(rs.getLong("USERNO"));
        member.setUserId(rs.getString("USERID"));
        member.setUserPw(rs.getString("USERPW"));
        member.setUserNm(rs.getString("USERNM"));
        member.setRegDt(rs.getTimestamp("REGDT").toLocalDateTime());
        return member;
    }

    /** 회원삭제를 성공하면 true 반환 */
    public boolean delete(String userId) {
        String sql = "DELETE FROM MEMBER WHERE USERID = ?";
        int cnt = jdbcTemplate.update(sql, userId);

        return cnt > 0;
    }
    /**
     * 전체 회원 수
     * */

    public long getTotal() {
        long total = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM MEMBER", Long.class);
        return total;
    }
}