package org.big.prj.controller;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.big.prj.dto.CartDTO;
import org.big.prj.dto.OrderDTO;
import org.big.prj.dto.UsersDTO;
import org.big.prj.service.CartService;
import org.big.prj.service.OrderService;
import org.big.prj.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart-and-order")
public class CartAndOrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private UsersService usersService;

    // 장바구니에 아이템 추가
    @PostMapping("/cart/add")
    public String addToCart(HttpSession session,
                            @RequestParam("carId") String carId,
                            @RequestParam("quantity") int quantity,
                            @RequestParam("totalPrice") String totalPrice, RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
        	redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/login?error=" + URLEncoder.encode("로그인이 필요합니다.", StandardCharsets.UTF_8);
        }

        CartDTO cart = new CartDTO();
        cart.setUserId(userId);
        cart.setCarId(carId);
        cart.setQuantity(quantity);
        cart.setTotalPrice(totalPrice);

        cartService.addToCart(cart);

        return "redirect:/cart-and-order/cart";
    }

    // 장바구니 조회
    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
        	redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/login?error=" + URLEncoder.encode("로그인이 필요합니다.", StandardCharsets.UTF_8);
        }
        List<CartDTO> cartItems = cartService.getCartItems(userId);
        model.addAttribute("cartItems", cartItems);
        return "thymeleaf/cartOrder/cart";
    }

    // 장바구니에서 아이템 삭제
    @GetMapping("/cart/delete/{cartNo}")
    public String removeFromCart(@PathVariable("cartNo") String cartNo) {
        cartService.removeFromCart(cartNo);
        return "redirect:/cart-and-order/cart";
    }
    
    // 장바구니 수량 변경
    @PostMapping("/cart/update")
    @ResponseBody
    public String updateCart(@RequestParam("cartNo") String cartNo, @RequestParam("quantity") int quantity) {
        CartDTO cart = new CartDTO();
        cart.setCartNo(cartNo);
        cart.setQuantity(quantity);
        cartService.updateCart(cart);
        return "success";
    }

    // 결제 페이지 조회
    @GetMapping("/order/checkout")
    public String checkout(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
       
        UsersDTO user = usersService.getUserById(userId); // 유저 정보 가져오기
        List<CartDTO> cartItems = cartService.getCartItems(userId);
        int totalPrice = cartService.calculateTotalPrice(cartItems);

        model.addAttribute("user", user);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
	
        return "thymeleaf/cartOrder/order";
    }

    // 주문 생성
    @PostMapping("/order/place")
    public String placeOrder(HttpSession session,
                             @RequestParam("carId") String carId,
                             @RequestParam("quantity") int quantity,
                             @RequestParam("totalPrice") String totalPrice) {
        String userId = (String) session.getAttribute("userId");
      
        // Assuming carId is passed from the form
        List<CartDTO> cartItems = cartService.getCartItems(userId);
        String cartNo = cartItems.isEmpty() ? null : cartItems.get(0).getCartNo();

        OrderDTO order = new OrderDTO();
        order.setUserId(userId);
        order.setCarId(carId);
        order.setCartNo(cartNo);
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);
        // orderDate is handled by the database (SYSDATE)

        orderService.placeOrder(order);
        
        // 장바구니 상태 업데이트
        cartService.updateCartStatus(userId, "ordered");

        return "redirect:/cart-and-order/order/confirmation";
    }

    // 주문 완료
    @PostMapping("/order/complete")
    public String orderComplete(@RequestParam("userId") String userId,
                                @RequestParam("userPost") String userPost,
                                @RequestParam("userAd") String userAd,
                                @RequestParam("userAdd") String userAdd,
                                @RequestParam("totalPrice") String totalPrice,
                                @RequestParam("cartItems") List<String> cartItems,
                                Model model) {
        for (String cartItem : cartItems) {
            CartDTO cartDTO = cartService.getCartItem(cartItem);
            OrderDTO order = new OrderDTO();
            // orderNo는 트리거에 의해 자동 생성됨
            order.setUserId(userId);
            order.setUserPost(userPost);
            order.setUserAd(userAd);
            order.setUserAdd(userAdd);
            order.setCarId(cartDTO.getCarId());
            order.setCartNo(cartDTO.getCartNo());
            order.setQuantity(cartDTO.getQuantity());
            order.setTotalPrice(cartDTO.getTotalPrice());
            orderService.placeOrder(order);
        }

        // 마지막으로 생성된 주문 번호를 가져옴
        String orderNo = orderService.getLastOrderNo();
        
        // 장바구니 상태 업데이트
        cartService.updateCartStatus(userId, "ordered");
        
        return "redirect:/cart-and-order/order/confirmation?orderNo=" + orderNo;
    }

    // 주문 확인 페이지 조회
    @GetMapping("/order/confirmation")
    public String orderConfirmation(@RequestParam("orderNo") String orderNo, Model model) {
        OrderDTO order = orderService.getOrderDetails(orderNo);
        if (order == null) {
            model.addAttribute("errorMessage", "주문 정보를 찾을 수 없습니다.");
            return "thymeleaf/cartOrder/order_confirmation";
        }

        model.addAttribute("orderNo", order.getOrderNo());
        model.addAttribute("kind", order.getKind());
        model.addAttribute("color", order.getColor());
        model.addAttribute("option", order.getOptions());
        model.addAttribute("totalPrice", order.getTotalPrice());

        return "thymeleaf/cartOrder/order_confirmation";
    }
    
 
    
    //주문확인
    @GetMapping("/orders")
    public String viewOrders(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
    	 String userId = (String) session.getAttribute("userId");
         if (userId == null) {
             redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
             return "redirect:/login?error=" + URLEncoder.encode("로그인이 필요합니다.", StandardCharsets.UTF_8);
         }
        List<OrderDTO> orders = orderService.getOrdersByUserId(userId);
        model.addAttribute("orders", orders);
        return "thymeleaf/cartOrder/orders";
    }

    @GetMapping("/orders/{orderNo}")
    public String viewOrderDetails(@PathVariable("orderNo") String orderNo, Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        OrderDTO order = orderService.getOrderDetails(orderNo);
        if (order == null) {
            model.addAttribute("errorMessage", "주문 정보를 찾을 수 없습니다.");
            return "thymeleaf/cartOrder/orders";
        }
        model.addAttribute("order", order);
        if ("admin".equals(userId)) {
            return "thymeleaf/cartOrder/admin_order_details";
        }
        return "thymeleaf/cartOrder/order_details";
    }
    
    
    @GetMapping("/list")
    public String listOrders(
        Model model,
        @RequestParam(value = "search", required = false, defaultValue = "") String search,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size) {

        int offset = page * size;
        List<OrderDTO> orders = orderService.getAllOrders(search, size, offset);
        model.addAttribute("orders", orders);
        model.addAttribute("search", search);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        // For pagination
        long totalOrders = orderService.countOrders(search);
        int totalPages = (int) Math.ceil((double) totalOrders / size);
        model.addAttribute("totalPages", totalPages);

        return "thymeleaf/cartOrder/admin_order_list";
    }
}