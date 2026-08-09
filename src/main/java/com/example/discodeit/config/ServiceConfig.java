// config/ServiceConfig.java
package com.example.discodeit.config;

import com.example.discodeit.repository.ChannelRepository;
import com.example.discodeit.repository.MessageRepository;
import com.example.discodeit.repository.UserRepository;
import com.example.discodeit.service.ChannelService;
import com.example.discodeit.service.MessageService;
import com.example.discodeit.service.UserService;
import com.example.discodeit.service.basic.BasicChannelService;
import com.example.discodeit.service.basic.BasicMessageService;
import com.example.discodeit.service.basic.BasicUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new BasicUserService(userRepository);
    }

    @Bean
    public ChannelService channelService(ChannelRepository channelRepository) {
        return new BasicChannelService(channelRepository);
    }

    @Bean
    public MessageService messageService(
            MessageRepository messageRepository,
            ChannelRepository channelRepository,
            UserRepository userRepository
    ) {
        return new BasicMessageService(messageRepository, channelRepository, userRepository);
    }
}