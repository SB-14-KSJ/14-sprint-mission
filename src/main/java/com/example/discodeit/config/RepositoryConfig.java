package com.example.discodeit.config;

import com.example.discodeit.repository.ChannelRepository;
import com.example.discodeit.repository.MessageRepository;
import com.example.discodeit.repository.UserRepository;
import com.example.discodeit.repository.file.FileChannelRepository;
import com.example.discodeit.repository.file.FileMessageRepository;
import com.example.discodeit.repository.file.FileUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public UserRepository userRepository() {
        return new FileUserRepository();
    }

    @Bean
    public ChannelRepository channelRepository() {
        return new FileChannelRepository();
    }

    @Bean
    public MessageRepository messageRepository() {
        return new FileMessageRepository();
    }
}