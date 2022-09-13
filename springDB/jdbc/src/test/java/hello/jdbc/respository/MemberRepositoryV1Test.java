package hello.jdbc.respository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.connection.ConnectionConst;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
class MemberRepositoryV1Test {

    MemberRepositoryV1 repository;

    @BeforeEach
    void beforeEach(){
        // 기본 DriverManager - 항상 새로운 커넥션획득
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);

        /*
        커넥션 풀링 :
        repository.save()처럼 connection을 쓰고, close()를 할때에 연결을 끊는 것이 아니라
        다시 풀에 반환하기 때문에 해당 테스트에서는 conn0 커넥션만 씀.
         */
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPoolName(PASSWORD);
        repository = new MemberRepositoryV1(dataSource);
    }

    @Test
    void crud() throws SQLException, InterruptedException {
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

        Thread.sleep(1000);//단지 로그보려고 그럼..로그 출력전 테스트 끝나버려서ㅠ
    }
}