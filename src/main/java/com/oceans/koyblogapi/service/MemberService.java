package com.oceans.koyblogapi.service;

import com.oceans.koyblogapi.domain.Member;
import com.oceans.koyblogapi.repository.MemberRepository;
import com.oceans.koyblogapi.repository.MemoryMemberRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();


    /*회원가입*/
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원은 회원가입이 되지 않는다.
        Optional<Member> result = memberRepository.findByName(member.getName());
        // validateDuplicateMember(member);
        result.ifPresent((m) -> {
            throw new IllegalStateException("already_exist_member");
        });

        memberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent((m) -> {
                throw new IllegalStateException("already_exist_member");
            });
    }

    /*전체 회원 조회*/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Member member) {
        return memberRepository.findById(member.getId());
    }
}
