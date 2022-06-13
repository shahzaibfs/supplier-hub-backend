package com.fyp.supplierHub.order.service;

import com.fyp.supplierHub.cart.entity.Cart;
import com.fyp.supplierHub.cart.entity.CartProducts;
import com.fyp.supplierHub.cart.repository.CartProductsRepo;
import com.fyp.supplierHub.cart.repository.CartRepo;
import com.fyp.supplierHub.cart.service.CartService;
import com.fyp.supplierHub.customer.entity.Customer;
import com.fyp.supplierHub.customer.entity.ShippingAddress;
import com.fyp.supplierHub.customer.models.ShippingAddressDto;
import com.fyp.supplierHub.customer.service.CustomerService;
import com.fyp.supplierHub.order.dtos.*;
import com.fyp.supplierHub.order.entity.Order;
import com.fyp.supplierHub.order.entity.Orders;
import com.fyp.supplierHub.order.repository.OrderRepo;
import com.fyp.supplierHub.order.repository.OrdersRepo;
import com.fyp.supplierHub.product.enitity.Product;
import com.fyp.supplierHub.product.repository.ProductRepo;
import com.fyp.supplierHub.supplier.entity.Supplier;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;
    private final CustomerService customerService;
    private final CartService cartService;
    private final OrderRepo orderRepo;
    private final OrdersRepo ordersRepo;
    private final CartProductsRepo cartProductsRepo ;
    private double  totalPrice = 0;

    // Todo : orders Create
    // Todo : order create for every product

    private Orders createOrders (Customer existing_customer, Integer customer_shipping_address_id){

        ShippingAddress existing_shipping_address = new ShippingAddress();
        existing_shipping_address.setId(customer_shipping_address_id);

        Orders new_orders = Orders.builder()
                .dateOfCreation(LocalDate.now())
                .customer(existing_customer)
                .shippingAddress(existing_shipping_address)
                .status("pending")
                .totalPrice(this.totalPrice)
                .build();

        new_orders =  ordersRepo.save(new_orders);

        return new_orders;
    }

    private PublicProductDetails findSupplier(Product existing_product){
        PublicProductDetails supplier_details = new PublicProductDetails();
        modelMapper.map(existing_product,supplier_details);
        return  supplier_details ;
    }

    private Double calcPrice (Product existing_product , Integer existing_quantity){
        long total =  existing_product.getProductPrice() *  existing_quantity;

        return  Long.valueOf(total).doubleValue();
    }

    // Todo : Extract the cart products
    private void saveCartProductToOrder (CartProducts cartProducts ,Orders orders){
        Product existing_product =productRepo.getById(cartProducts.getProduct().getProductId());
        PublicProductDetails supplier_details  = findSupplier(existing_product);
        this.totalPrice += calcPrice(existing_product,cartProducts.getQuantity());
        Supplier existing_supplier  =new Supplier();

        existing_supplier.setSupplierId(supplier_details.getSupplier().getSupplierId());

        Order new_order = Order
                .builder()
                .orders(orders)
                .product(existing_product)
                .supplier(existing_supplier)
                .quantity(cartProducts.getQuantity())
                .status("pending")
                .build();

         orderRepo.save(new_order);


    }

    @Transactional
    public OrderResponse postOrder (String customer_name , OrderRequest orderRequest){
        Customer existing_customer = customerService.getAuthenticatedCustomer(customer_name);
        Orders new_orders = createOrders(existing_customer,orderRequest.getCustomerShippingAddressId());
        Cart existing_cart  = cartService.getCart(customer_name);

        Orders finalNew_orders = new_orders;
        existing_cart.getCartProducts().forEach((cartProducts)->{
            saveCartProductToOrder(cartProducts, finalNew_orders);
        });

        new_orders.setTotalPrice(this.totalPrice);
        new_orders = ordersRepo.save(new_orders);

        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(new_orders.getOrdersId())
                .dateOfCreation(new_orders.getDateOfCreation())
                .status(new_orders.getStatus())
                .totalPrice(new_orders.getTotalPrice())
                .build();

        cartProductsRepo.deleteAllByCartId(existing_cart.getCartId());
        return  orderResponse ;
    }

    public List<CustomerOrders> getAllOrders (String username ){
        Customer existing_customer = customerService.getAuthenticatedCustomer(username);
        List<Orders> orders = ordersRepo.getTrackOrdersByCustomer(existing_customer.getCustomer_id());
        TypeToken<List<CustomerOrders>> typeToken = new TypeToken<>(){};
        List<CustomerOrders> customerOrders= modelMapper.map(orders,typeToken.getType());

        return customerOrders;
    }

    public List<CustomerOrders> getOrdersByOrderId (String username , Integer id){
        Customer existing_customer = customerService.getAuthenticatedCustomer(username);
       List<Orders>  existing_orders = ordersRepo.searchOrder(id,existing_customer.getCustomer_id());

       TypeToken<List<CustomerOrders>> typeToken = new TypeToken<>(){};
       List<CustomerOrders> searchOrdersList = modelMapper.map(existing_orders,typeToken.getType());

        return searchOrdersList;
    }

}
