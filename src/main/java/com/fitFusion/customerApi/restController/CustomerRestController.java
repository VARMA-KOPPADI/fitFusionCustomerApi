package com.fitFusion.customerApi.restController;
import com.fitFusion.customerApi.apiResponse.ApiResponse;
import com.fitFusion.customerApi.dto.CustomerDto;
import com.fitFusion.customerApi.dto.PasswordUpdateDto;
import com.fitFusion.customerApi.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/saveCustomer")
    public ResponseEntity<ApiResponse<CustomerDto>> saveCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto saveCustomer = customerService.saveCustomer(customerDto);
        ApiResponse<CustomerDto> apiResponse = new ApiResponse<CustomerDto>();
        if (saveCustomer != null) {
            apiResponse.setStatus(200);
            apiResponse.setData(saveCustomer);
            apiResponse.setMessage("Customer Saved");
            return new ResponseEntity<ApiResponse<CustomerDto>>(apiResponse, HttpStatus.CREATED);
        }
        apiResponse.setStatus(500);
        apiResponse.setMessage("Customer not saved");
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/login")
    public ResponseEntity<ApiResponse<CustomerDto>> login(@RequestBody CustomerDto cDto) {
        CustomerDto customerDto = customerService.logIn(cDto.getEmail(), cDto.getPwdUpdated());
        ApiResponse<CustomerDto> response = new ApiResponse<>();
        if (customerDto != null) {
            response.setStatus(200);
            response.setData(customerDto);
            response.setMessage("user login success");
            return new ResponseEntity<ApiResponse<CustomerDto>>(response, HttpStatus.OK);
        }
        response.setStatus(500);
        response.setData(null);
        response.setMessage("user login success");
        return new ResponseEntity<ApiResponse<CustomerDto>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<ApiResponse<String>> resetPassword(@RequestBody PasswordUpdateDto pDto) {
        boolean resetPwd = customerService.resetPwd(pDto);
        ApiResponse<String> response = new ApiResponse<>();
        if (resetPwd) {
            response.setStatus(200);
            response.setMessage("user password reset success");
            return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.OK);
        }
        response.setStatus(500);
        response.setData(null);
        response.setMessage("password reset failed");
        return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/Customer/{email}")
    public ResponseEntity<ApiResponse<CustomerDto>> resetPassword(@PathVariable String email) {
        CustomerDto customerByEmail = customerService.getCustomerByEmail(email);
        ApiResponse<CustomerDto> response = new ApiResponse<>();
        if (customerByEmail != null) {
            response.setStatus(200);
            response.setData(customerByEmail);
            response.setMessage("retrieving the  user details successfully");
            return new ResponseEntity<ApiResponse<CustomerDto>>(response, HttpStatus.OK);
        }
        response.setStatus(500);
        response.setData(null);
        response.setMessage("password reset failed");
        return new ResponseEntity<ApiResponse<CustomerDto>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/updateCustomer")
    public ResponseEntity<ApiResponse<CustomerDto>> updateCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto customer = customerService.updateCustomer(customerDto);
        ApiResponse<CustomerDto> response = new ApiResponse<>();
        if (customer != null) {
            response.setStatus(200);
            response.setData(customer);
            response.setMessage("customer updated successfully");
            return new ResponseEntity<ApiResponse<CustomerDto>>(response, HttpStatus.OK);
        }
        response.setStatus(500);
        response.setData(null);
        response.setMessage("updating customer failed");
        return new ResponseEntity<ApiResponse<CustomerDto>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}