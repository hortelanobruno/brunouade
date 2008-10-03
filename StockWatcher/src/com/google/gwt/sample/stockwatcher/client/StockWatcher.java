package com.google.gwt.sample.stockwatcher.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Random;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;

import java.util.Date;


public class StockWatcher implements EntryPoint {

  private VerticalPanel mainPanel = new VerticalPanel();   
  private VerticalPanel mainPanel2 = new VerticalPanel();  
  private FlexTable stocksFlexTable = new FlexTable();
  private HorizontalPanel addPanel = new HorizontalPanel();
  private TextBox newSymbolTextBox = new TextBox();
  private Button addButton = new Button("Add");
  private Label lastUpdatedLabel = new Label();

  private ArrayList<String> stocks = new ArrayList<String>();
  
  private static final int REFRESH_INTERVAL = 5000; // ms
  
  public void onModuleLoad() {
    // set up stock list table
    stocksFlexTable.setText(0, 0, "Symbol");
    stocksFlexTable.setText(0, 1, "Price");
    stocksFlexTable.setText(0, 2, "Change");
    stocksFlexTable.setText(0, 3, "Remove");

    stocksFlexTable.setCellPadding(5);
    stocksFlexTable.addStyleName("watchList");
    stocksFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");
    stocksFlexTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
    stocksFlexTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
    stocksFlexTable.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");
    
    
 // set up event listeners for adding a new stock
    addButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        addStock();
      }
    });
    
    newSymbolTextBox.addKeyboardListener(new KeyboardListenerAdapter() {
    	  @Override
    	  public void onKeyDown(Widget sender, char keyCode, int modifiers) {
    		  if (keyCode == KEY_ENTER) {
    	      addStock();
    	    }
    	  }
    	});
    
    // assemble Add Stock panel
    addPanel.add(newSymbolTextBox);
    addPanel.add(addButton);
    addPanel.addStyleName("addPanel");
    
   /* // assemble main panel
    mainPanel.add(stocksFlexTable);
    mainPanel.add(addPanel);
    mainPanel.add(lastUpdatedLabel);*/
    
    Label lastUpdatedLabel2 = new Label("Brunoo");
    CheckBox checkBox = new CheckBox("Capo?");
    mainPanel.add(lastUpdatedLabel2);
    mainPanel.add(checkBox);
    
    
 // assemble main panel
    mainPanel2.add(stocksFlexTable);
    mainPanel2.add(addPanel);
    mainPanel2.add(lastUpdatedLabel);
    
    // add the main panel to the HTML element with the id "stockList"
    RootPanel.get("stockList").add(mainPanel);
    RootPanel.get("stockList2").add(mainPanel2);
    
 // setup timer to refresh list automatically
    Timer refreshTimer = new Timer() {
      public void run() {
        refreshWatchList();        
      }
    };
    refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
    
    // move cursor focus to the text box
    newSymbolTextBox.setFocus(true);
  }
  
  private void addStock() {
	  final String symbol = newSymbolTextBox.getText().toUpperCase().trim();
	  newSymbolTextBox.setFocus(true);
	  
	  // symbol must be between 1 and 10 chars that are numbers, letters, or dots
	  if (!symbol.matches("^[0-9a-zA-Z\\.]{1,10}$"))
	  {
	    Window.alert("'" + symbol + "' is not a valid symbol.");
	    newSymbolTextBox.selectAll();
	    return;
	  }
	  
	  newSymbolTextBox.setText("");
	      
	// don't add the stock if it's already in the watch list
	  if (stocks.contains(symbol))
	    return;

	  // add the stock to the list
	  int row = stocksFlexTable.getRowCount();
	  stocks.add(symbol);
	  stocksFlexTable.setText(row, 0, symbol);
	  stocksFlexTable.setWidget(row, 2, new Label());
	  stocksFlexTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
	  stocksFlexTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");
	  stocksFlexTable.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");

	  // add button to remove this stock from the list
	  Button removeStock = new Button("x");
	  removeStock.addStyleDependentName("remove");
	  
	  removeStock.addClickListener(new ClickListener(){
	     public void onClick(Widget sender) {
	       int removedIndex = stocks.indexOf(symbol);
	       stocks.remove(removedIndex);
	       stocksFlexTable.removeRow(removedIndex+1);
	    }      
	  });
	  stocksFlexTable.setWidget(row, 3, removeStock);
	  
	// get stock price
	  refreshWatchList();
	}
  
  private void refreshWatchList() {
	  final double MAX_PRICE = 100.0; // $100.00
	  final double MAX_PRICE_CHANGE = 0.02; // +/- 2%

	  StockPrice[] prices = new StockPrice[stocks.size()];
	  for (int i=0; i<stocks.size(); i++) {
	    double price = Random.nextDouble() * MAX_PRICE;
	    double change = price * MAX_PRICE_CHANGE * (Random.nextDouble() * 2.0 - 1.0);
	      
	    prices[i] = new StockPrice((String)stocks.get(i), price, change);
	  }
	  
	  updateTable(prices);
	}
  
  private void updateTable(StockPrice[] prices) {
	  for (int i=0; i<prices.length; i++) {
	    updateTable(prices[i]);
	  }
	  
	  // change the last update timestamp
	  lastUpdatedLabel.setText("Last update : " + 
	      DateTimeFormat.getMediumDateTimeFormat().format(new Date()));
	}

	private void updateTable(StockPrice price) {
		// make sure stock is still in our watch list
		  if (!stocks.contains(price.getSymbol()))
		    return;
		  
		  int row = stocks.indexOf(price.getSymbol()) + 1;

		  // apply nice formatting to price and change
		  String priceText = NumberFormat.getFormat("#,##0.00").format(price.getPrice());
		  NumberFormat changeFormat = NumberFormat.getFormat("+#,##0.00;-#,##0.00");
		  String changeText = changeFormat.format(price.getChange());
		  String changePercentText = changeFormat.format(price.getChangePercent());

		  // update the watch list with the new values
		  stocksFlexTable.setText(row, 1, priceText);
		  Label changeWidget = (Label)stocksFlexTable.getWidget(row, 2);
		  changeWidget.setText(changeText + " (" + changePercentText + "%)");

		  String changeStyleName = "noChange";
		  if (price.getChangePercent() < -0.1f) {
		    changeStyleName = "negativeChange";
		  }
		  else if (price.getChangePercent() > 0.1f) {
		    changeStyleName = "positiveChange";
		  }
		  
		  changeWidget.setStyleName(changeStyleName);
	}
}
  