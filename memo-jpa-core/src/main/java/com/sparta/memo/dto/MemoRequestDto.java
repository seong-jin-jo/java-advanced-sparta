package com.sparta.memo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemoRequestDto {
    private String memo;
    private String username;
    private String contents;
}
