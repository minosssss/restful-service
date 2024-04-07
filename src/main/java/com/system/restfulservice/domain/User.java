package com.system.restfulservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = { "password", "ssn" }, allowSetters = true)
public class User {
    private Long id;

    @Size(min = 2, message = "이름은 2글자 이상 입력해주세요.")
    private String name;

    @Past
    private Date joinDate;

    @NotBlank(message = "비밀번호는 필수 입니다.")
    private String password;

    @NotBlank(message = "주민등록번호는 필수 입니다.")
    private String ssn;

}
