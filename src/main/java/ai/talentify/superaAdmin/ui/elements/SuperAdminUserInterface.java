package ai.talentify.superaAdmin.ui.elements;

import javax.servlet.annotation.WebServlet;

import org.json.JSONObject;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ai.talentify.suepradmin.restutils.MD5Utils;
import ai.talentify.superaAdmin.ui.service.Dashboard;
import ai.talentify.superadmin.RestUtility;
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
public class SuperAdminUserInterface extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		if (UIUtility.userAutheticated(this, this.getSession().getCurrent().getSession())) {
			new Dashboard().loadDashBoard(this);
		} else {
			
			final TextField name = new TextField();
			name.setCaption("Username");
			final PasswordField password = new PasswordField();
			password.setCaption("Password");
			Button button = new Button("Login");
			button.addClickListener(e -> {
				RestUtility util = new RestUtility();
				JSONObject json = null;
				try {
					json = util.postRequest("https://sales.talentify.in/istar/rest/auth/login",
							"email=" + name.getValue() + "&password=" + MD5Utils.toMd5(password.getValue()));
					JSONObject complexObject = new JSONObject(json);
					// System.out.println("62 -> "+ json);
					// System.out.println("63 -> "+ json);
					this.getSession().getCurrent().getSession().setAttribute("complexObject", json);
					this.getSession().getCurrent().getSession().setAttribute("isLoggedIn", true);
				} catch (Exception e1) {
					layout.addComponent(new Label(""));
				}
				if (json.getInt("statusCode") == 200) {
					layout.addComponent(new Label(json.toString()));
					new Dashboard().loadDashBoard(this);
				} else {
					layout.addComponent(new Label("In valid Username and password"));
				}
			});
			layout.addComponents(name, password, button);
			setContent(layout);
		}
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = SuperAdminUserInterface.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
