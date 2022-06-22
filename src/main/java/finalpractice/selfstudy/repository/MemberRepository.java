package finalpractice.selfstudy.repository;

import finalpractice.selfstudy.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
