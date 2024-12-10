package nino.rs.mdsinformaticki.controller;

import nino.rs.mdsinformaticki.respository.StockRepository;
import nino.rs.mdsinformaticki.respository.ValueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PeriodStocks {

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
                "  \"from\": \"2020-06-01\",\n" +
                "  \"to\": \"2020-06-10\",\n" +
                "  \"name\": \"Google\"\n" +
                "}";

        String responseContent = mockMvc.perform(post("http://localhost:8081/stock/info")
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
