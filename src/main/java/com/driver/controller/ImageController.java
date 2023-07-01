package com.driver.controller;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    ImageService imageService;

    @PostMapping("/{blogId}/add-image")
    public ResponseEntity<String> addImage(@PathVariable int blogId, @RequestParam String description, @RequestParam String dimensions) throws Exception {
        // Add image into the give blog
        try{
            imageService.addImage(blogId,description,dimensions);
            return new ResponseEntity<>("Added image successfully", HttpStatus.OK);
        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            throw new Exception(e.getMessage());

        }

    }

    @GetMapping("/countImagesInScreen/{id}/{screenDimensions}")
    public ResponseEntity<Integer> countImagesInScreen(@PathVariable int id, @PathVariable String screenDimensions) throws Exception {
        try{
            int res= imageService.countImagesInScreen(id,screenDimensions);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            throw new Exception(e.getMessage());

        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable int id) throws Exception {
        try{
            imageService.deleteImage(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            throw new Exception(e.getMessage());

        }
        // delete image using deleteById

    }
}



