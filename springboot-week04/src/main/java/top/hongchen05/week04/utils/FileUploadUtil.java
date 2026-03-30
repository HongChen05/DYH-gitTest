package top.hongchen05.week04.utils;

import org.springframework.web.multipart.MultipartFile;
import top.hongchen05.week04.exception.BusinessException;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class FileUploadUtil {

    // 上传目录 → 项目根目录 / upload
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/upload/";

    // 白名单
    private static final Set<String> ALLOW_TYPES = Set.of(
            ".jpg", ".jpeg", ".png", ".gif", ".bmp",
            ".pdf", ".txt", ".doc", ".docx", ".xls", ".xlsx"
    );

    static {
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static String upload(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException(400, "文件不能为空");
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.contains(".")) {
            throw new BusinessException(400, "文件名不合法");
        }

        String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        if (!ALLOW_TYPES.contains(suffix)) {
            throw new BusinessException(400, "不支持的文件类型：" + suffix);
        }

        String uuidName = UUID.randomUUID() + suffix;
        File dest = new File(UPLOAD_DIR + uuidName);

        try {
            file.transferTo(dest);
            return uuidName;
        } catch (IOException e) {
            throw new BusinessException(500, "上传失败");
        }
    }
}