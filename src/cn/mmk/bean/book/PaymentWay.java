package cn.mmk.bean.book;
/**
 * 支付方式
 */
public enum PaymentWay {
	/** 网上支付 */
	NET{
		public String getName(){return "网上支付";}
	},
	/** 货到付款 */
	COD{
		public String getName(){return "货到付款";}
	},
	/** 银行电汇 */
	BANKREMITTANCE{
		public String getName(){return "银行电汇";}
	},
	/** 邮局汇款 */
	POSTOFFICEREMITTANCE{
		public String getName(){return "邮局汇款";}
	};
	public abstract String getName();
}
