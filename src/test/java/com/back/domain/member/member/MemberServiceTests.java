package com.back.domain.member.member;

import com.back.domain.member.member.dto.Member;
import com.back.domain.member.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
public class MemberServiceTests {
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원 다건 조회")
    void t1() {
        List<Member> members = memberService.findAll();

        assertThat(members).hasSize(2);
    }
}
