package com.example.springbootweb02.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class Multiple {
    @RequestMapping("/UploadTest")
    public String upLoad(){
        return "UploadTest";
    }

    @PostMapping("/upload")
    public String upload(@RequestPart("avatar") MultipartFile avatar,
                         @RequestPart("photo") MultipartFile[] photo){
        if (!avatar.isEmpty()){
            try {
                avatar.transferTo(new File("C:\\Users\\YQ\\IdeaProjects\\"+avatar.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (photo.length > 0){
            for (MultipartFile multipartFile : photo) {
                try {
                    multipartFile.transferTo(new File("C:\\Users\\YQ\\IdeaProjects\\"+multipartFile.getOriginalFilename()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "index";
    }
}
