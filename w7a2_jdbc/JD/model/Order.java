package w7a2_jdbc.JD.model;

public class Order {
	private int id;
	private int item_id;
	private int qty;
	
	public Order(int id, int item_id, int qty) {
		super();
		this.id = id;
		this.item_id = item_id;
		this.qty = qty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
}
