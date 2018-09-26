package ai.talentify.superaAdmin.ui.elements;


import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;

import ai.talentify.superaAdmin.ui.service.Dashboard;
import ai.talentify.superadmin.ComplexObjectUtils;

public class NavBar{
    private static NavBar single_instance = null;
    MenuBar navbar = new MenuBar(); 

   
   
	public MenuBar getMenuBar(UI superAdminUserInterface) {
		// This is the logic where we will add different items to menu bar 
    	
	    
        // A top-level menu item that opens a submenu
		MenuItem dashboard = navbar.addItem("Home", null, new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
			    new Dashboard().loadDashBoard(superAdminUserInterface);
			}

		});
    	MenuItem courses = navbar.addItem("Courses", null, null);
    	MenuItem licensemanagement = navbar.addItem("License Management", null, null);
    	MenuItem classroommanagement = navbar.addItem("ClassRoom Management", null, null);
    	MenuItem assetsmanagement = navbar.addItem("Assets Management", null, null);
    	MenuItem usersmanagement = navbar.addItem("Users Management", null, null);
    	MenuItem talentifyusers = navbar.addItem("Talentify Users", null, null);
    	
    	//System.out.println("40 Comlex Object -> "+ superAdminUserInterface.getSession().getCurrent().getSession().getAttribute("complexObject"));
    	try {
			MenuItem profile = navbar.addItem("Welcome " +  new ComplexObjectUtils(superAdminUserInterface.getSession().getCurrent().getSession().getAttribute("complexObject").toString()).getName(), null, null);
			profile.addItem("Profile", null, null);
			profile.addItem("Logout", null, new Command() {
				  @Override
				  public void menuSelected(MenuItem selectedItem) {
				    //System.out.println("You clicked on "+selectedItem.getText());
				    superAdminUserInterface.getSession().getCurrent().getSession().invalidate();
				    superAdminUserInterface.getUI().getPage().setLocation("http://localhost:8080/superadmin/");

				    }
				});
		} catch (Exception e) {
			superAdminUserInterface.getSession().getCurrent().getSession().invalidate();
		    superAdminUserInterface.getUI().getPage().setLocation("http://localhost:8080/superadmin/");
		}
    	return navbar;
	} 
	
	 
   
}