package top.hongchen05.tweek03.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class Appconfig {
    private String appName;
    private String version;
    private String description;
    private Boolean published;
    private Author author;
    private List<String> features;

    /**
     * 静态内部类
     */
   @Data
    private static class Author {
        private String name;
        private String website;
        private String email;
    }
}

