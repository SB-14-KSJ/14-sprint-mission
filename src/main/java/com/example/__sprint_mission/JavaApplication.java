package com.example.__sprint_mission;

import com.example.__sprint_mission.entity.Channel;
import com.example.__sprint_mission.entity.Message;
import com.example.__sprint_mission.entity.User;
import com.example.__sprint_mission.repository.ChannelRepository;
import com.example.__sprint_mission.repository.MessageRepository;
import com.example.__sprint_mission.repository.UserRepository;
import com.example.__sprint_mission.repository.file.FileChannelRepository;
import com.example.__sprint_mission.repository.file.FileMessageRepository;
import com.example.__sprint_mission.repository.file.FileUserRepository;
import com.example.__sprint_mission.repository.jcf.JCFChannelRepository;
import com.example.__sprint_mission.repository.jcf.JCFMessageRepository;
import com.example.__sprint_mission.repository.jcf.JCFUserRepository;
import com.example.__sprint_mission.service.ChannelService;
import com.example.__sprint_mission.service.MessageService;
import com.example.__sprint_mission.service.UserService;
import com.example.__sprint_mission.service.basic.BasicChannelService;
import com.example.__sprint_mission.service.basic.BasicMessageService;
import com.example.__sprint_mission.service.basic.BasicUserService;


public class JavaApplication {

	static User setupUser(UserService userService) {
		User user = userService.create("woody", "woody@codeit.com");
		return user;
	}

	static Channel setupChannel(ChannelService channelService) {
		Channel channel = channelService.create("공지", "공지 채널입니다.");
		return channel;
	}

	static void messageCreateTest(MessageService messageService, Channel channel, User author) {
		Message message = messageService.create("안녕하세요.", author.getId(), channel.getId());
		System.out.println("메시지 생성 성공! Message ID: " + message.getId());
	}

	public static void main(String[] args) {
		System.out.println("=== 1. JCF 기반 Repository 테스트 ===");
		runTest(new JCFUserRepository(), new JCFChannelRepository(), new JCFMessageRepository());

		System.out.println("\n=== 2. File 기반 Repository 테스트 (데이터 영속화) ===");
		runTest(new FileUserRepository(), new FileChannelRepository(), new FileMessageRepository());
	}

	private static void runTest(UserRepository userRepository,
                                ChannelRepository channelRepository,
                                MessageRepository messageRepository) {
		// 서비스 초기화: Basic*Service 구현체 활용
		UserService userService = new BasicUserService(userRepository);
		ChannelService channelService = new BasicChannelService(channelRepository);
		MessageService messageService = new BasicMessageService(messageRepository, userRepository, channelRepository);

		// 셋업
		User user = setupUser(userService);
		Channel channel = setupChannel(channelService);

		// 테스트
		messageCreateTest(messageService, channel, user);
	}
}