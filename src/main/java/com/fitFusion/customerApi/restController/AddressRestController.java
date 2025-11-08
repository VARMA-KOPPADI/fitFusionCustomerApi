package com.fitFusion.customerApi.restController;

import com.fitFusion.customerApi.apiResponse.ApiResponse;
import com.fitFusion.customerApi.dto.ShippingAddressDto;
import com.fitFusion.customerApi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 78488200457 accenture Hr  from zoobdia

@RestController
public class AddressRestController {
    @Autowired
    private AddressService addressService;

    @PostMapping("address/{customerId}")
    public ResponseEntity<ApiResponse<ShippingAddressDto>> saveAddress(@RequestBody ShippingAddressDto addressDto, @PathVariable Integer customerId){
        ShippingAddressDto shippingAddressDto = addressService.saveAddress(addressDto, customerId);
        ApiResponse<ShippingAddressDto> response = new ApiResponse<>();
        if(shippingAddressDto != null){
            response.setStatus(200);
            response.setData(shippingAddressDto);
            response.setMessage("Address Saved");
            return new ResponseEntity<ApiResponse<ShippingAddressDto>>(response, HttpStatus.CREATED);
        }
        response.setStatus(500);
        response.setData(null);
        response.setMessage("Address Save Failed");
        return new ResponseEntity<ApiResponse<ShippingAddressDto>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/address/{addressId}")
    public ResponseEntity<ApiResponse<ShippingAddressDto>> getAddress(@PathVariable Integer addressId)
    {
        ApiResponse<ShippingAddressDto> response =new ApiResponse<>();
        ShippingAddressDto address = addressService.getAddress(addressId);
        if(address != null){
            response.setStatus(200);
            response.setData(address);
            response.setMessage("Address Fetched Successfully");
            return new ResponseEntity<ApiResponse<ShippingAddressDto>>(response,HttpStatus.OK);
        }
        response.setStatus(500);
        response.setData(null);
        response.setMessage("Address Fetching Failed");
        return new ResponseEntity<ApiResponse<ShippingAddressDto>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/address/{customerId}")
    public ResponseEntity<ApiResponse<ShippingAddressDto>> updateAddress(@RequestBody ShippingAddressDto addressDto, @PathVariable Integer customerId){
        ApiResponse<ShippingAddressDto> response =new ApiResponse<>();
        ShippingAddressDto shippingAddressDto = addressService.saveAddress(addressDto, customerId);
        if(shippingAddressDto != null){
            response.setStatus(200);
            response.setData(shippingAddressDto);
            response.setMessage("Address Fetched Successfully");
            return new ResponseEntity<ApiResponse<ShippingAddressDto>>(response,HttpStatus.OK);
        }
        response.setStatus(500);
        response.setData(null);
        response.setMessage("Address Fetching Failed");
        return new ResponseEntity<ApiResponse<ShippingAddressDto>>(response,HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @GetMapping("/allAddresses/{customerId}")
    public ResponseEntity<ApiResponse<List<ShippingAddressDto>>> getAllAddresses(@PathVariable Integer customerId){
        ApiResponse<List<ShippingAddressDto>> response =new ApiResponse<>();
        List<ShippingAddressDto> customerAddresses = addressService.getCustomerAddresses(customerId);
        if(customerAddresses != null){
            response.setStatus(200);
            response.setData(customerAddresses);
            response.setMessage("fetched all customer address");
            return new ResponseEntity<ApiResponse<List<ShippingAddressDto>>>(response,HttpStatus.OK);
        }
        response.setStatus(500);
        response.setData(null);
        response.setMessage("Getting addresses failed with Id : "+ customerId);
        return new ResponseEntity<ApiResponse<List<ShippingAddressDto>>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/address/{addressId}")
    public ResponseEntity<ApiResponse<String>> deleteAddress(@PathVariable Integer addressId){
        ApiResponse<String> response =new ApiResponse<>();
        boolean b = addressService.deleteAddress(addressId);
        if(b){
            response.setStatus(200);
            response.setMessage("address successfully deleted");
            return new ResponseEntity<ApiResponse<String>>(response,HttpStatus.OK);
        }
        response.setStatus(500);
        response.setMessage("deleting the address failed");
        return new ResponseEntity<ApiResponse<String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
