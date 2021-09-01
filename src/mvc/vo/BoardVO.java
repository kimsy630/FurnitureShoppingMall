package mvc.vo;

import java.sql.Timestamp;
import java.util.List;

public class BoardVO {
    int b_id;
    String mb_id;
    String b_writer;
    String b_subject;
    String b_content;
    int b_ref;
    int b_ref_step;
    int b_ref_level;
    Timestamp b_reg_date;
    
    List<BoardVO>reply;
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getB_writer() {
		return b_writer;
	}
	public void setB_writer(String b_writer) {
		this.b_writer = b_writer;
	}
	public String getB_subject() {
		return b_subject;
	}
	public void setB_subject(String b_subject) {
		this.b_subject = b_subject;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public int getB_ref() {
		return b_ref;
	}
	public void setB_ref(int b_ref) {
		this.b_ref = b_ref;
	}
	public int getB_ref_step() {
		return b_ref_step;
	}
	public void setB_ref_step(int b_ref_step) {
		this.b_ref_step = b_ref_step;
	}
	public int getB_ref_level() {
		return b_ref_level;
	}
	public void setB_ref_level(int b_ref_level) {
		this.b_ref_level = b_ref_level;
	}
	public Timestamp getB_reg_date() {
		return b_reg_date;
	}
	public void setB_reg_date(Timestamp b_reg_date) {
		this.b_reg_date = b_reg_date;
	}
	public List<BoardVO> getReply() {
		return reply;
	}
	public void setReply(List<BoardVO> reply) {
		this.reply = reply;
	}
    
    
}
