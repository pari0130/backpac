package com.task.backpac.biz.core.product.repo;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.task.backpac.biz.core.product.dto.OrderDto;
import com.task.backpac.biz.core.product.dto.ProductDto;
import com.task.backpac.biz.core.product.entity.Order;
import com.task.backpac.biz.core.product.entity.Product;
import com.task.backpac.biz.core.product.entity.QOrder;
import com.task.backpac.biz.core.product.entity.QProduct;
import com.task.backpac.biz.core.user.entity.QUser;
import com.task.backpac.biz.core.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductRepository {

    private final EntityManager entityManager;
    private final ProductJPARepository productJPARepository;
    private final OrderJPARepository orderJPARepository;
    private final JPAQueryFactory queryFactory;

    public ProductRepositoryImpl(EntityManager entityManager, ProductJPARepository productJPARepository, OrderJPARepository orderJPARepository, JPAQueryFactory queryFactory) {
        super(User.class);
        this.entityManager = entityManager;
        this.productJPARepository = productJPARepository;
        this.orderJPARepository = orderJPARepository;
        this.queryFactory = queryFactory;
    }

    @Override
    public Product insertProduct(ProductDto dto) {
        Product product = Product.builder()
                .productName(dto.getProductName())
                .productDesc(dto.getProductDesc())
                .build();

        return productJPARepository.save(product);
    }

    @Override
    public List<ProductDto> selectListProduct() {

        QProduct qProduct = QProduct.product;

        JPAQuery<ProductDto> query = new JPAQuery<ProductDto>(entityManager);

        return query.from(qProduct).fetch();
    }

    @Override
    public Order insertOrder(OrderDto.Req dto) {

        Order order = Order.builder()
                .orderId(dto.getOrderId())
                .productId(dto.getProductId())
                .userNo(dto.getUserNo())
                .build();

        return orderJPARepository.save(order);
    }

    @Override
    public List<OrderDto.Res> selectListOrder(OrderDto.Req dto) {

        QOrder qOrder = QOrder.order;
        QProduct qProduct = QProduct.product;
        QUser qUser = QUser.user;
        JPAQuery<OrderDto.Res> query = new JPAQuery<OrderDto.Res>(entityManager);

        Expression<?>[] expressions = {
                qOrder.orderId,
                qOrder.userNo,
                qProduct.productName,
                qProduct.productDesc,
                ExpressionUtils.as(qOrder.createdDate, "orderDate")
        };

        query.select(Projections.bean(OrderDto.Res.class, expressions))
                .from(qOrder)
                .innerJoin(qUser).on(qOrder.userNo.eq(qUser.userNo))
                .innerJoin(qProduct).on(qOrder.productId.eq(qProduct.productId))
                .where(qOrder.userNo.eq(dto.getUserNo()))
                .orderBy(qOrder.createdDate.desc());

        return query.fetch();
    }

    @Override
    public Page<OrderDto.UserOrderRes> selectPageUserOrder(Pageable pageable, OrderDto.UserOrderReq dto) {

        QOrder qOrder = QOrder.order;
        QProduct qProduct = QProduct.product;
        QUser qUser = QUser.user;
        JPAQuery<OrderDto.UserOrderRes> query = new JPAQuery<OrderDto.UserOrderRes>(entityManager);

        Expression<?>[] expressions = {
                qUser.userNo,
                qUser.userEmail,
                qUser.userName,
                ExpressionUtils.as(qOrder.orderId, "lastOrderId"),
                qProduct.productName,
                qProduct.productDesc,
                ExpressionUtils.as(qOrder.createdDate, "orderDate")
        };

        query.select(Projections.bean(OrderDto.UserOrderRes.class, expressions))
                .from(qOrder)
                .innerJoin(qUser).on(qOrder.userNo.eq(qUser.userNo))
                .innerJoin(qProduct).on(qOrder.productId.eq(qProduct.productId));

        // 이름 검색
        if(StringUtils.isNotEmpty(dto.getUserName())) {
            query.where(qUser.userName.contains(dto.getUserName()));
        }

        // 이메일 검색
        if(StringUtils.isNotEmpty(dto.getUserEmail())) {
            query.where(qUser.userEmail.contains(dto.getUserEmail()));
        }

        // 마지막 주문정보 조회 여부
       if(dto.isLastOrder()){
            query.where(qOrder.createdDate.in(
                        JPAExpressions.select(qOrder.createdDate.max())
                                .from(qOrder)
                                .groupBy(qOrder.userNo)));
        }

       // 날짜 사이 조건 검색
       if(dto.getSearchStartDate() != null && dto.getSearchEndDate() != null){
           query.where(qOrder.createdDate.between(dto.getSearchStartDate(), dto.getSearchEndDate()));
       }

        query.orderBy(qOrder.createdDate.desc());

        List<OrderDto.UserOrderRes> paging = getQuerydsl().applyPagination(pageable, query).fetch();

        return new PageImpl<>(paging, pageable, query.fetchCount());
    }

    @Override
    public boolean confirmOrderId(String orderId) {

        QOrder qOrder = QOrder.order;

        Integer count =
                queryFactory
                        .selectOne()
                        .from(qOrder)
                        .where(qOrder.orderId.eq(orderId))
                        .fetchFirst();

        return count == null;
    }
}
