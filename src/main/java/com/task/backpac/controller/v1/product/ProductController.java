package com.task.backpac.controller.v1.product;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"02. 상품관리"})
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/product")
@Slf4j
public class ProductController {
}
