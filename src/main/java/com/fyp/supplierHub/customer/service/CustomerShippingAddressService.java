package com.fyp.supplierHub.customer.service;

import com.fyp.supplierHub.customer.entity.Customer;
import com.fyp.supplierHub.customer.entity.ShippingAddress;
import com.fyp.supplierHub.customer.models.ShippingAddressDto;
import com.fyp.supplierHub.customer.repository.ShippingAddressRepo;
import com.fyp.supplierHub.exceptions.Exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerShippingAddressService {
    private final ModelMapper modelMapper ;

    private final ShippingAddressRepo shippingAddressRepo ;

    private final CustomerService customerService ;

    public CustomerShippingAddressService(ModelMapper modelMapper, ShippingAddressRepo shippingAddressRepo ,CustomerService customerService) {
        this.modelMapper = modelMapper;
        this.shippingAddressRepo = shippingAddressRepo;
        this.customerService = customerService ;
    }

    public List<ShippingAddressDto> findAll(String username)
    {
        Customer Existing_Customer = customerService.getAuthenticatedCustomer(username);
        List<ShippingAddress> shippingAddresses = shippingAddressRepo.getAllByAuthenticatedCustomer(Existing_Customer.getCustomerName());
        TypeToken<List<ShippingAddressDto>> typeToken = new TypeToken<>(){};

        List<ShippingAddressDto> shippingAddressDtos = modelMapper.map(shippingAddresses,typeToken.getType());

        return shippingAddressDtos ;
    }

    public ShippingAddressDto createOrUpdate (String username , ShippingAddressDto shippingAddressDto){
        Customer Existing_Customer = customerService.getAuthenticatedCustomer(username);

        if(shippingAddressDto.getId()>0){
            ShippingAddress Existing_Shipping_Address =
                    shippingAddressRepo.findById(shippingAddressDto.getId())
                            .orElseThrow(()-> new NotFoundException("Shipping Address That you have Sent is Not found Please Create First And then Try to Edit ","/api/v1.0/customer-shipping-address/create-or-update"));

            shippingAddressDto.setId(Existing_Shipping_Address.getId());
            modelMapper.map(shippingAddressDto,Existing_Shipping_Address);

            Existing_Shipping_Address = shippingAddressRepo.save(Existing_Shipping_Address);

            return shippingAddressDto;
        }
        ShippingAddress newShippingAddress = new ShippingAddress() ;
        newShippingAddress.setCustomer(Existing_Customer);
        modelMapper.map(shippingAddressDto,newShippingAddress);

        newShippingAddress = shippingAddressRepo.save(newShippingAddress);
        shippingAddressDto.setId(newShippingAddress.getId());

        return shippingAddressDto ;
    }
}
