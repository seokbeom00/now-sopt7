package org.sopt.spring.blog.repository;

import org.sopt.spring.blog.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
