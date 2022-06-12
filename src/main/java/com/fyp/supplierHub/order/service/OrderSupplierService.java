package com.fyp.supplierHub.order.service;


import com.fyp.supplierHub.cart.repository.CartProductsRepo;
import com.fyp.supplierHub.cart.service.CartService;
import com.fyp.supplierHub.customer.service.CustomerService;
import com.fyp.supplierHub.order.dtos.OrderSupplierDto;
import com.fyp.supplierHub.order.entity.Order;
import com.fyp.supplierHub.order.repository.OrderRepo;
import com.fyp.supplierHub.order.repository.OrdersRepo;
import com.fyp.supplierHub.product.repository.ProductRepo;
import com.fyp.supplierHub.supplier.entity.Supplier;
import com.fyp.supplierHub.supplier.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderSupplierService {

    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;
    private final OrderRepo orderRepo;
    private final OrdersRepo ordersRepo;
    private final SupplierService supplierService ;


    public List<OrderSupplierDto> getPendingOrders (String username ){
        Supplier existing_supplier =supplierService.LoadAuthenticatedSupplier(username);

        List<Order> orders = orderRepo.getPendingOrdersBysupplier(existing_supplier.getSupplierId());
        TypeToken<List<OrderSupplierDto>> typeToken =new TypeToken<>(){};

        List<OrderSupplierDto> pending_Orders = modelMapper.map(orders, typeToken.getType());

        return pending_Orders;
    }

    public List<OrderSupplierDto> getActiveOrders (String username ){
        Supplier existing_supplier =supplierService.LoadAuthenticatedSupplier(username);

        List<Order> orders = orderRepo.getActiveOrdersBySupplier(existing_supplier.getSupplierId());
        TypeToken<List<OrderSupplierDto>> typeToken =new TypeToken<>(){};

        List<OrderSupplierDto> active_orders = modelMapper.map(orders, typeToken.getType());

        return active_orders;
    }

    public List<OrderSupplierDto> getDeliveredOrders (String username ){
        Supplier existing_supplier =supplierService.LoadAuthenticatedSupplier(username);

        List<Order> orders = orderRepo.getDeliveredOrders(existing_supplier.getSupplierId());
        TypeToken<List<OrderSupplierDto>> typeToken =new TypeToken<>(){};

        List<OrderSupplierDto> delivered_orders = modelMapper.map(orders, typeToken.getType());

        return delivered_orders;
    }


}
