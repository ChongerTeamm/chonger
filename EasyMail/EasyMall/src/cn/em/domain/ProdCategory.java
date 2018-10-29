package cn.em.domain;

public class ProdCategory {
	
	private int id;
	private String cname;
	
	public ProdCategory() {
	}
	
	public ProdCategory(int id, String cname) {
		super();
		this.id = id;
		this.cname = cname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "ProdCategory [id=" + id + ", cname=" + cname + "]";
	}
}
