package adrianromanski.repositories;

import adrianromanski.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor , Long> {
}
