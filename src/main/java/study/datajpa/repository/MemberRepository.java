package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDTO;
import study.datajpa.entity.Member;

import java.util.Collection;
import java.util.List;

@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsername(String username);

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query(
            "select m from Member m" +
                    " where m.username = :username" +
                    " and m.age = :age"
    )
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query(
            "select m.username from Member m"
    )
    List<String> findUsernameList();

    @Query(
            "select new study.datajpa.dto.MemberDTO(m.id, m.username, t.name)" +
                    " from Member m" +
                    " join m.team t"
    )
    List<MemberDTO> findMemberDTO();

    @Query(
            "select m from Member m" +
                    " where m.username in :names"
    )
    List<Member> findByNames(@Param("names") Collection<String> names);


}