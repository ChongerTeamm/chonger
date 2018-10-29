package cn.em.domain;

public class Prod {
	
	private int id;
	private String name;
	private double price;
	private int cid;
	// 商品种类名称，该字段不属于商品表
	// 但是属于前台表单提交的数据，因此添加属性来封装数据
	private String cname; 
	private int pnum;
	private String imgurl;
	private String description;
	public Prod() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Prod(int id, String name, double price, int cid, String cname,
			int pnum, String imgurl, String description) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.cid = cid;
		this.cname = cname;
		this.pnum = pnum;
		this.imgurl = imgurl;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Prod [id=" + id + ", name=" + name + ", price=" + price
				+ ", cid=" + cid + ", cname=" + cname + ", pnum=" + pnum
				+ ", imgurl=" + imgurl + ", description=" + description + "]";
	}

}
