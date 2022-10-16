package com.tms.dto;

import com.tms.model.Book;
import com.tms.model.ROLE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Integer id;
    private String login;
    private String password;
    private ROLE role;
    private Date created;
    private Date updated;
    private List<Book> books = new ArrayList<>();
}
