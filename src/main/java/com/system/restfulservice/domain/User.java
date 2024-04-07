package com.system.restfulservice.domain;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    private Long id;

    @Size(min = 2, message = "이름은 2글자 이상 입력해주세요.")
    private String name;
    @Past
    private Date joinDate;

}
