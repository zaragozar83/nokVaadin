package com.coffeenok.nok.ui;

import com.vaadin.ui.*;
import org.springframework.context.ApplicationContext;

public class LoginForm extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField txtLogin = new TextField("Login: ");
    private PasswordField txtPassword = new PasswordField("Password: ");
    private Button btnLogin = new Button("Login");

    public LoginForm() {
    	setMargin(Boolean.TRUE);
    	setSpacing(Boolean.TRUE);
        addComponent(txtLogin);
        addComponent(txtPassword);
        addComponent(btnLogin);

        LoginFormListener loginFormListener = getLoginFormListener();

        btnLogin.addClickListener(loginFormListener);
    }

    public LoginFormListener getLoginFormListener() {
        nokUI ui = (nokUI) UI.getCurrent();
        ApplicationContext context = ui.getApplicationContext();
        return context.getBean(LoginFormListener.class);
    }

    public TextField getTxtLogin() {
        return txtLogin;
    }

    public PasswordField getTxtPassword() {
        return txtPassword;
    }
}
