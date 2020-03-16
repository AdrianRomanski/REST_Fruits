package adrianromanski.bootstrap;

import adrianromanski.domain.Category;
import adrianromanski.domain.Customer;
import adrianromanski.repositories.CategoryRepository;
import adrianromanski.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(nuts);
        categoryRepository.save(exotic);

        Customer adrian = new Customer();
        adrian.setFirstName("Adrian");
        adrian.setLastName("Romanski");

        Customer kinga = new Customer();
        kinga.setFirstName("Kinga");
        kinga.setLastName("Patyna");

        customerRepository.save(adrian);
        customerRepository.save(kinga);


        System.out.println("Data Loaded Categories = " + categoryRepository.count());
        System.out.println("Data Loaded Customers = " + customerRepository.count());

    }
}
