package controllers.members;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class Join {
    /** @NotBlank 검증을 알려주는 애노테이션 */
    @NotBlank(message = "아이디를 입력하세요.😭")
    @Size(min=6, max=16)
    private String userId;

    @NotBlank(message = "비밀번호를 입력하세요.😭")
    @Size(min=8)
    private String userPw;

    @NotBlank
    private String userPwRe;

    @NotBlank
    private String userNm;

    @Email
    private String email;

    @AssertTrue
    private boolean agree;
}
