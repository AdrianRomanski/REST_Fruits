package adrianromanski.services;


import adrianromanski.domain.Customer;
import adrianromanski.mapper.CustomerMapper;
import adrianromanski.model.CustomerDTO;
import adrianromanski.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {


    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void getAllCustomers() {
        //Given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customersDTOS = customerService.getAllCustomers();

        assertEquals(3, customersDTOS.size());
    }

    @Test
    public void getCustomerById() {
        // Given
        Customer customer = new Customer();
        String NAME = "Adrian";
        customer.setFirstName(NAME);
        String LAST_NAME = "Romanski";
        customer.setLastName(LAST_NAME);
        customer.setId(1L);


        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer));

        // When
        CustomerDTO customerDTO = customerService.getCustomerByID(1L);

        assertEquals(NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }
}
