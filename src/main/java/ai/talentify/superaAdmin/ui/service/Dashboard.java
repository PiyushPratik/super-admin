package ai.talentify.superaAdmin.ui.service;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.lotsabackscatter.masonry.ClickListener;
import com.github.lotsabackscatter.masonry.Masonry;
import com.vaadin.shared.ui.dd.HorizontalDropLocation;
import com.vaadin.shared.ui.orderedlayout.HorizontalLayoutState;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ai.talentify.superaAdmin.ui.elements.NavBar;
import ai.talentify.superadmin.RestUtility;

public class Dashboard {
	public void loadDashBoard(UI superAdminUserInterface) {
		HorizontalLayout content = new HorizontalLayout();
		content.setSizeFull(); // Use entire window
		superAdminUserInterface.setContent(content); // Attach to the UI

		MenuBar barmenu = new NavBar().getMenuBar(superAdminUserInterface);
		// Add some component
		Masonry masonry = new Masonry();

		// now lets hit a service to get a list of oragnziations
		RestUtility utils = new RestUtility();
		JSONArray items = utils.getRequest("http://192.168.1.31:8080/istar/rest/organization/getAllOrgs")
				.getJSONArray("data");
		for (Object org : items) {
			JSONObject organization = (JSONObject) org;

			int id = organization.getInt("id");
			String name = organization.getString("name");
			String description="";
			if(organization.has("description")) {
				 description = organization.getString("description");
			}
			
			String image = organization.getString("image");
			masonry.addCard(name,description,image,name, new ClickListener() {
				
				@Override
				public void onClick() {
					// TODO Auto-generated method stub
					
				}
			});
		}

		content.addComponent(barmenu);
		content.addComponent(masonry);

	}
}
