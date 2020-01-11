package co.hadwen.web.auth;

import static org.mockito.Mockito.when;

import co.hadwen.user.UserClient;
import co.hadwen.user.entity.UserAccount;
import co.hadwen.web.auth.dto.LoginDTO;
import co.hadwen.web.session.WebSessionClient;
import co.hadwen.web.session.WebSessionToken;
import co.hadwen.web.util.RequestBodyUtils;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;
@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {
    private static final String SESSION_ID = "test-session-id";
    private static final String USER_ID = "test-user-id";
    private static final String EMAIL = "test@hadwen.io";
    private static final String PASSWORD = "abc123";

    @MockBean
    UserClient userClient;

    @MockBean
    WebSessionClient webSessionClient;

    @Mock
    UserAccount account;

    @Mock
    WebSessionToken webSession;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);

        when(account.doesPasswordMatch(PASSWORD)).thenReturn(true);
        when(account.getUserId()).thenReturn(USER_ID);
        when(userClient.getByEmail(EMAIL)).thenReturn(
                Optional.of(account));

        when(webSession.getSessionToken()).thenReturn(SESSION_ID);
        when(webSessionClient.create(USER_ID)).thenReturn(webSession);
    }

    @DisplayName("Valid login should produce a session token")
    @Test
    public void valid() throws Exception {
        LoginDTO request = LoginDTO.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        mvc.perform(MockMvcRequestBuilders.post( "/v1/login")
                .content(RequestBodyUtils.asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
                // todo: figure out tests
                //.andExpect(status().isCreated());
    }
}
