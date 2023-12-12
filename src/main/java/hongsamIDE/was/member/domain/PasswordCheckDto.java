package hongsamIDE.was.member.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PasswordCheckDto {

    private String password;

    public PasswordCheckDto() {
    }

    public PasswordCheckDto(String password) {
        this.password = password;
    }
}
