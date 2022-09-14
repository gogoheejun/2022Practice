package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.respository.MemberRepositoryV2;
import hello.jdbc.respository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜잭션 - 트랜잭션 매니저 사용
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3_1 {

//    private final DataSource dataSource; //얘는 JDBC기술 직접 사용하는거라 별로임
    private final PlatformTransactionManager transactionManager;
    private final MemberRepositoryV3 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {

        //트랜잭션 시작
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try{
            //비즈니스 로직 시작
            bizLogic(fromId, toId, money);
            transactionManager.commit(status);//성공 시 커밋
        }catch (Exception e){
            transactionManager.rollback(status);//실패 시 롤백
            throw new IllegalStateException(e);
        }
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        memberRepository.update(fromId, fromMember.getMoney()- money);
        validation(toMember);
        memberRepository.update(toId, fromMember.getMoney()+ money);
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
