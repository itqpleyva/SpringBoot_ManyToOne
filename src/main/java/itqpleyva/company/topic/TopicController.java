package itqpleyva.company.topic;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService;	
	
	@RequestMapping("/topics")
	public List<Topic> getAllTopics(){	
		return topicService.getAllTopics();
	}
	
	@RequestMapping("/topics/{id}")
	public Optional<Topic> getTopic( @PathVariable String id){	
		return topicService.getTopic(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics")
	public Topic postTopic( @RequestBody Topic topic){
		topicService.postTopic(topic);
		return topic;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
	public List<Topic> postTopic(@PathVariable String id){
		topicService.deleteTopic(id);
		return getAllTopics();
	}
	
	@RequestMapping(method=RequestMethod.PATCH, value="/topics/{id}")
	public Optional<Topic> patchTopic( @RequestBody Map<String, String> field,  @PathVariable String id){
		return topicService.modifyTopicAtributte(id, field);
	}
/*	@RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
	public Topic putTopic( @RequestBody Topic topic,  @PathVariable String id){

		topicService.modifyTopic(topic);
		return topic;
	}*/
}
