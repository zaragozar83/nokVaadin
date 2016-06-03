package com.coffeenok.nok.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.coffeenok.nok.domain.AuthManager;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

@Component
public class LoginFormListener implements Button.ClickListener {

	private static final long serialVersionUID = 1L;
	@Autowired
    private AuthManager authManager;

    public void buttonClick(Button.ClickEvent event) {
        try {
            Button source = event.getButton();
            LoginForm parent = (LoginForm) source.getParent();
            String username = parent.getTxtLogin().getValue();
            String password = parent.getTxtPassword().getValue();

            UsernamePasswordAuthenticationToken request = new UsernamePasswordAuthenticationToken(username, password);

            Authentication result = authManager.authenticate(request);

            SecurityContextHolder.getContext().setAuthentication(result);

            Navigator navigator = UI.getCurrent().getNavigator();
            //navigator.navigateTo("user");
            navigator.navigateTo("orders");

        } catch (AuthenticationException e) {
            Notification.show("Authentication failed: " + e.getMessage(), Type.ERROR_MESSAGE);
        }

    }
}
