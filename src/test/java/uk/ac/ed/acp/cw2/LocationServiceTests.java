package uk.ac.ed.acp.cw2;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationServiceTests {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void distanceToValidBody() throws Exception {

        String body = """
                {
                    "position1": {
                        "lng": -3.192473,
                        "lat": 55.946233
                    },
                     "position2": {
                        "lng": -3.192473,
                        "lat": 55.942617
                    }
                }
                """;
        mockMvc.perform(post("http://localhost:8080/api/v1/distanceTo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0.003616000000000952")));
    }
}
