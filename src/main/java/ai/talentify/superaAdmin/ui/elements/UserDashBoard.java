package ai.talentify.superaAdmin.ui.elements;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import ai.talentify.superaAdmin.ui.service.Dashboard;
import ai.talentify.superadmin.UIUtility;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Viewport("width=device-width, initial-scale=1")
public class UserDashBoard extends UI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7272054977788281589L;
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		// Most of the pages need to check wether the user is logged if nto redirect hom to login page or show taht specfic page 
		UIUtility.printSession(this.getSession().getCurrent().getSession());
		if (UIUtility.userAutheticated(this, VaadinService.getCurrentRequest().getWrappedSession())) {
			new Dashboard().loadDashBoard(this);
		} else {
			this.getPage().setLocation("http://localhost:8080/superadmin/");
		}
		
	}
	@WebServlet(urlPatterns = "/dashboard", name = "UserDashBoard", asyncSupported = true)
	@VaadinServletConfiguration(ui = UserDashBoard.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
