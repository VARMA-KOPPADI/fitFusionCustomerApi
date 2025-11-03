package com.fitFusion.customerApi.serviceImpl;

import com.fitFusion.customerApi.dto.CustomerDto;
import com.fitFusion.customerApi.dto.PasswordUpdateDto;
import com.fitFusion.customerApi.entity.CustomerEntity;
import com.fitFusion.customerApi.mapper.CustomerMapper;
import com.fitFusion.customerApi.repository.CustomerRepo;
import com.fitFusion.customerApi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private EmailService emailService;

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {

        CustomerEntity customerEntity = CustomerMapper.convertToEntity(customerDto);
        String randomPwd = this.randomPasswordGenerator(6);
        customerEntity.setPassword(randomPwd);
        customerEntity.setPwdUpdated("NO");

        CustomerEntity save = customerRepo.save(customerEntity);

        String subject = "your account is created";
        String body = "your temporary password is " + randomPwd;
        String to = customerDto.getEmail();

        boolean email = emailService.sendEmail(subject, body, to);
        if (email) {
            return CustomerMapper.convertToDto(save);
        }
        return null;
    }

    @Override
    public CustomerDto logIn(String email, String pwd) {
        CustomerEntity entity = customerRepo.findByEmailAndPassword(email, pwd);
        if (entity != null && !entity.getPwdUpdated().equals("NO")) {
            return CustomerMapper.convertToDto(entity);
        }
        return null;
    }

    @Override
    public boolean resetPwd(PasswordUpdateDto pwdUpdateDto) {
        CustomerEntity customerEntity = customerRepo.findByEmail(pwdUpdateDto.getEmail());
        if (customerEntity != null) {
            customerEntity.setPassword(pwdUpdateDto.getConformPassword());
            customerEntity.setPwdUpdated("YES");
            customerRepo.save(customerEntity);
            return true;
        }

        return false;
    }

    @Override
    public CustomerDto getCustomerByEmail(String email) {
        CustomerEntity customerEntity = customerRepo.findByEmail(email);
        if (customerEntity != null) {
            return CustomerMapper.convertToDto(customerEntity);
        }
        return null;
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        CustomerEntity entity = CustomerMapper.convertToEntity(customerDto);
        CustomerEntity save = customerRepo.save(entity);
        return CustomerMapper.convertToDto(save);
    }

    public String randomPasswordGenerator(int num) {
        Random random = new Random();
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*?abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder builder = new StringBuilder(num);
        for (int i = 0; i < num; i++) {
            int i1 = random.nextInt(s.length());
            builder.append(s.charAt(i1));
        }

        return builder.toString();
    }
}