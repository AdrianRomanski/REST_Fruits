package adrianromanski.controllers;

import adrianromanski.model.CustomerDTO;
import adrianromanski.model.CustomerListDTO;
import adrianromanski.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.saveCustomerByDTO(id, customerDTO),
                HttpStatus.OK);
    }

    @PatchMapping({"{id}"})
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.patchCustomer(id, customerDTO),
                HttpStatus.OK);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {

        customerService.deleteCustomerById(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
