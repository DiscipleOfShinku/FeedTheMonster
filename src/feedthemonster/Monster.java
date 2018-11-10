package feedthemonster;

import java.sql.Date;

public class Monster {
	
	private Integer id;
	private String name;
	private Integer level;
	private Date birthday;
	private String picture;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getLevel() {
		return level;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Date getBirthday() {
		return birthday;
	}

	public void setPicture(Integer id) {
		this.picture = "/resources/images/" + id + ".png";
	}

	public String getPicture() {
		return picture;
	}

}
