package models.member;

import lombok.Data;

import java.time.LocalDateTime;


/** @Getter @Setter @ToString 등등등 정보 애너테이션 */
@Data
/** DB 테이블 안에 들어갈 정보 */
public class Member {

    /** 회원 번호 */
    private long userNo;
    /** 회원 ID */
    private String userId;
    /** 회원 비밀번호 */
    private String userPw;
    /** 회원 이름 */
    private String userNm;
    /** 회원 가입시간 */
    private LocalDateTime regDt;
}