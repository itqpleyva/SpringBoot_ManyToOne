package itqpleyva.company.course;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldFilter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import itqpleyva.company.topic.Topic;

@RestController
public class CourseController {
	
	@Autowired
	private CourseService courseService;	
	
	@RequestMapping("/topics/{topicId}/courses")
	public List<Course> getAllCourses(@PathVariable String topicId){	
		return courseService.getAllCourses(topicId);
	}
	
	@RequestMapping("/topics/{topicId}/courses/{courseId}")
	public Optional<Course> getCourse( @PathVariable String courseId){	
		return courseService.getCourse(courseId);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics/{topicId}/courses")
	public Course postCourse( @RequestBody Course course, @PathVariable String topicId){
		course.setTopic(new Topic(topicId, "", ""));
		courseService.postCourse(course);
		return course;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{topicId}/courses/{id}")
	public List<Course> postCourse(@PathVariable String id){
		courseService.deleteCourse(id);
		return getAllCourses();
	}
	
	@RequestMapping(method=RequestMethod.PATCH, value="/topics/{topicId}/courses/{id}")
	public Optional<Course> patchCourse( @RequestBody Map<String, String> field,  @PathVariable String id){
		return courseService.modifyCourseAtributte(id, field);
	}
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
	public Course putCourse( @RequestBody Course course,  @PathVariable String id){

		courseService.modifyCourse(course);
		return course;
	}
}
