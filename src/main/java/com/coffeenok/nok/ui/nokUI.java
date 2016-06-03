package com.coffeenok.nok.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.WrappedHttpSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.UI;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@PreserveOnRefresh
public class nokUI extends UI {

	private static final long serialVersionUID = 1L;
	private ApplicationContext applicationContext;

	@Override
    protected void init(VaadinRequest request) {
        WrappedSession session = request.getWrappedSession();
        HttpSession httpSession = ((WrappedHttpSession) session).getHttpSession();
        ServletContext servletContext = httpSession.getServletContext();
        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

        
        //OrderDao orderDAO = applicationContext.getBean(OrderDao.class);
        //orderDAO.createDbTable();
        
        Navigator navigator = new Navigator(this, this);

        navigator.addView("login", LoginView.class);
        
        navigator.addView("orders", OrdersView.class);
        
        navigator.addView("user", UserView.class);

        navigator.navigateTo("login");
        setNavigator(navigator);
    }
    
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
