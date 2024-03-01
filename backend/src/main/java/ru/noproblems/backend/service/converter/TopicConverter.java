package ru.noproblems.backend.service.converter;

import org.springframework.stereotype.Component;

import ru.noproblems.backend.data.Topic;
import ru.noproblems.backend.service.dto.TopicDto;

@Component
public class TopicConverter {
    public TopicDto toDto(Topic topic) {
        if (topic == null) {
            return null;
        }
        TopicDto topicDto = new TopicDto();
        topicDto.setId(topic.getId());
        topicDto.setName(topic.getName());
        return topicDto;
    }

    public Topic toEntity(TopicDto topicDto) {
        if (topicDto == null) {
            return null;
        }
        Topic topic = new Topic();
        topic.setId(topicDto.getId());
        topic.setName(topicDto.getName());
        return topic;
    }
}
