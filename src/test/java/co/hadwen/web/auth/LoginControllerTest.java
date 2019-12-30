package co.hadwen.web.auth;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import co.hadwen.web.auth.dto.LoginDTO;
import co.hadwen.web.util.RequestBodyUtils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
    private static final String EMAIL = "test@hadwen.io";
    private static final String PASSWORD = "abc123";

    @Autowired
    private MockMvc mvc;

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
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
