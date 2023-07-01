package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;


    public Blog createAndReturnBlog(Integer userId, String title, String content) throws Exception {
        //create a blog at the current time
        Optional<User> user = userRepository1.findById(userId);
        if(!user.isPresent()){
            throw new Exception("User not Found");
        }
        User blogUser = user.get();

        Blog newLog = Blog.builder()
                .user(blogUser)
                .content(content)
                .pubDate(new Date())
                .title(title)
                .build();
        blogUser.getBlogList().add(newLog);

        User responseUser = userRepository1.save(blogUser);

        Blog resposeBlog = responseUser.getBlogList().get(responseUser.getBlogList().size()-1);
        resposeBlog.getUser().setBlogList(null);

        return resposeBlog;
    }

    public void deleteBlog(int blogId) throws Exception {
        //delete blog and corresponding images
        Optional<Blog> blog = blogRepository1.findById(blogId);
        if(!blog.isPresent()){
           throw new Exception("Blog not found");
        }
        blogRepository1.deleteById(blogId);


    }
}
