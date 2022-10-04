package com.oceans.koyblogapi.service;

import com.oceans.koyblogapi.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {
    MemberService memberService = new MemberService();

    @Test
    void normalSignUp() {
        //give
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(member).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void duplicateSignUp() {
        //give
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName(member1.getName());

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException err) {
            System.out.println(err.getMessage());
            Assertions.assertThat(err.getMessage()).isEqualTo("already_exist_member");
        }

        //then
        Member findMember = memberService.findOne(member1).get();
        Assertions.assertThat(member2.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void findMembers() {

    }


}
