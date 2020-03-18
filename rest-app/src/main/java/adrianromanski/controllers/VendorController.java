package adrianromanski.controllers;

import adrianromanski.model.CustomerListDTO;
import adrianromanski.model.VendorDTO;
import adrianromanski.model.VendorListDTO;
import adrianromanski.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "Vendor API")
@RestController
@RequestMapping("/vendors/")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "Returns list of all Vendors")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors() {
        return new VendorListDTO(vendorService.getAllVendors());
    }

    @ApiOperation(value = "Returns single Vendor based on ID provided by the user")
    @GetMapping("{vendorID}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorByID(@PathVariable String vendorID) {
        return vendorService.getVendorByID(Long.valueOf(vendorID));
    }

    @ApiOperation(value = "Creates a single Vendor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.createNewVendor(vendorDTO);
    }

    @ApiOperation(value = "Update an existing Vendor")
    @PutMapping({"id"})
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.saveVendor(id, vendorDTO);
    }

    @ApiOperation(value = "Update a Vendor Property")
    @PatchMapping({"id"})
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendor(id, vendorDTO);
    }

    @ApiOperation(value = "Delete Vendor based on ID provided by the user")
    @DeleteMapping({"id"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendorByID(id);
    }
}
