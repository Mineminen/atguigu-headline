package com.atguigu.headline.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsUser {
    private Integer uid;
    private String username;
    private String userPwd;
    private String nickName;
}