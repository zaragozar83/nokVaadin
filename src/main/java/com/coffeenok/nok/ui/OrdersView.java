package com.coffeenok.nok.ui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.coffeenok.nok.domain.Order;
import com.coffeenok.nok.service.OrderService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Grid.MultiSelectionModel;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class OrdersView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	private TextField txtOrderLabel = new TextField("Order Name: ");
	private BeanItemContainer<Order> containerOrders = new BeanItemContainer<Order>(Order.class);
	private List<Order> listOrders = new ArrayList<Order>();
	private List<Order> listOrdersSelected = new ArrayList<Order>();
	private Button btnLogOut = new Button("LogOut");
	private GridLayout containerHead = new GridLayout(6,1);
	private Button btnDelete = new Button("Delete Selected");
	private MultiSelectionModel selection = null;
	
	private Grid gridOrders = new Grid("Orders");
	
	public OrdersView(){
		containerHead.setWidth(100, Unit.PERCENTAGE);
		containerHead.setHeight(100, Unit.PIXELS);
		containerHead.setMargin(Boolean.TRUE);
		containerHead.addComponent(txtOrderLabel, 0, 0);
		
		LogoutListener logOut = new LogoutListener();
		btnLogOut.addClickListener(logOut);
		
		containerHead.addComponent(btnLogOut, 5,0);
		
		btnDelete.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				//selection = (MultiSelectionModel) gridOrders.getSelectionModel();
				
				for(Object object : selection.getSelectedRows()){
					//gridOrders.getContainerDataSource().removeItem(object);
					containerOrders.removeItem(object);
				}
				gridOrders.getSelectionModel().reset();
			}
		});
		
		setMargin(Boolean.TRUE);
		setSpacing(Boolean.TRUE);
		initGrid();
	}

	public void enter(ViewChangeListener.ViewChangeEvent event) {
		removeAllComponents();
		addComponent(containerHead);
		Button btnAddNewOrder = new Button("Add New Order");
		btnAddNewOrder.addClickListener(new AddNewOrderListener());
		addComponent(btnAddNewOrder);
		nokUI current = (nokUI) UI.getCurrent();
		ApplicationContext context = current.getApplicationContext();
		OrderService service = (OrderService) context.getBean("orderService");
		//OrderService service = context.getBean(OrderService.class);
		
		listOrders = service.findAll();
		containerOrders.addAll(listOrders);
		selection = (MultiSelectionModel) gridOrders.getSelectionModel();
		
		addComponent(gridOrders);
		addComponent(btnDelete);
	}
	
	private void initGrid(){
		gridOrders.setContainerDataSource(containerOrders);
		gridOrders.setWidth(100, Unit.PERCENTAGE);
		gridOrders.setHeight(100, Unit.PERCENTAGE);
		gridOrders.setSelectionMode(SelectionMode.MULTI);
		gridOrders.setEditorEnabled(Boolean.TRUE);
		gridOrders.setImmediate(Boolean.TRUE);
		gridOrders.addItemClickListener(new ItemClickListener() {
			
			private static final long serialVersionUID = 1L;

			public void itemClick(ItemClickEvent event) {
					String nombre = event.getItem().getItemProperty("nombre").getValue().toString();
					Notification.show(nombre, Type.HUMANIZED_MESSAGE);			
			}
		});
		
		// Create a header row to hold column filters
		HeaderRow filterRow = gridOrders.appendHeaderRow();
		
		for (final Object pid: gridOrders.getContainerDataSource()
                .getContainerPropertyIds()) {
			HeaderCell cell = filterRow.getCell(pid);
			
			// Have an input field to use for filter
			TextField filterField = new TextField();
			filterField.setWidth(100, Unit.PERCENTAGE);
			//filterField.setColumns(8);
			
			filterField.addTextChangeListener(new TextChangeListener() {

				private static final long serialVersionUID = 1L;

				public void textChange(TextChangeEvent event) {
					containerOrders.removeContainerFilters(pid);
					
					if(!event.getText().isEmpty()){
						containerOrders.addContainerFilter(new SimpleStringFilter(pid, event.getText(), Boolean.TRUE, Boolean.FALSE));
					}
				}
			});

			
			/*// Update filter When the filter input is changed
			filterField.addTextChangeListener(change -> {
			   // Can't modify filters so need to replace
			   container.removeContainerFilters(pid);
			
			   // (Re)create the filter if necessary
			   if (! change.getText().isEmpty())
			       container.addContainerFilter(
			           new SimpleStringFilter(pid,
			               change.getText(), true, false));
			});*/
			cell.setComponent(filterField);
			}
		
	}

	public TextField getTxtOrderLabel() {
		return txtOrderLabel;
	}
}
