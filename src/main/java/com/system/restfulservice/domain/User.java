package com.system.restfulservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = { "password", "ssn" }, allowSetters = true)
@Schema(description = "사용자 상세 정보를 위한 객체")
public class User {

    @Schema(description = "사용자 ID는 자동생성")
    private Long id;

    @Schema(title = "사용자명", description = "사용자 이름 입력")
    @Size(min = 2, message = "이름은 2글자 이상 입력해주세요.")
    private String name;

    @Schema(title = "사용자 등록일", description = "입력하지 않으면 현재날짜 저장")
    @Past
    private Date joinDate;

    @Schema(title = "사용자 비밀번호", description = "사용자 비밀번호 입력")
    @NotBlank(message = "비밀번호는 필수 입니다.")
    private String password;

    @Schema(title = "사용자 주민등록번호", description = "사용자 주민등록번호 입력")
    @NotBlank(message = "주민등록번호는 필수 입니다.")
    private String ssn;

}
