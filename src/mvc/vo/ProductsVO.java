package mvc.vo;

import java.sql.Timestamp;

public class ProductsVO {
    int p_id ;//상품코드 
    String mb_id;//판매자*
    String p_name ;//이름*
    String p_category;//카테고리*
    int p_count;//판매수량*
    int p_price;//판매가*
    int p_saleprice;//할인가*
    int p_score ;//평점
    String p_image;//메인이미지*
    String p_image2;
    String p_image3;//상세이미지*
    int p_saleCount;//판매량 0
    int p_readCount;//조회 0
    Timestamp p_date;//등록일자 timestamp   
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
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_category() {
		return p_category;
	}
	public void setP_category(String p_category) {
		this.p_category = p_category;
	}
	public int getP_count() {
		return p_count;
	}
	public void setP_count(int p_count) {
		this.p_count = p_count;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public int getP_saleprice() {
		return p_saleprice;
	}
	public void setP_saleprice(int p_saleprice) {
		this.p_saleprice = p_saleprice;
	}
	public int getP_score() {
		return p_score;
	}
	public void setP_score(int p_score) {
		this.p_score = p_score;
	}
	public String getP_image() {
		return p_image;
	}
	public void setP_image(String p_image) {
		this.p_image = p_image;
	}
	public String getP_image2() {
		return p_image2;
	}
	public void setP_image2(String p_image2) {
		this.p_image2 = p_image2;
	}
	public String getP_image3() {
		return p_image3;
	}
	public void setP_image3(String p_image3) {
		this.p_image3 = p_image3;
	}
	public int getP_saleCount() {
		return p_saleCount;
	}
	public void setP_saleCount(int p_saleCount) {
		this.p_saleCount = p_saleCount;
	}
	public int getP_readCount() {
		return p_readCount;
	}
	public void setP_readCount(int p_readCount) {
		this.p_readCount = p_readCount;
	}
	public Timestamp getP_date() {
		return p_date;
	}
	public void setP_date(Timestamp p_date) {
		this.p_date = p_date;
	}
}
