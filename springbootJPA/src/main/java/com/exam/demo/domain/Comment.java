package com.exam.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Comment {
    @Id @GeneratedValue
    private Long id;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    private Integer likeCount = 0;
}
