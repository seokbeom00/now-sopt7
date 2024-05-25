package org.sopt.spring.member.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.spring.common.auth.UserAuthentication;
import org.sopt.spring.common.exception.ErrorMessage;
import org.sopt.spring.common.jwt.JwtTokenProvider;
import org.sopt.spring.member.domain.Member;
import org.sopt.spring.common.exception.NotFoundException;
import org.sopt.spring.member.repository.MemberRepository;
import org.sopt.spring.member.dto.MemberCreateDto;
import org.sopt.spring.member.dto.MemberFindDto;
import org.sopt.spring.member.service.dto.UserJoinResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserJoinResponse createMember(
            MemberCreateDto memberCreate
    ) {
        Member member = memberRepository.save(
                Member.create(memberCreate.name(), memberCreate.part(), memberCreate.age())
        );
        Long memberId = member.getId();
        String accessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(memberId)
        );
        return UserJoinResponse.of(accessToken, memberId.toString());
    }

    public MemberFindDto findMemberById(Long memberId) {
        return MemberFindDto.of(memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다.")
        ));
    }

    @Transactional
    public void deleteMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다."));
        memberRepository.delete(member);
    }

    public List<MemberFindDto> findAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(MemberFindDto::of)
                .collect(Collectors.toList());
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND)
        );
    }
}
