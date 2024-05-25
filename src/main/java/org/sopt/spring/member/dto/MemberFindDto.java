package org.sopt.spring.member.dto;

import org.sopt.spring.member.domain.Member;
import org.sopt.spring.member.domain.Part;

public record MemberFindDto(
        String name,
        Part part,
        int age
) {
    public static MemberFindDto of(Member member) {
        return new MemberFindDto(member.getName(), member.getPart(), member.getAge());
    }
}
