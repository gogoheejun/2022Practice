package hello.jdbc.respository;

import hello.jdbc.domain.Member;
import hello.jdbc.respository.ex.MyDbException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

/**
 * 예외 누수 문제 해결
 * 체크예외를 런타임 예외로 변경 => 덕분에 서비스계층의 순수성(순수자바) 유지가능. 이후에 jdbc를 다른애로 변경하더라도 서비스는 변겅안할 수 있음.
 * MemberRepository 인터페이스 사용
 * throws SQLException 제거
 */
@Slf4j
public class MemberRepositoryV4_1 implements MemberRepository{

    private final DataSource dataSource;

    public MemberRepositoryV4_1(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(member_id, money) values (?,?)"; //참고로 ? 이런 파라미터 바인딩을 써야 sql injection 예방가능함.

        Connection con = null;
        PreparedStatement pstmt = null;


        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());

            pstmt.executeUpdate();// Statement 통해 준비된 sql을 커넥션통해서 디비로 전달
            return member;
        } catch (SQLException e) {
            throw new MyDbException(e);
        }finally {
            close(con, pstmt, null);
        }
    }

    @Override
    public Member findById(String memberId) {
        String sql = "select * from member where member_id = ?";

        //try-catch때매 어쩔수없이 바깥에 선언할 수 밖에ㅠ
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);

            rs = pstmt.executeQuery(); // select는 executeQuery 씀
            if(rs.next()){ //내부에 커서 있음. 한번 움직여야 데이터있는 위치로 감
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            }else {
                throw new NoSuchElementException("member not found memberId = "+memberId);
            }

        } catch (SQLException e) {
            throw new MyDbException(e);
        }finally {
            close(con, pstmt, rs);
        }
    }


    @Override
    public void update(String memberId, int moeny) {
        String sql = "update member set money=? where member_id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, moeny);
            pstmt.setString(2, memberId);

            int resultSize = pstmt.executeUpdate();// Statement 통해 준비된 sql을 커넥션통해서 디비로 전달
            log.info("resultSize={}", resultSize);
        } catch (SQLException e) {
            new MyDbException(e);
        }finally {
            close(con, pstmt, null);
        }
    }

    @Override
    public void delete(String memberId) {
        String sql = "delete from member where member_id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);

            int resultSize = pstmt.executeUpdate();// Statement 통해 준비된 sql을 커넥션통해서 디비로 전달
            log.info("resultSize={}", resultSize);
        } catch (SQLException e) {
            new MyDbException(e);
        }finally {
            close(con, pstmt, null);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs){

        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
//        JdbcUtils.closeConnection(con);
        //주의!  트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 함.
        DataSourceUtils.releaseConnection(con, dataSource);
    }

    private Connection getConnection() throws SQLException {
        //주의! 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 함
        Connection con = DataSourceUtils.getConnection(dataSource);
        log.info("get connection={}, class={}", con, con.getClass());
        return con;
    }

}
