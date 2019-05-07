import com.randomm.dao.UserAccountDAO;
import com.randomm.dao.UserAccountDAOImpl;
import com.randomm.db.PostgreSqlDB;
import com.randomm.domain.UserAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserAccountDaoTest {
    @Mock
    private PostgreSqlDB db;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;

    private UserAccount userAccount;

    @Before
    public void setUp() throws Exception {
        assertNotNull(db);
        when(db.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        userAccount = new UserAccount("test", "surname");
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(1)).thenReturn(userAccount.getUsername());
        when(resultSet.getString(2)).thenReturn(userAccount.getSurname());
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

    }

    @Test(expected=IllegalArgumentException.class)
    public void nullCreateThrowsException() {
        new UserAccountDAOImpl(db).addUserAccount(null);
        verifyZeroInteractions(connection);
    }

    @Test
    public void createPerson() throws SQLException {
        new UserAccountDAOImpl(db).addUserAccount(userAccount);
        verify(preparedStatement).setString(1, userAccount.getUsername());
        verify(preparedStatement).setString(2, userAccount.getSurname());
    }

    @Test
    public void findByUsername(){
        UserAccountDAO userAccountDAO = new UserAccountDAOImpl(db);
        userAccountDAO.addUserAccount(userAccount);
        UserAccount actual = userAccountDAO.findUserAccountByUsername(userAccount.getUsername());
        assertEquals(userAccount, actual);
    }

    @Test
    public void updateSurname() throws SQLException {
        UserAccountDAO userAccountDAO = new UserAccountDAOImpl(db);
        userAccountDAO.addUserAccount(userAccount);
        assertEquals(true,userAccountDAO.updateSurname(userAccount.getUsername(),"tst"));
        verify(preparedStatement).setString(1, "tst");
        verify(preparedStatement).setString(2, userAccount.getUsername());
    }
}
