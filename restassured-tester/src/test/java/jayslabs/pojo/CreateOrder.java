package jayslabs.pojo;

import java.util.List;

public class CreateOrder {
	public List<OrderDetail> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDetail> orderDetails) {
		this.orders = orderDetails;
	}

	private List<OrderDetail> orders;
}
