package com.example.__sprint_mission;

import com.example.__sprint_mission.entity.Channel;
import com.example.__sprint_mission.entity.Message;
import com.example.__sprint_mission.entity.User;
import com.example.__sprint_mission.service.ChannelService;
import com.example.__sprint_mission.service.MessageService;
import com.example.__sprint_mission.service.UserService;
import com.example.__sprint_mission.service.jcf.JCFChannelService;
import com.example.__sprint_mission.service.jcf.JCFMessageService;
import com.example.__sprint_mission.service.jcf.JCFUserService;

import java.util.UUID;

public class JavaApplication {

	public static void main(String[] args) {
		System.out.println("=== DisCodeIt 서비스 테스트 시작 ===\n");

		// 1. 서비스 초기화 및 의존성 주입
		UserService userService = new JCFUserService();
		ChannelService channelService = new JCFChannelService();
		MessageService messageService = new JCFMessageService(userService, channelService);

		// 2. [등록]
		System.out.println("--- 1. 데이터 등록 ---");
		User user1 = userService.create("alice", "alice@example.com");
		User user2 = userService.create("bob", "bob@example.com");
		Channel channel1 = channelService.create("일반", "자유롭게 대화하는 채널");

		Message msg1 = messageService.create("안녕하세요!", user1.getId(), channel1.getId());
		Message msg2 = messageService.create("반갑습니다.", user2.getId(), channel1.getId());

		System.out.println("유저 생성 완료: " + user1.getUsername());
		System.out.println("채널 생성 완료: " + channel1.getName());
		System.out.println("메시지 생성 완료: " + msg1.getContent());

		// 3. [조회 - 단건 및 다건]
		System.out.println("\n--- 2. 데이터 조회 ---");
		System.out.println("단건 유저 조회: " + userService.read(user1.getId()).getUsername());
		System.out.println("전체 채널 수: " + channelService.readAll().size());
		System.out.println("전체 메시지 목록:");
		for (Message m : messageService.readAll()) {
			System.out.println("  - [" + m.getId() + "] " + m.getContent());
		}

		// 4. [수정]
		System.out.println("\n--- 3. 데이터 수정 ---");
		userService.update(user1.getId(), "alice_updated", "alice_new@example.com");
		messageService.update(msg1.getId(), "안녕하세요! (수정됨)");

		// 5. [수정된 데이터 조회]
		System.out.println("\n--- 4. 수정된 데이터 확인 ---");
		User updatedUser = userService.read(user1.getId());
		Message updatedMsg = messageService.read(msg1.getId());
		System.out.println("수정된 유저 이름: " + updatedUser.getUsername() + " (수정시각: " + updatedUser.getUpdatedAt() + ")");
		System.out.println("수정된 메시지 내용: " + updatedMsg.getContent() + " (수정시각: " + updatedMsg.getUpdatedAt() + ")");

		// 6. [심화 검증 테스트]
		System.out.println("\n--- 5. [심화] 연관 데이터 검증 테스트 ---");
		try {
			UUID invalidUserId = UUID.randomUUID();
			System.out.println("존재하지 않는 유저 ID로 메시지 작성 시도...");
			messageService.create("실패할 메시지", invalidUserId, channel1.getId());
		} catch (IllegalArgumentException e) {
			System.out.println("검증 성공 (예외 발생): " + e.getMessage());
		}

		// 7. [삭제]
		System.out.println("\n--- 6. 데이터 삭제 ---");
		messageService.delete(msg2.getId());
		System.out.println("메시지2 삭제 완료.");

		// 8. [삭제 여부 확인]
		System.out.println("\n--- 7. 삭제 확인 ---");
		Message deletedMsg = messageService.read(msg2.getId());
		System.out.println("삭제된 메시지 조회 결과: " + (deletedMsg == null ? "null (정상 삭제됨)" : "존재함"));
		System.out.println("남은 메시지 개수: " + messageService.readAll().size());
	}
}