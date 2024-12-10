package nino.rs.mdsinformaticki.controller;


import nino.rs.mdsinformaticki.respository.StockRepository;
import nino.rs.mdsinformaticki.respository.ValueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostStock {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StockController stockController;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ValueRepository valueRepository;

    @Test
    public void test() throws Exception {

        String jsonBody = "{\n" +
                "  \"name\": \"Example Company\",\n" +
                "  \"mark\": \"EXC\",\n" +
                "  \"foundingDate\": \"2020-01-01\",\n" +
                "  \"values\": [\n" +
                "    {\n" +
                "      \"date\": \"2020-01-02\",\n" +
                "      \"open\": 100.5,\n" +
                "      \"high\": 105.0,\n" +
                "      \"low\": 98.0,\n" +
                "      \"close\": 102.0,\n" +
                "      \"adjClose\": 102.0,\n" +
                "      \"volume\": 150000\n" +
                "    },\n" +
                "    {\n" +
                "      \"date\": \"2020-01-03\",\n" +
                "      \"open\": 102.0,\n" +
                "      \"high\": 108.0,\n" +
                "      \"low\": 101.0,\n" +
                "      \"close\": 107.0,\n" +
                "      \"adjClose\": 107.0,\n" +
                "      \"volume\": 200000\n" +
                "    }\n" +
                "  ]\n" +
                "}";


        String responseContent = mockMvc.perform(post("http://localhost:8081/stock/add")
                        .contentType("application/json")
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("Response content: " + responseContent);

    }
}
