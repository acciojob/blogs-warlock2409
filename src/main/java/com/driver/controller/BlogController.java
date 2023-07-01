package com.driver.controller;

import com.driver.models.Blog;
import com.driver.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    BlogService blogService;
    @PostMapping("/create")
    public ResponseEntity createBlog(@RequestParam Integer userId ,
                                     @RequestParam String title,
                                     @RequestParam String content) throws Exception {
        // Create a blog and add it under given user
        try{
            Blog responseBlog= blogService.createAndReturnBlog(userId,title,content);
            return new ResponseEntity<>(responseBlog, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable int blogId) throws Exception {
        try{
            blogService.deleteBlog(blogId);
            return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        // Delete the blog using deleteById

    }
}




