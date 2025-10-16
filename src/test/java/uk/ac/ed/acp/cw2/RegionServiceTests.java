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
public class RegionServiceTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void isInRegionValidBodyNotInRegion() throws Exception {
        String body = """
                {
                "position": {
                "lng": 1.234,
                "lat": 1.222
                },
                "region": {
                "name": "central",
                "vertices": [
                {
                "lng": -3.192473,
                "lat": 55.946233
                },
                {
                "lng": -3.192473,
                "lat": 55.942617
                },
                {
                "lng": -3.184319,
                "lat": 55.942617
                },
                {
                "lng": -3.184319,
                "lat": 55.946233
                },
                {
                "lng": -3.192473,
                "lat": 55.946233
                }
                ]
                }
                }
                """;

        mockMvc.perform(post("http://localhost:8080/api/v1/isInRegion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                        .andExpect(status().is(200))
                        .andExpect(content().string(containsString("false")));
    }

    @Test
    public void isInRegionValidBodyInRegion() throws Exception {
        String body = """
                {
                "position": {
                "lng": -3.186,
                "lat": 55.944
                },
                "region": {
                "name": "central",
                "vertices": [
                {
                "lng": -3.192473,
                "lat": 55.946233
                },
                {
                "lng": -3.192473,
                "lat": 55.942617
                },
                {
                "lng": -3.184319,
                "lat": 55.942617
                },
                {
                "lng": -3.184319,
                "lat": 55.946233
                },
                {
                "lng": -3.192473,
                "lat": 55.946233
                }
                ]
                }
                }
                """;

        mockMvc.perform(post("http://localhost:8080/api/v1/isInRegion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().is(200))
                .andExpect(content().string(containsString("true")));
    }

    @Test
    public void isInRegionValidBodyOnRegionBoundary() throws Exception {
        String body = """
                {
                "position": {
                "lng": -2,
                "lat": 3
                },
                "region": {
                "name": "central",
                "vertices": [
                {
                "lng": 3,
                "lat": 3
                },
                {
                "lng": 3,
                "lat": -3
                },
                {
                "lng": -3,
                "lat": -3
                },
                {
                "lng": -3,
                "lat": 3
                },
                {
                "lng": 3,
                "lat": 3
                }
                ]
                }
                }
                """;

        mockMvc.perform(post("http://localhost:8080/api/v1/isInRegion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().is(200))
                .andExpect(content().string(containsString("true")));
    }

    @Test
    public void isInRegionInValidBodyPosition() throws Exception {
        String body = """
                {
                "p": {
                "lng": -2,
                "lat": 3
                },
                "region": {
                "name": "central",
                "vertices": [
                {
                "lng": 3,
                "lat": 3
                },
                {
                "lng": 3,
                "lat": -3
                },
                {
                "lng": -3,
                "lat": -3
                },
                {
                "lng": -3,
                "lat": 3
                },
                {
                "lng": 3,
                "lat": 3
                }
                ]
                }
                }
                """;

        mockMvc.perform(post("http://localhost:8080/api/v1/isInRegion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().is(400));
    }

    @Test
    public void isInRegionInvalidBodyRegion() throws Exception {
        String body = """
                {
                "position": {
                "lng": -2,
                "lat": 3
                },
                "r": {
                "name": "central",
                "vertices": [
                {
                "lng": 3,
                "lat": 3
                },
                {
                "lng": 3,
                "lat": -3
                },
                {
                "lng": -3,
                "lat": -3
                },
                {
                "lng": -3,
                "lat": 3
                },
                {
                "lng": 3,
                "lat": 3
                }
                ]
                }
                }
                """;

        mockMvc.perform(post("http://localhost:8080/api/v1/isInRegion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().is(400));
    }

    @Test
    public void isInRegionInvalidBodyVertices() throws Exception {
        String body = """
                {
                "position": {
                "lng": -2,
                "lat": 3
                },
                "region": {
                "name": "central",
                "v": [
                {
                "lng": 3,
                "lat": 3
                },
                {
                "lng": 3,
                "lat": -3
                },
                {
                "lng": -3,
                "lat": -3
                },
                {
                "lng": -3,
                "lat": 3
                },
                {
                "lng": 3,
                "lat": 3
                }
                ]
                }
                }
                """;

        mockMvc.perform(post("http://localhost:8080/api/v1/isInRegion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().is(400));
    }

    @Test
    public void isInRegionInvalidBodyVertex() throws Exception {
        String body = """
                {
                "position": {
                "lng": -2,
                "lat": 3
                },
                "region": {
                "name": "central",
                "vertices": [
                {
                "l": 3,
                "lat": 3
                },
                {
                "lng": 3,
                "lat": -3
                },
                {
                "lng": -3,
                "lat": -3
                },
                {
                "lng": -3,
                "lat": 3
                },
                {
                "lng": 3,
                "lat": 3
                }
                ]
                }
                }
                """;

        mockMvc.perform(post("http://localhost:8080/api/v1/isInRegion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().is(400));
    }
}
