package com.example.demo.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.handler.Utils;
import com.example.demo.model.Asset;
import com.example.demo.service.AssetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/cloudinary")
public class CloudinaryRestController {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private AssetService assetService;

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            Asset asset = new Asset(uploadResult.get("asset_id").toString(), uploadResult.get("secure_url").toString());
            assetService.save(asset);
            return Utils.generateResponseEntity(HttpStatus.OK, "Image Uploaded Successfully", uploadResult);
        } catch (IOException e) {
            return Utils.generateResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "Upload image failed");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getImage(){
        List<Asset> images = assetService.get();
        if(images != null){
            return Utils.generateResponseEntity(HttpStatus.OK, "Image Uploaded Successfully", images);
        }
        return Utils.generateResponseEntity(HttpStatus.OK, "No Image");
    }
}
