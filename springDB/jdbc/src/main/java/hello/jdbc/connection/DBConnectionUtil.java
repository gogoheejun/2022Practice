package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

/**
 * 참고로 h2디비 실행은 터미널에서 h2/bin이 있는 위치에서 ./h2.sh 를 치면 됨. 근데 브라우저에서 안열리면 브라우저 url입력칸에 ip대신 localhost로 바꾸면됨.
 * jdbc url: jdbc:h2:tcp://localhost/~/test
 */

@Slf4j
public class DBConnectionUtil {

    public static Connection getConnection() {
        try {
            /*
            jdbc가 제공하는 DriveManager을 사용하면 라이브러리에 있는 데잍베이스 드라이버를 찾아서 해당 드라이버가 제공하는 커넥션을 반환함
            참고로 이 반환되는 h2커넥션은 java.sql.Connection 인터페이스를 구현하는 것임.
             */
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("get connection={}, class={}", connection, connection.getClass());
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
