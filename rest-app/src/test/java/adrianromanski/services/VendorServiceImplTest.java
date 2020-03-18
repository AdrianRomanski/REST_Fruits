package adrianromanski.services;


import adrianromanski.domain.Vendor;
import adrianromanski.mapper.VendorMapper;
import adrianromanski.model.VendorDTO;
import adrianromanski.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class VendorServiceImplTest {

    private VendorService vendorService;

    @Mock
    private VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    public void getAllVendors() {
        //Given
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);
        // When
        List<VendorDTO> vendorsDTO = vendorService.getAllVendors();

        assertEquals(3, vendorsDTO.size());
    }

    @Test
    public void getVendorByID() {
        //Given
        Vendor vendor = new Vendor();
        String name = "Walter";
        vendor.setName(name);
        vendor.setId(1L);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        // When
        VendorDTO vendorDto = vendorService.getVendorByID(1L);

        assertEquals(name, vendorDto.getName());
    }

    @Test
    public void createNewVendor() {
    }

    @Test
    public void saveVendor() {
    }

    @Test
    public void patchCustomer() {
    }

    @Test
    public void deleteVendorByID() {
        Long id = 1L;

        vendorRepository.deleteById(id);

        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}