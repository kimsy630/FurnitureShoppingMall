package mvc.vo;

import java.sql.Timestamp;

public class OrderVO {
	int order_id ;
	int p_id;
    String mb_id ;
    int order_count ;
    int order_price;
    int order_point ;
    String order_name ;
    String order_ph ;
    String order_address;
    Timestamp order_date;
    int order_state;
    ProductsVO product;
    
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public int getOrder_count() {
		return order_count;
	}
	public void setOrder_count(int order_count) {
		this.order_count = order_count;
	}
	public int getOrder_price() {
		return order_price;
	}
	public void setOrder_price(int order_price) {
		this.order_price = order_price;
	}
	public int getOrder_point() {
		return order_point;
	}
	public void setOrder_point(int order_point) {
		this.order_point = order_point;
	}
	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}
	public int getOrder_state() {
		return order_state;
	}
	public void setOrder_state(int order_state) {
		this.order_state = order_state;
	}
	public ProductsVO getProduct() {
		return product;
	}
	public void setProduct(ProductsVO product) {
		this.product = product;
	}
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public String getOrder_ph() {
		return order_ph;
	}
	public void setOrder_ph(String order_ph) {
		this.order_ph = order_ph;
	}
	public String getOrder_address() {
		return order_address;
	}
	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}
	@Override
	public String toString() {
		return "OrderVO [order_id=" + order_id + ", p_id=" + p_id + ", mb_id=" + mb_id + ", order_count=" + order_count
				+ ", order_price=" + order_price + ", order_point=" + order_point + ", order_name=" + order_name
				+ ", order_ph=" + order_ph + ", order_address=" + order_address + ", order_date=" + order_date
				+ ", order_state=" + order_state + ", product=" + product + "]";
	}
    
    
    
}
