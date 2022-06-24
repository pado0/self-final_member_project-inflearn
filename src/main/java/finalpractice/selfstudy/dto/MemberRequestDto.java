package finalpractice.selfstudy.dto;

import finalpractice.selfstudy.entity.Address;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
public class MemberRequestDto {

    @NotBlank
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
