package adrianromanski.controllers;

import adrianromanski.model.CategoryDTO;
import adrianromanski.model.CustomerDTO;
import adrianromanski.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    public static final String FIRST_NAME = "Adrian";
    public static final String LAST_NAME = "Romanski";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testListCustomers() throws Exception {
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName(FIRST_NAME);
        customerDTO1.setLastName(LAST_NAME);
        customerDTO1.setId(1L);

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setFirstName("Kinga");
        customerDTO2.setLastName("Patyna");
        customerDTO2.setId(2L);

        List<CustomerDTO> customers = Arrays.asList(customerDTO1, customerDTO2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetById() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(LAST_NAME);
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setId(1L);

        when(customerService.getCustomerByID(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
    }
}
