package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.respository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜잭션 - 파라미터에 커넥션을 넣는 방법(올드함)
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {

        Connection con = dataSource.getConnection();
        try{
            con.setAutoCommit(false);//트랜잭션 시작
            //비즈니스 로직 시작
            bizLogic(con, fromId, toId, money);

            con.commit(); //성공 시 커밋
        }catch (Exception e){
            con.rollback();//실패 시 롤백
            throw new IllegalStateException(e);
        }finally {
            release(con);
        }


    }

    private void bizLogic(Connection con, String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(con, fromId);
        Member toMember = memberRepository.findById(con, toId);

        memberRepository.update(con, fromId, fromMember.getMoney()- money);
        validation(toMember);
        memberRepository.update(con, toId, fromMember.getMoney()+ money);
    }

    private void release(Connection con) {
        if( con != null){
            try{
                con.setAutoCommit(true);// true가 기본값이기 때문. 걍 커넥션풀로 돌려버리면 다른 세션이 와서 그 커넥션 쓰면 고대로 false상태임
                con.close();
            }catch (Exception e){
                log.info("error", e);
            }
        }
    }

    private void validation(Member toMember) {
        if(toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("이체중 예외 발생!");
        }
    }
}
