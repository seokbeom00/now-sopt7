package org.sopt.spring.member.dto;

import org.sopt.spring.member.domain.Part;

public record MemberCreateDto(
        String name,
        Part part,
        int age
) {
}
