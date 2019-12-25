package itqpleyva.company.topic;

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
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
/*	
	private List<Topic> topics = new ArrayList(Arrays.asList(
			 new Topic("spring1", "spring1", "description spring1"),
			 new Topic("spring2", "spring2", "description spring2"),
			 new Topic("spring3", "spring3", "description spring3"),
			 new Topic("spring4", "spring4", "description spring4")
			));
	*/
	public List<Topic> getAllTopics(){
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll()
		.forEach(topics::add);
		return topics;
	}
	public Optional<Topic> getTopic(String id) {
		
		return topicRepository.findById(id);
	}
	
	public void postTopic(Topic topic){
		topicRepository.save(topic);
	}
	
	public void deleteTopic(String topicId){
		topicRepository.deleteById(topicId);
	}
	
	public void modifyTopic(Topic topic){
		topicRepository.save(topic);
	}
	
	public Optional<Topic> modifyTopicAtributte(String id, Map<String, String> field){
	 Optional<Topic> topic =  getTopic(id);
	    field.forEach((k, v) -> {
		   	 if(k =="name") {
				 topic.get().setName(v);
			 }
			 else if(k =="description") {
				 topic.get().setDescription(v);
			 }
	    });
	  topicRepository.save(topic.get());
	  return topic;
	}
}
