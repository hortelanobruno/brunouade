package com.bruno.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Index implements EntryPoint {

	private Button addButton = new Button("Entrar");
    
	
	public void onModuleLoad() {
		// Create a table to layout the form options
	    FlexTable layout = new FlexTable();
	    layout.setCellSpacing(8);
	    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

	    // Add a title to the form
	    layout.setHTML(0, 0, "Login");
	    cellFormatter.setColSpan(0, 0, 2);
	    cellFormatter.setHorizontalAlignment(0, 0,
	        HasHorizontalAlignment.ALIGN_CENTER);

	    
	    addButton.addClickListener(new ClickListener() {
	        public void onClick(Widget sender) {
	          login();
	        }
	    });
	    
	    
	    // Add some standard form options
	    layout.setHTML(1, 0, "Usuario");
	    layout.setWidget(1, 1, new TextBox());
	    layout.setHTML(2, 0, "Password");
	    layout.setWidget(2, 1, new TextBox());
	    layout.setWidget(3, 1, addButton);

	    
	    
	    
	    // Wrap the content in a DecoratorPanel
	    DecoratorPanel decPanel = new DecoratorPanel();
	    decPanel.setWidget(layout);
		
	    RootPanel.get("login").add(decPanel);
	}
	
	 private void login() {
		 redirect("./Login.html");
	 }

	//redirect the browser to the given url
	 public static native void redirect(String url)/*-{
	       $wnd.location = url;
	   }-*/;

	 
	
}
