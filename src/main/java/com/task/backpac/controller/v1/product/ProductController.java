package com.task.backpac.controller.v1.product;

import com.task.backpac.biz.comm.message.MulLangMessage;
import com.task.backpac.biz.core.product.dto.OrderDto;
import com.task.backpac.biz.core.product.dto.ProductDto;
import com.task.backpac.biz.core.product.service.ProductService;
import com.task.backpac.controller.response.ResponseController;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"02. 제품관리"})
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
@Slf4j
public class ProductController {

    private final MulLangMessage lang;
    private final ResponseController response;
    private final ProductService productService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "제품 저장", notes = "신규 제품 정보를 저장한다.")
    @PostMapping(value = "/products")
    public Object setProducts(ProductDto productDto) {
        return response.single(productService.insertProduct(productDto), "", "");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "제품 조회", notes = "제품 전체 정보를 조회한다.")
    @GetMapping(value = "/products")
    public Object getProducts(@ApiParam(value = "페이지 번호", required = true) @RequestParam(defaultValue = "0") int page,
                              @ApiParam(value = "페이지 로우 사이즈", required = true) @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return response.page(productService.selectListProduct(pageable), "", "");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "주문 정보 입력", notes = "제품 주문 정보를 입력한다.")
    @PostMapping(value = "/orders")
    public Object setOrders(@ApiParam(value = "주문자 회원 번호", required = true) @RequestParam Long userNo,
                           @ApiParam(value = "주문 상품 번호", required = true) @RequestParam Long productId) {

        OrderDto.Req orderDto = new OrderDto.Req();
        orderDto.setUserNo(userNo);
        orderDto.setProductId(productId);

        return response.single(productService.insertOrder(orderDto), "", "");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "단일 회원의 주문 목록 조회", notes = "단일 회원의 주문 목록을 조회한다.")
    @GetMapping(value = "/orders/{userNo}")
    public Object getOrders(@ApiParam(value = "주문자 회원 번호", required = true) @PathVariable Long userNo) {

        OrderDto.Req orderDto = new OrderDto.Req();
        orderDto.setUserNo(userNo);

        return response.list(productService.selectListOrder(orderDto), "", "");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "여러 회원의 주문 목록 조회", notes = "여러 회원의 주문 목록을 조회한다.")
    @GetMapping(value = "/orders")
    public Object getOrdersPage(@ApiParam(value = "페이지 번호", required = true) @RequestParam(defaultValue = "0") int page,
                               @ApiParam(value = "페이지 로우 사이즈", required = true) @RequestParam(defaultValue = "10") int size,
                           OrderDto.UserOrderReq orderReq) {

        Pageable pageable = PageRequest.of(page, size);

        return response.page(productService.selectPageUserOrder(pageable, orderReq), "", "");
    }
}
