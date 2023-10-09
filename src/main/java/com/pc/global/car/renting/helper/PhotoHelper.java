package com.pc.global.car.renting.helper;

import com.pc.global.car.renting.customeResponse.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Log4j2
public class PhotoHelper
{
    private static final Set<String> validExtensionsImage = new HashSet<>();

    static
    {
        validExtensionsImage.add(".png");
        validExtensionsImage.add(".jpg");
        validExtensionsImage.add(".jpeg");
        validExtensionsImage.add(".jfif");
        validExtensionsImage.add(".pjpeg");
        validExtensionsImage.add(".pjp");
    }

    private static final Set<String> validExtensionsVideo = new HashSet<>();

    static
    {
        validExtensionsVideo.add(".mp4");
        validExtensionsVideo.add(".avi");
        validExtensionsVideo.add(".mov");
        validExtensionsVideo.add(".mkv");
        validExtensionsVideo.add(".wmv");
        validExtensionsVideo.add(".flv");
        validExtensionsVideo.add(".webm");
        validExtensionsVideo.add(".3gp");
        validExtensionsVideo.add(".ogg");
        validExtensionsVideo.add(".rmvb");
        validExtensionsVideo.add(".mpg");
        validExtensionsVideo.add(".mpeg");
        validExtensionsVideo.add(".ts");
    }

    public static boolean isImage(String... extensions)
    {
        for (String ext : extensions)
        {
            if (!validExtensionsImage.contains(ext.toLowerCase()))
            {
                log.error("extension:{} is not a valid extension", ext);
                return false;
            }
        }
        return true;
    }

    public static boolean isVideo(String... extensions)
    {
        for (String ext : extensions)
        {
            if (!validExtensionsVideo.contains(ext.toLowerCase()))
            {
                log.error("extension:{} is not a valid extension", ext);
                return false;
            }
        }
        return true;
    }

    public static String createPath(String directory, String fileName)
    {
        try
        {
            if (!directory.isEmpty() && !fileName.isEmpty())
            {

                String path = directory + "\\" + fileName;
                log.info("created path :{}", path);
                return path;
            }
            else
            {
                log.error("directory:{} or fileName:{} are null or empty", directory, fileName);
                throw new RuntimeException();
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


    public static Response storeFiles(String entityId, MultipartFile entityImage, String defaultPath, String name, Boolean isImage)
    {
        try
        {
            log.info("storing files for " + name);
            File directory = new File(defaultPath + "\\" + name + "_" + entityId);
            log.info("trying to create creating directory:{}", directory);
            if (!directory.exists())
            {
                log.info("creating directory:{}", directory);
                boolean created = directory.mkdir();
                if (!created)
                {
                    return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "unable to create directory " + directory.getName());
                }
                log.info("directory:{} created successfully", directory);
            }
            else
            {
                log.info("directory:{} already exists", directory);
            }

            String entityImageName = StringUtils.cleanPath(Objects.requireNonNull(entityImage.getOriginalFilename()));
            String entityImageExtension = entityImageName.split("(?=\\.[^.]+$)")[1];
            log.info("backIdName:{} ", entityImageName);
            if (isImage)
            {
                if (!isImage(entityImageExtension))
                {
                    return new Response(HttpStatus.BAD_REQUEST, "provided file is not an image");
                }
            }
            else
            {
                if (!isVideo(entityImageExtension))
                {
                    return new Response(HttpStatus.BAD_REQUEST, "provided file is not an image");
                }
            }
            String entityImagePath = createPath(directory.toString(), entityImageName);
            log.info("frontIdPath:{}", entityImagePath);
            if (!save(entityImagePath, entityImage))
            {
                return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "error while saving front id image");

            }

            return new Response(entityImagePath);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean save(String path, MultipartFile file)
    {
        try
        {
            if (!file.isEmpty())
            {
                FileOutputStream fout = new FileOutputStream(path);

                BufferedOutputStream bufStream = new BufferedOutputStream(fout);
                bufStream.write(file.getBytes());
                bufStream.close();
                return true;
            }
            return false;
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}