package cn.tedu.entity;

public class ChangePassword {
	private String oldPassword;
	
	private String newPassword;

	private Integer uid;
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "ChangePassword [" + (oldPassword != null ? "oldPassword=" + oldPassword + ", " : "")
				+ (newPassword != null ? "newPassword=" + newPassword + ", " : "") + (uid != null ? "uid=" + uid : "")
				+ "]";
	}

	
	
}
