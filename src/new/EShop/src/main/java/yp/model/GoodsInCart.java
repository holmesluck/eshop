package yp.model;
/**
 * 一个用于数据存储的类，用来存储购物车中商品信息，包括商品对象，购买数量，商品库存。
 * @author yu
 *
 */
public class GoodsInCart {
	/**
	 * 商品对象
	 */
	private TGoods tGoods;
	/**
	 * 购买数量
	 */
	private int amount;
	/**
	 * 库存
	 */
	private int stock;
	/**
	 * 现价
	 */
	private double price;
	/**
	 * 总价
	 */
	private double totalPrice;
	/**
	 * 折后价格
	 */
	private double countPrice;
	
	/**
	 * 是否被勾选  
	 * 默认为true
	 */
	private boolean boolSelected;
	
	public GoodsInCart(TGoods tGoods,int amount,int stock){
		this.tGoods = tGoods;
		this.amount = amount;
		this.stock = stock;
		price = tGoods.getGoodsPrice() * tGoods.getGoodsDiscount();
		totalPrice = price * amount;
		countPrice = 0.0;
		boolSelected = true;
		
	}
	public TGoods gettGoods() {
		return tGoods;
	}
	public void settGoods(TGoods tGoods) {
		this.tGoods = tGoods;
	}
	public double getCountPrice() {
		return countPrice;
	}
	public void setCountPrice(double countPrice) {
		this.countPrice = countPrice;
	}
	public boolean isBoolSelected() {
		return boolSelected;
	}
	public void setBoolSelected(boolean boolSelected) {
		this.boolSelected = boolSelected;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public TGoods getTGoods() {
		return tGoods;
	}
	public void setTGoods(TGoods TGoods) {
		this.tGoods = TGoods;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
		this.totalPrice = amount * this.price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

}
