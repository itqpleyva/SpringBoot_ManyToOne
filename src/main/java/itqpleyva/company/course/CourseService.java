package itqpleyva.company.course;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
/*	
	private List<Topic> topics = new ArrayList(Arrays.asList(
			 new Topic("spring1", "spring1", "description spring1"),
			 new Topic("spring2", "spring2", "description spring2"),
			 new Topic("spring3", "spring3", "description spring3"),
			 new Topic("spring4", "spring4", "description spring4")
			));
	*/
	public List<Course> getAllCourses(){
		List<Course> courses = new ArrayList<>();
		courseRepository.findAll()
		.forEach(courses::add);
		return courses;
	}
	public Optional<Course> getCourse(String id) {
		
		return courseRepository.findById(id);
	}
	
	public void postCourse(Course course){
		courseRepository.save(course);
	}
	
	public void deleteCourse(String courseId){
		courseRepository.deleteById(courseId);
	}
	
	public void modifyCourse(Course course){
		courseRepository.save(course);
	}
	
	public Optional<Course> modifyCourseAtributte(String id, Map<String, String> field){
	 Optional<Course> course =  getCourse(id);
	    field.forEach((k, v) -> {
		   	 if(k =="name") {
				 course.get().setName(v);
			 }
			 else if(k =="description") {
				 course.get().setDescription(v);
			 }
	    });
	    courseRepository.save(course.get());
	  return course;
	}
}
