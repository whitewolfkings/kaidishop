package cn.mmk.bean.product;

public enum Sex {
	NONE{public String getName(){return "男女不限";} },
	MAN{public String getName(){return "男";} },
	WOMEN{public String getName(){return "女";}};
	public abstract String getName();
}
