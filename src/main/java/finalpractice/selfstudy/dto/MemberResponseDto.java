package finalpractice.selfstudy.dto;

import lombok.Data;

@Data
public class MemberResponseDto {

    private String name;

    public MemberResponseDto(String name) {
        this.name = name;
    }
}
