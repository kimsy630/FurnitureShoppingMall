package mvc.vo;

public class CartVO {
	int cart_id;
	int p_id;
    String mb_id ;
    int cart_count;
    ProductsVO product;
    
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
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
	public int getCart_count() {
		return cart_count;
	}
	public void setCart_count(int cart_count) {
		this.cart_count = cart_count;
	}
	public ProductsVO getProduct() {
		return product;
	}
	public void setProduct(ProductsVO product) {
		this.product = product;
	}
}
