package cn.mmk.bean.product;

public enum Sex {
	NONE{public String getName(){return "��Ů����";} },
	MAN{public String getName(){return "��";} },
	WOMEN{public String getName(){return "Ů";}};
	public abstract String getName();
}
