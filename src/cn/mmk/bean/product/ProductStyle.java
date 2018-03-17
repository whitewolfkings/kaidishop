package cn.mmk.bean.product;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.SearchableReference;
import org.compass.annotations.Store;

@Entity @Searchable(root=false)
public class ProductStyle implements Serializable{
	private static final long serialVersionUID = -4926119953511144279L;
	private Integer id;
	/** 样式的名称 **/
	private String name;
	/** 图片 **/
	private String imagename;
	/** 是否可见 **/
	private Boolean visible = true;
	private ProductInfo product;
	
	public ProductStyle() {}
	
	public ProductStyle(Integer id) {
		this.id = id;
	}

	public ProductStyle(String name, String imagename) {
		this.name = name;
		this.imagename = imagename;
	}
	@ManyToOne(cascade=CascadeType.REFRESH,optional=false)
	@JoinColumn(name="productid") @SearchableReference
	public ProductInfo getProduct() {
		return product;
	}
	public void setProduct(ProductInfo product) {
		this.product = product;
	}
	@Id @GeneratedValue @SearchableProperty(index=Index.NO,store=Store.YES)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=30,nullable=false) @SearchableProperty(index=Index.NO,store=Store.YES,name="styleName")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length=40,nullable=false) @SearchableProperty(index=Index.NO,store=Store.YES)
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	@Column(nullable=false)
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	@Transient
	public String getImageFullPath(){
		return "/images/product/"+ this.getProduct().getType().getTypeid()+ "/"+
		this.getProduct().getId()+ "/prototype/"+ this.imagename;
	}
	@Transient
	public String getImage140FullPath(){
		return "/images/product/"+ this.getProduct().getType().getTypeid()+ "/"+
		this.getProduct().getId()+ "/140x/"+ this.imagename;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ProductStyle other = (ProductStyle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
