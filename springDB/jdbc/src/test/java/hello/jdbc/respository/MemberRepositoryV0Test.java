package hello.jdbc.respository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        //save
        Member member = new Member("memberV5", 10000);
        repository.save(member);

        //findById
        Member findMember = repository.findById(member.getMemberId());
//        log.info("findMember = {}", findMember);
        Assertions.assertThat(findMember).isEqualTo(member); //둘이 인스턴스는 다르지만, @Data가 equals랑 hashcode를 만들어줌

        //update
        repository.update(member.getMemberId(), 20000);
        Member updateMember = repository.findById(member.getMemberId());
        Assertions.assertThat(updateMember.getMoney()).isEqualTo(20000);

        //delete...찾으려는 행이 없을 때 확인하는 법!!
        /**
         *  assertThatThrownBy : 해당 예외가 발생해야 검증에 성공
         */
        repository.delete(member.getMemberId());
        Assertions.assertThatThrownBy(()-> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }
}