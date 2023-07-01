package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions) {
        //add an image to the blog
        Optional<Blog> blogOptional = blogRepository2.findById(blogId);
//        if(!blogOptional.isPresent()){
//            throw new Exception("Blog not found");
//        }
        Blog blog = blogOptional.get();

        Image image=Image.builder()
                .description(description)
                .dimensions(dimensions)
                .blog(blog)
                .build();

        blog.getImageList().add(image);


        Blog blogResponse = blogRepository2.save(blog);

        Image responseImage = blogResponse.getImageList().get(blogResponse.getImageList().size()-1);
        responseImage.getBlog().setImageList(null);
        responseImage.getBlog().setUser(null);



        return responseImage;
    }

    public void deleteImage(Integer id) {
        Optional<Image> imageOptional = imageRepository2.findById(id);
//        if(!imageOptional.isPresent()){
//            throw new Exception("Image not found");
//        }
        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Optional<Image> imageOptional = imageRepository2.findById(id);
//        if(!imageOptional.isPresent()){
//            throw new Exception("Image not found");
//        }
        Image image = imageOptional.get();
        String[] dimensions = screenDimensions.split("X");
//        if (dimensions.length != 2) {
//            throw new IllegalArgumentException("Invalid screen dimensions");
//        }
        int screenWidth = Integer.parseInt(dimensions[0]);
        int screenHeight = Integer.parseInt(dimensions[1]);

        // Calculate the number of images that can fit in the screen
        String[] imageDimensions = image.getDimensions().split("X");

        int imageWidth = Integer.parseInt(imageDimensions[0]);
        int imageHeight = Integer.parseInt(imageDimensions[1]);;
        int imagesInWidth = screenWidth / imageWidth;
        int imagesInHeight = screenHeight / imageHeight;
        int totalImages = imagesInWidth * imagesInHeight;

        return totalImages;
    }
}
