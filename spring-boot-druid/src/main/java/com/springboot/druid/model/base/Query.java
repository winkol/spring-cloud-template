package com.springboot.druid.model.base;

import com.google.common.base.CaseFormat;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 查询条件
 *
 * @author sunshun
 * @date 2018/12/30
 */
public class Query {

    private Map<String, Object> condition;
    private Paging page;
    private String orderColumn;
    private String orderType;

    public String getOrderByClause() {
        StringBuffer sb = new StringBuffer(" ");
        if(!StringUtils.isEmpty(orderColumn)){
            sb.append(" order by ");
            if(!StringUtils.isEmpty(orderColumn)) {//展示排序字段
                String orderColumnUnder = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderColumn);
                sb.append(orderColumnUnder);
                if ("desc".equalsIgnoreCase(orderType)) {
                    sb.append(" desc ");
                } else {
                    sb.append(" asc ");
                }
            }
        }
        return  sb.toString();
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    public Paging getPage() {
        return page;
    }

    public void setPage(Paging page) {
        this.page = page;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
