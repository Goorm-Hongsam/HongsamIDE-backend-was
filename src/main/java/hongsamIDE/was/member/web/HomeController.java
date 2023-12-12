package hongsamIDE.was.member.web;

import hongsamIDE.was.member.domain.LoginMemberResponse;
import hongsamIDE.was.member.domain.MemberResponse;
import hongsamIDE.was.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping("/")
    public MemberResponse home(HttpServletRequest request, @SessionAttribute(value = "loginMember", required = false) LoginMemberResponse loginMemberResponse) {

        HttpSession session = request.getSession(false);

        if (session == null || loginMemberResponse == null) {
            return new MemberResponse(400, "로그인 정보 없음");
        }

        return new MemberResponse(200, loginMemberResponse);
    }

    // 코드편집기 접근 uuid 반환
    @GetMapping("/question/{questionId}")
    public MemberResponse getUUID(HttpServletRequest request, @SessionAttribute(value = "loginMember", required = false)
    LoginMemberResponse loginMemberResponse, @PathVariable("questionId") String questionId) {

        HttpSession session = request.getSession(false);

        if (session == null || loginMemberResponse == null) {
            return new MemberResponse(400, "로그인 정보 없음");
        }

        return memberService.getUUID(loginMemberResponse.getEmail());

    }
}
