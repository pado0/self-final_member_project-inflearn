package finalpractice.selfstudy.repository;

import finalpractice.selfstudy.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByName(String name);

}
