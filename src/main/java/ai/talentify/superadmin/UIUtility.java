package ai.talentify.superadmin;

import java.util.Enumeration;

import com.vaadin.server.VaadinService;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.UI;

public class UIUtility {

	public static boolean userAutheticated(UI superAdminUserInterface, WrappedSession  session) {
		try {
			return ((Boolean) VaadinService.getCurrentRequest().getWrappedSession().getAttribute("isLoggedIn") == true);
		} catch (Exception e) {
			return false;
		}
	}

	public static void printSession(WrappedSession  session) {
		System.out.println(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("isLoggedIn"));
		/*Enumeration e = (Enumeration) (session.getAttributeNames());

		while (e.hasMoreElements()) {
			Object tring;
			if ((tring = e.nextElement()) != null) {
				System.err.println("key-> " + tring + " value-> " + session.getAttribute(tring.toString()));

			}
		}*/
	}
}