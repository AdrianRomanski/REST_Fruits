package adrianromanski.controllers;

import adrianromanski.model.VendorDTO;
import adrianromanski.services.VendorService;
import org.junit.After;
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

import static adrianromanski.controllers.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest {

    public static final String WALTER = "Walter";
    public static final String JESSIE = "Jessie";

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getAllVendors() throws Exception {
        VendorDTO walter = new VendorDTO();
        walter.setName(WALTER);
        walter.setId(1L);

        VendorDTO jessie = new VendorDTO();
        jessie.setName(JESSIE);
        jessie.setId(2L);

        List<VendorDTO> vendors = Arrays.asList(walter,jessie);

        when(vendorService.getAllVendors()).thenReturn(vendors);

        mockMvc.perform(get("/vendors/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void testGetByID() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(WALTER);
        vendorDTO.setId(1L);

        when(vendorService.getVendorByID(anyLong())).thenReturn(vendorDTO);

        mockMvc.perform(get("/vendors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(WALTER)));
    }

    @Test
    public void createNewVendor() throws Exception {
        //given
        VendorDTO vendor = new VendorDTO();
        vendor.setName(WALTER);

        VendorDTO returnVendor = new VendorDTO();
        returnVendor.setName(vendor.getName());
        returnVendor.setVendorUrl("/vendors/1");

        when(vendorService.createNewVendor(vendor)).thenReturn(returnVendor);

        mockMvc.perform(post("/vendors/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(WALTER)))
                .andExpect(jsonPath("$.vendorUrl", equalTo("/vendors/1")));
    }



}