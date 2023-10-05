import dao.DBFunctions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;


public class DBFunctionsTest {

    @Test
    public void testConnect() throws SQLException, ClassNotFoundException {
        Connection connection = DBFunctions.connect();
        assertThat(connection, instanceOf(Connection.class));
    }

}
