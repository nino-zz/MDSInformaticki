package nino.rs.mdsinformaticki.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import nino.rs.mdsinformaticki.respository.StockRepository;
import nino.rs.mdsinformaticki.respository.ValueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class DeleteStock {

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

        String responseContent = mockMvc.perform(delete("http://localhost:8081/stock/delete/Google"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // You can then log or assert the response
        System.out.println("Response content: " + responseContent);
    }
}
