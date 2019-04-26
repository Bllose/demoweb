package org.bllose.demoweb.repository;

import org.bllose.demoweb.model.dto.OrderDto;
import org.bllose.demoweb.model.dto.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositoryDemo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<OrderDto> find(){
        List<OrderDto> result = jdbcTemplate.query(
                        "select ID, ORDER_NO, NSRSBH, QYMC, PRODUCT_ID from zx_bank_order_info where rowNum < 50",
                (rs, rowNum) -> new OrderDto(rs.getString("ID"), rs.getString("NSRSBH"),
                        rs.getString("ORDER_NO"), rs.getString("PRODUCT_ID"), rs.getString("QYMC")));

        return result;
    }

    public List<OrderDto> searchTop5(){
        String sql = "select ID, ORDER_NO, NSRSBH, QYMC, PRODUCT_ID from zx_bank_order_info where rowNum < 5";

        return jdbcTemplate.query(sql, new OrderRowMapper());
    }

    /**
     * 从数据库中查找 job 所设置的定时
     * @param job
     * @return
     */
    public String findJobCron(String job) {
        String jobCon = jdbcTemplate.queryForObject("select cron from config where job='"+job+"'" , String.class);
        System.out.println("查找到job:"+job+", 所设置的时间为："+jobCon);
        return jobCon;
    }
}
