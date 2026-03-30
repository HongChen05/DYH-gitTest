package top.hongchen05.week04.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.hongchen05.week04.common.Result;
import top.hongchen05.week04.exception.BusinessException;
import top.hongchen05.week04.utils.FileUploadUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/file")
public class UploadController {
    private static final String FILE_URL_PREFIX = "http://localhost:8080/upload/";

    // 单文件上传
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException(400, "文件不能为空");
        }
        try {
            String fileName = FileUploadUtil.upload(file);
            String url = FILE_URL_PREFIX + fileName;
            return Result.success(url);
        } catch (Exception e) {
            throw new RuntimeException("文件上传失败", e);
        }
    }

    // 批量文件上传
    @PostMapping("/upload/batch")
    public Result<List<String>> uploadBatch(@RequestParam("files") MultipartFile[] files) {
        if (files == null || files.length == 0) {
            throw new BusinessException(400, "文件不能为空");
        }
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;
            try {
                String fileName = FileUploadUtil.upload(file);
                urls.add(FILE_URL_PREFIX + fileName);
            } catch (Exception e) {
                throw new RuntimeException("文件上传失败: " + file.getOriginalFilename(), e);
            }
        }
        return Result.success(urls);
    }
}