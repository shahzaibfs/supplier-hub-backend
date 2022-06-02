package com.fyp.supplierHub.cart.service;

import com.fyp.supplierHub.cart.dtos.CartReq;
import com.fyp.supplierHub.cart.entity.Cart;
import com.fyp.supplierHub.cart.entity.CartProducts;
import com.fyp.supplierHub.cart.repository.CartProductsRepo;
import com.fyp.supplierHub.cart.repository.CartRepo;
import com.fyp.supplierHub.customer.entity.Customer;
import com.fyp.supplierHub.customer.service.CustomerService;
import com.fyp.supplierHub.customer.service.CustomerServiceImp;
import com.fyp.supplierHub.product.enitity.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepo cartRepo ;
    private final CartProductsRepo cartProductsRepo ;
    private final CustomerService customerService ;
    private final ModelMapper modelMapper ;

    public  Cart getCart  (String customer_name){
        Customer existing_customer  = customerService.getAuthenticatedCustomer(customer_name);
        Cart existing_cart  = cartRepo.getCartById(existing_customer.getCustomer_id());

        if(existing_cart != null ){
            /*** when There is a Cart for that Customer ***/
            return existing_cart ;
        }
        /*** when There is No Cart for that Customer ***/
        Cart new_cart  = Cart.builder()
                .customer(existing_customer)
                .build();
        new_cart =  cartRepo.save(new_cart);
        return new_cart ;
    }


    private CartProducts addCartProduct(CartReq cartReq , Cart existing_cart){
        Product existing_product = new Product();
        CartProducts existing_cart_product = cartProductsRepo.getCartProductByProductId(cartReq.getProductId(),existing_cart.getCartId());

        existing_product.setProductId(cartReq.getProductId());
        if(existing_cart_product != null){
            /*** When the Product is Already Existed in the Cart  ***/
            existing_cart_product.setQuantity(cartReq.getQuantity());
            existing_cart_product=  cartProductsRepo.save(existing_cart_product);
            return existing_cart_product ;
        }
        /*** When the Product is not Existed in the Cart  ***/
        CartProducts new_cart_product = CartProducts.builder()
                .product(existing_product)
                .quantity(cartReq.getQuantity())
                .cart(existing_cart)
                .build();
        new_cart_product= cartProductsRepo.save(new_cart_product);
        return new_cart_product;
    }

    public String addProductToCart  (List<CartReq> cartReqList ,String customer_name) {
        Cart existing_cart = getCart(customer_name);

        cartReqList.forEach((cartReq)->{
            CartProducts cartProducts = addCartProduct(cartReq,existing_cart);
            System.out.println("Cart product Saved Successfully");
        });

        return "Cart Updated Successfully ";
    }

}
