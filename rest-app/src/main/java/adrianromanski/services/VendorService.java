package adrianromanski.services;

import adrianromanski.domain.Vendor;
import adrianromanski.model.CustomerDTO;
import adrianromanski.model.VendorDTO;

import java.util.List;


public interface VendorService {

    List<VendorDTO> getAllVendors();

    VendorDTO getVendorByID(Long id);

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO saveVendor(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorByID(Long id);
}
