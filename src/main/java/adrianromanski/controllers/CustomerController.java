package adrianromanski.controllers;

import adrianromanski.model.CategoryListDTO;
import adrianromanski.model.CustomerDTO;
import adrianromanski.model.CustomerListDTO;
import adrianromanski.repositories.CustomerRepository;
import adrianromanski.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers() {
        return new ResponseEntity<>(new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("{customerID}")
    public ResponseEntity<CustomerDTO> getCustomerByID(@PathVariable String customerID) {
        return new ResponseEntity<>(customerService.getCustomerByID(Long.valueOf(customerID)), HttpStatus.OK);
    }
}
