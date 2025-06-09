package com.hitss.springboot.app_apirest.services.impl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class userDTO {
    private Long id;
    private String username;
    private String email;
}
