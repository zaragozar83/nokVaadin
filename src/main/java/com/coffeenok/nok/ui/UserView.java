package com.coffeenok.nok.ui;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class UserView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	/**
     * Constructs an empty VerticalLayout.
     */
    public UserView() {
    	setMargin(Boolean.TRUE);
    	setSpacing(Boolean.TRUE);
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        removeAllComponents();

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println(context.getAuthentication());

        if (authentication != null && authentication.isAuthenticated()) {
            String name = authentication.getName();

            Label labelLogin = new Label("Username: " + name);
            addComponent(labelLogin);

            final Label lblAuthority = new Label();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            for (GrantedAuthority ga : authorities) {
                String authority = ga.getAuthority();
                if ("ADMIN".equals(authority)) {
                	lblAuthority.setValue("Eres un ADMINISTRADOR");
                	addComponent(lblAuthority);
                }else if("ALMACEN".equals(authority)){
                	lblAuthority.setValue("Eres el responsable del ALMACEN!!!");
                	addComponent(lblAuthority);
                }else if("TALLER".equals(authority)){
                	lblAuthority.setValue("Eres responsable del TALLER!!!");
                	addComponent(lblAuthority);
                }else {
                    lblAuthority.setValue("Granted Authority: " + authority);
                    addComponent(lblAuthority);
                }
            }
            
            Button logout = new Button("Logout");
            LogoutListener logoutListener = new LogoutListener();
            
            logout.addClickListener(logoutListener);
            addComponent(logout);
        } else {
            Navigator navigator = UI.getCurrent().getNavigator();
            navigator.navigateTo("login");
        }
    }
}
