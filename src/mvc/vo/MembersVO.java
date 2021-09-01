package mvc.vo;

import java.sql.Timestamp;

public class MembersVO {
	String 		mb_Id;//아이디
	String 		mb_Pwd;//비밀번호
	String 		mb_Name;//이름 /판매자
	String 		mb_CertifiNum;//인증번호 주민번호 사업자번호
	String 		mb_Phone;//핸드폰
	String 		mb_Email;//이메일
	String 		mb_Address;//
	Timestamp	mb_join_date;
	Timestamp   mb_Withdraw_date;
	int	   		mb_Rank_Point;
	int    		mb_Point;
	String 		mb_Classifi;
	public String getMb_Id() {
		return mb_Id;
	}
	public void setMb_Id(String mb_Id) {
		this.mb_Id = mb_Id;
	}
	public String getMb_Pwd() {
		return mb_Pwd;
	}
	public void setMb_Pwd(String mb_Pwd) {
		this.mb_Pwd = mb_Pwd;
	}
	public String getMb_Name() {
		return mb_Name;
	}
	public void setMb_Name(String mb_Name) {
		this.mb_Name = mb_Name;
	}
	public String getMb_CertifiNum() {
		return mb_CertifiNum;
	}
	public void setMb_CertifiNum(String mb_CertifiNum) {
		this.mb_CertifiNum = mb_CertifiNum;
	}
	public String getMb_Phone() {
		return mb_Phone;
	}
	public void setMb_Phone(String mb_Phone) {
		this.mb_Phone = mb_Phone;
	}
	public String getMb_Email() {
		return mb_Email;
	}
	public void setMb_Email(String mb_Email) {
		this.mb_Email = mb_Email;
	}
	
	public String getMb_Address() {
		return mb_Address;
	}
	public void setMb_Address(String mb_Address) {
		this.mb_Address = mb_Address;
	}
	public Timestamp getMb_join_date() {
		return mb_join_date;
	}
	public void setMb_join_date(Timestamp mb_join_date) {
		this.mb_join_date = mb_join_date;
	}
	public Timestamp getMb_Withdraw_date() {
		return mb_Withdraw_date;
	}
	public void setMb_Withdraw_date(Timestamp mb_Withdraw_date) {
		this.mb_Withdraw_date = mb_Withdraw_date;
	}
	public int getMb_Rank_Point() {
		return mb_Rank_Point;
	}
	public void setMb_Rank_Point(int mb_Rank_Point) {
		this.mb_Rank_Point = mb_Rank_Point;
	}
	public int getMb_Point() {
		return mb_Point;
	}
	public void setMb_Point(int mb_Point) {
		this.mb_Point = mb_Point;
	}
	public String getMb_Classifi() {
		return mb_Classifi;
	}
	public void setMb_Classifi(String mb_Classifi) {
		this.mb_Classifi = mb_Classifi;
	}
	
	public String getRankstr() {
		
		/*회원가입 ~ 2,000점
		LV.2 루키	2,001 ~ 10,000점
		LV.3 멤버	10,001 ~ 100,000점
		LV.4 브론즈	100,001 ~ 200,000점
		LV.5 실버	200,001 ~ 500,000점
		LV.6 골드	500,001 ~ 1,000,000점
		LV.7 플래티넘	1,000,001 ~ 2,000,000점
		LV.8 다이아몬드	2,000,001점 ~
		*/
		if(mb_Rank_Point<=2000)return "뉴비";
		if(mb_Rank_Point<=10000)return "루키";
		if(mb_Rank_Point<=100000)return "멤버";
		if(mb_Rank_Point<=200000)return "브론즈";
		if(mb_Rank_Point<=500000)return "실버";
		if(mb_Rank_Point<=1000000)return "골드";
		if(mb_Rank_Point<=2000000)return "플래티넘";
		else return "다이아몬드";
	}
	public int getRankDiscount() {
		if(mb_Rank_Point<=2000)return 1;
		if(mb_Rank_Point<=10000)return 2;
		if(mb_Rank_Point<=100000)return 3;
		if(mb_Rank_Point<=200000)return 4;
		if(mb_Rank_Point<=500000)return 5;
		if(mb_Rank_Point<=1000000)return 6;
		if(mb_Rank_Point<=2000000)return 7;
		else return 7;
	}
	
	public int getRankCal(int num) {
		float num2 =0;
		if(mb_Rank_Point<=2000)num2 = 0.01f;
		else if(mb_Rank_Point<=10001)num2 = 0.02f;
		else if(mb_Rank_Point<=100000)num2 = 0.03f;
		else if(mb_Rank_Point<=200000)num2 = 0.04f;
		else if(mb_Rank_Point<=500000)num2 = 0.05f;
		else if(mb_Rank_Point<=1000000)num2 = 0.06f;
		else if(mb_Rank_Point<=2000000)num2 = 0.07f;
		else num2 =  0.08f;
		return (int)(num*num2);
	}
	
	
}
