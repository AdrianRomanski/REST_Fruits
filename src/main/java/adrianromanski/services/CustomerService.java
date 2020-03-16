package adrianromanski.services;

import adrianromanski.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerByID(Long id);

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
}
