package cn.tedu.entity.vo;

import java.util.Arrays;

public class OrderVO {
	
	private Integer uid;
	
	private String name;
	
	private String phone;
	
	private String address;
	
	private Integer status;
	
	private Long price;
	
	private Integer cids[];

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer[] getCids() {
		return cids;
	}

	public void setCids(Integer[] cids) {
		this.cids = cids;
	}

	@Override
	public String toString() {
		return "OrderVO [" + (uid != null ? "uid=" + uid + ", " : "") + (name != null ? "name=" + name + ", " : "")
				+ (phone != null ? "phone=" + phone + ", " : "") + (address != null ? "address=" + address + ", " : "")
				+ (status != null ? "status=" + status + ", " : "") + (price != null ? "price=" + price + ", " : "")
				+ (cids != null ? "cids=" + Arrays.toString(cids) : "") + "]";
	}
	
	
}
