package com.coffeenok.nok.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout implements View{

	private static final long serialVersionUID = 1L;

	public LoginView() {
        LoginForm loginForm = new LoginForm();
        addComponent(loginForm);
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
