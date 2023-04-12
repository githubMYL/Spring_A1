package controllers.members;


import lombok.RequiredArgsConstructor;
import models.member.MemberDao;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator {

    private final MemberDao memberDao;

    @Override
    public boolean supports(Class<?> clazz) {
        /** Join 커맨드 객체로 검증을 한정 */
        return Join.class.isAssignableFrom(clazz);
    }

    /**
     * 1. 필수 항목 체크 - userId, userPw, userPwRe, userNm
     * 2. userId 중복여부 - 이미 가입된 경우는 가입 불가
     * 3. userPw, userPwRe의 일치 여부
     * 4. 약관 동의 여부
     * */
    @Override
    public void validate(Object target, Errors errors) {
        Join join  = (Join)target;

        String userId = join.getUserId();
        String userPw = join.getUserPw();
        String userPwRe = join.getUserPwRe();

        /** 2. userID 중복 여부 - 이미 가입된 경우는 가입 불가 */
        if (userId != null && !userId.isBlank() && memberDao.isExists(userId)) {
            errors.rejectValue("userId", "Duplicated");
        }

        /** 3. userPw, userPwRe의 일치 여부 */
        if (userPw !=null && !userPw.isBlank() && userPwRe != null
                && !userPwRe.isBlank() && !userPw.equals(userPwRe)) {
            errors.rejectValue("userPwRe", "Incorrect");
        }

        /** 공통 에러 테스트 */
//        errors.reject("common", "공통 에러 테스트!🤔");


        /** 1. 필수 항목 체크 */
//
//        if (userId == null || userId.isBlank()) {
//            errors.rejectValue("userId", "Required2", "아이디 입력(기본)");
//        }
//
//        if (userPw == null || userPw.isBlank()) {
//            errors.rejectValue("userPw", "Required");
//        }
//
//        if (userPwRe == null || userPwRe.isBlank()) {
//            errors.rejectValue("userPwRe", "Required");
//        }
//
//        if (userNm == null || userNm.isBlank()) {
//            errors.rejectValue("userNm", "Required");
//        }
        /** 위에방식을 이런식으로 코드를 줄여서 사용 쓰는 빈도가 많지는 않음 */
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPw", "Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPwRe", "Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userNm", "Required");

    }
}
