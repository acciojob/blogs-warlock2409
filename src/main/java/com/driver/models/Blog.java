package com.driver.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public
class Blog{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String title;
    String content;
    Date pubDate;

    @OneToMany(mappedBy = "blog",cascade = CascadeType.ALL)
    List<Image> imageList = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    User user;


}