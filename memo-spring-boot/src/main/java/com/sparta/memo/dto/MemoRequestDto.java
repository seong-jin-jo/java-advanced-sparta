package com.sparta.memo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemoRequestDto {

    private String title;
    private String contents;
    private String username;
}
