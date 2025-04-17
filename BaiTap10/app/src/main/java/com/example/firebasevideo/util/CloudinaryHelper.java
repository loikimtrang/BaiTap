package com.example.firebasevideo.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.InputStream;
import java.util.Map;

public class CloudinaryHelper {
    private static final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dunizzku7",
            "api_key", "734565825852793",
            "api_secret", "4k0u2xwn_Ip-gKwK1br53t1LQdE"
    ));
    public static String uploadVideo(InputStream inputStream) {
        try {
            Map uploadResult = cloudinary.uploader().upload(inputStream, ObjectUtils.asMap(
                    "resource_type", "video"
            ));
            inputStream.close();
            return uploadResult.get("secure_url").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
