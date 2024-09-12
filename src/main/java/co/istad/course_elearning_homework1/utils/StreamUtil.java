package co.istad.course_elearning_homework1.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StreamUtil {
    @Value("${stream.image}")
    private String streamImage;

    public String buildImageUri() {
        return streamImage;
    }
}
