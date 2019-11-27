package com.exam.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @Temporal(TemporalType.TIMESTAMP)   //
    private Date created = new Date();

    @Transient //컬럼 매핑 제외
    private String yes;

    @Transient //컬럼 매핑 제외
    private String no;
}
