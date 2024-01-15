package com.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long blogId;
    private String title;
    private String author;
    private String content;
    private String date;
    private String imageUrl;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserProfile userProfile;
}
