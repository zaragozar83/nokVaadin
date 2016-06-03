package com.coffeenok.nok.ui;

import org.springframework.context.ApplicationContext;

import com.coffeenok.nok.dao.OrderDao;
import com.coffeenok.nok.dao.impl.OrderDaoImpl;
import com.coffeenok.nok.domain.Order;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class AddNewOrderListener implements Button.ClickListener  {

	private static final long serialVersionUID = 1L;

	public void buttonClick(Button.ClickEvent event) {
		OrdersView view = (OrdersView) event.getButton().getParent();
		
		nokUI current = (nokUI) (UI.getCurrent());
		
		ApplicationContext context = current.getApplicationContext();
		
		//OrderDao orderDao = context.getBean(OrderDao.class);
		OrderDao orderDao = (OrderDao)context.getBean(OrderDaoImpl.class);
		
		TextField txtOrderLabel = view.getTxtOrderLabel();
		
		String value = txtOrderLabel.getValue();
		
		Order order = new Order();
		order.setNombre(value);
		orderDao.save(order);
		current.getNavigator().navigateTo("orders");
	}
}
