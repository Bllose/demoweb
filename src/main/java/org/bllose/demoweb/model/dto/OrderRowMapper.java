package org.bllose.demoweb.model.dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ProjectName: demoweb
 * @Package: org.bllose.demoweb.model.dto
 * @ClassName: OrderRowMapper
 * @Description: java类作用描述
 * @Author: Bllose
 * @CreateDate: 2019/4/23 9:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/23 9:04
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class OrderRowMapper implements RowMapper<OrderDto> {
    @Override
    public OrderDto mapRow(ResultSet rs, int i) throws SQLException {
        OrderDto order = new OrderDto();
        order.setId(rs.getString("ID"));
        order.setNsrsbh(rs.getString("NSRSBH"));
        order.setCompanyName(rs.getString("QYMC"));
        order.setProductId(rs.getString("PRODUCT_ID"));
        order.setOrderNo(rs.getString("ORDER_NO"));
        return order;
    }
}
