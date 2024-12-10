package nino.rs.mdsinformaticki.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllStocks() throws Exception {

        mockMvc.perform(get("http://localhost:8081/stock/all"))
                .andExpect(status().isOk()) // Proverava da je status 200 OK
                .andExpect(content().string("Lista svih akcija")); // Proverava odgovor
    }
}
