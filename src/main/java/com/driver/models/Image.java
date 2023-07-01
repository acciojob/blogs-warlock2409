package com.driver.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public
class Image{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

   String description;
   String dimensions;

   @ManyToOne
   @JoinColumn
   Blog blog;

}