package hongsamIDE.was.member.web;

import hongsamIDE.was.member.domain.*;
import hongsamIDE.was.member.repository.MemberRepository;
import hongsamIDE.was.member.service.MemberService;
import hongsamIDE.was.member.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypage")
public class MemberInfoController {

    private final S3Service s3Service;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    // 프로필 사진 수정
    @PostMapping("/profile-img")
    public MemberResponse updateProfileImg(@RequestParam("profileImg") MultipartFile multipartFile,
                                           @SessionAttribute(value = "loginMember", required = false) LoginMemberResponse loginMember) throws IOException {

        if (loginMember == null) {
            return new MemberResponse(400, "로그인 유저 정보 없음");
        }

        Member member = memberRepository.findMemberByEmailOne(loginMember.getEmail());

//      s3에 파일 올리기
        String imgUrl = s3Service.uploadFiles(member.getUuid(), multipartFile, "image");

        loginMember.setProfileUrl(imgUrl);

        return new MemberResponse(200,imgUrl);
    }

    // 회원 정보 수정
    @PutMapping("/info")
    public MemberResponse updateMemberInfo(@RequestBody MemberUpdateDto memberUpdateDto,
                                           @SessionAttribute(value = "loginMember", required = false) LoginMemberResponse loginMember) {

        if (loginMember == null) {
            return new MemberResponse(400, "로그인 유저 정보 없음");
        }

        return memberService.updateMemberInfo(loginMember, memberUpdateDto.getUsername(),memberUpdateDto.getPassword());
    }

    // 비밀번호 확인
    @PostMapping("/pw-check")
    public MemberResponse checkPassword(@RequestBody PasswordCheckDto passwordCheckDto,
                                        @SessionAttribute(value = "loginMember", required = false) LoginMemberResponse loginMember) {

        if (loginMember == null) {
            return new MemberResponse(400, "로그인 유저 정보 없음");
        }

        return memberService.checkPassword(passwordCheckDto.getPassword(), loginMember.getEmail());
    }

    // 회원 탙퇴
    @DeleteMapping("/members")
    public MemberResponse deleteMember(@SessionAttribute(value = "loginMember", required = false) LoginMemberResponse loginMember) {

        if (loginMember == null) {
            return new MemberResponse(400, "로그인 유저 정보 없음");
        }

        return memberService.deleteMember(loginMember.getEmail());
    }

    // 사진 파일 삭제용 테스트
    @PostMapping
    public void test() throws IOException {
        String url = "";

        s3Service.deleteFile(url.split("/")[3]);
    }


}
