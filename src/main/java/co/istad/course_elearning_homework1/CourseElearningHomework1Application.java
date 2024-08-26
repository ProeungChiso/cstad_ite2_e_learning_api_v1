package co.istad.course_elearning_homework1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class CourseElearningHomework1Application {

	public static void main(String[] args) {
		SpringApplication.run(CourseElearningHomework1Application.class, args);
	}

}
