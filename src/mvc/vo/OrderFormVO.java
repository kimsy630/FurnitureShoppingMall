package mvc.vo;

public class OrderFormVO {
	int id;
	int cart_id;
	int p_id;
	String p_image;
	String p_name;
	int count;
	int price; //판매가
	int saleprice;//회원할인 판매가
	int totalprice; //회원할인 판매가에 갯수만큼 곱한값
	int point;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_image() {
		return p_image;
	}
	public void setP_image(String p_image) {
		this.p_image = p_image;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(int saleprice) {
		this.saleprice = saleprice;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return "OrderFormVO [id=" + id + ", cart_id=" + cart_id + ", p_id=" + p_id + ", p_image=" + p_image
				+ ", p_name=" + p_name + ", count=" + count + ", price=" + price + ", saleprice=" + saleprice
				+ ", totalprice=" + totalprice + ", point=" + point + "]";
	}
	
	
}
