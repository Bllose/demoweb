package org.bllose.demoweb.model.dto;

public class OrderDto {

    private String id;
    private String nsrsbh;
    private String orderNo;
    private String productId;
    private String companyName;

    public OrderDto(String id, String nsrsbh, String orderNo,
                    String productId, String companyName){
        this.id = id;
        this.nsrsbh = nsrsbh;
        this.orderNo = orderNo;
        this.productId = productId;
        this.companyName = companyName;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("{ ");
        sb.append("id:").append(id).append(" | ");
        sb.append("nsrsbh:").append(nsrsbh).append(" | ");
        sb.append("orderNo:").append(orderNo).append(" | ");
        sb.append("productId:").append(productId).append(" | ");
        sb.append("companyName:").append(companyName);
        sb.append(" }");
        return new String(sb);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
