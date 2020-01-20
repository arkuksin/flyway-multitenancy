package de.kuksin.multitenant;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.kuksin.multitenant.entities.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCarsWithVW() throws Exception {
        // given
        String tenant = "vw";

        String url = "/cars";
        Car car = Car.builder()
                .name("Tiguan")
                .color("Black")
                .build();

        // when
        mockMvc.perform(get(url)
                .header("X-Tenant", tenant))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        mockMvc.perform(post(url)
                .header("X-Tenant", tenant)
                .content(objectMapper.writeValueAsString(car))
                .contentType(MediaType.APPLICATION_JSON_VALUE))

                // then
                .andExpect(status().isCreated());

        mockMvc.perform(get(url)
                .header("X-Tenant", tenant))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Tiguan")))
                .andExpect(jsonPath("$[0].color", is("Black")));
    }

    @Test
    void testCarsWithBMW() throws Exception {
        // given
        String tenant = "bmw";

        String url = "/cars";
        Car car = Car.builder()
                .name("X5")
                .color("Orange")
                .build();

        // when

        mockMvc.perform(get(url)
                .header("X-Tenant", tenant))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        mockMvc.perform(post(url)
                .header("X-Tenant", tenant)
                .content(objectMapper.writeValueAsString(car))
                .contentType(MediaType.APPLICATION_JSON_VALUE))

                // then
                .andExpect(status().isCreated());

        mockMvc.perform(get(url)
                .header("X-Tenant", tenant))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("X5")))
                .andExpect(jsonPath("$[0].color", is("Orange")));
    }
}
