package hello.jdbc.respository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

/**
 * JDBC Template 사용...반복되던 코드들 얘가 다 해줌. 동기화까지 다 해줌. 예외변환기까지 해줌 ㄸ
 */
@Slf4j
public class MemberRepositoryV5 implements MemberRepository{

    private final JdbcTemplate template;

    public MemberRepositoryV5(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(member_id, money) values (?,?)"; //참고로 ? 이런 파라미터 바인딩을 써야 sql injection 예방가능함.
        template.update(sql, member.getMemberId(), member.getMoney());

        return member;
    }

    @Override
    public Member findById(String memberId) {
        String sql = "select * from member where member_id = ?";

        return template.queryForObject(sql, memeberRowMapper(), memberId);
    }

    private RowMapper<Member> memeberRowMapper() {
        return (rs, rowNum)->{
             Member member = new Member();
             member.setMemberId(rs.getString("member_id"));
             member.setMoney((rs.getInt("money")));
             return member;
        };
    }


    @Override
    public void update(String memberId, int moeny) {
        String sql = "update member set money=? where member_id=?";

        template.update(sql, moeny, memberId);
    }

    @Override
    public void delete(String memberId) {
        String sql = "delete from member where member_id=?";

        template.update(sql, memberId);
    }

}
