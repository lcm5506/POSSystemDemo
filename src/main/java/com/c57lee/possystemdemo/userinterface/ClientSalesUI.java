package com.c57lee.possystemdemo.userinterface;

import com.c57lee.possystemdemo.controller.MenuController;
import com.c57lee.possystemdemo.logic.ClientSalesLogic;
import com.c57lee.possystemdemo.obj.Menu;
import com.c57lee.possystemdemo.obj.*;
import com.c57lee.possystemdemo.userinterface.component.MenuButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class ClientSalesUI {

    private final Stage stage;
    private final BorderPane root;
    private final String locationName;
    private Location location;
    private List<Menu> menuList;
    private GridPane menuButtonGridPane;
    private List<Item> tempItemsAdded;
    private TableView tableView;
    private ClientSalesLogic clientSalesLogic;
    private Employee employeeLoggedIn;


    private final double WINDOW_WIDTH = 800;
    private final double WINDOW_HEIGHT = 800;





    public ClientSalesUI(Stage stage, String locationName){

        this.stage = stage;
        this.root = new BorderPane();
        this.locationName = locationName;
        this.clientSalesLogic = new ClientSalesLogic();
        tempItemsAdded = new ArrayList<>();
        System.out.println("INITIALIZING UI");

        initializeUserInterface();
    }

    private void initializeUserInterface() {
        checkEmployeeLoggedIn();
        findAndSetLocation();
        if (!findAndSetClientWithLocation()){
            checkIfClientAvailable();
        }
        findAndSetMenu();
        drawBackground(root);
        drawClientInfoPane(root);
        drawClientSalesTableView(root);
        drawMenuButtonPane(root);
        drawFunctionButtonPane(root);
        stage.show();

    }



    private void drawBackground(BorderPane root) {
        System.out.println("DRAWING BACKGROUND");
        Scene scene = new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
    }

    private void drawFunctionButtonPane(BorderPane root) {
        GridPane functionButtonGridPane = new GridPane();
        functionButtonGridPane.setPrefSize(600,200);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                clientSalesLogic.addItems(tempItemsAdded);
                tempItemsAdded.clear();
            }
        });
        functionButtonGridPane.add(submitButton,0,0);

        Button voidButton = new Button("Void");
        voidButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    Item selectedItem = (Item)tableView.getSelectionModel().getSelectedItem();
                    if (tempItemsAdded.contains(selectedItem)){
                        tempItemsAdded.remove(selectedItem);
                    } else {
                        clientSalesLogic.removeItemFromClient(selectedItem);
                    }
                    tableView.getItems().remove(selectedItem);
                }
            }
        });
        functionButtonGridPane.add(voidButton,1,0);

        Button changeEmployeeButton = new Button("Change Employee");
        changeEmployeeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clientSalesLogic.changeEmployee(employeeIDDialog());
            }
        });
        functionButtonGridPane.add(changeEmployeeButton,2,0);


        root.setBottom(functionButtonGridPane);
    }

    private void drawMenuButtonPane(BorderPane root) {
        menuButtonGridPane = new GridPane();
        menuButtonGridPane.setPrefSize(200,500);
        for (int i=0; i < menuList.size(); i++){
            Menu menu = menuList.get(i);
            MenuButton button = new MenuButton(menu);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Menu clickedMenu = ((MenuButton)e.getSource()).getMenu();
                    Item clickedItem = new Item(clickedMenu,clientSalesLogic.getCurrentClient(),employeeLoggedIn);
                    tableView.getItems().add(clickedItem);
                    tempItemsAdded.add(clickedItem);
                }
            });
            menuButtonGridPane.add(button,i%2,i/2);
        }
        root.setRight(menuButtonGridPane);
    }

    private void drawClientSalesTableView(BorderPane root) {
        tableView = new TableView();

        tableView.setEditable(false);



        TableColumn<Item,String> itemNameColumn = new TableColumn<>("Name");
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<Item,Double> itemPriceColumn = new TableColumn<>("Price");
        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Item,String> itemCategoryColumn = new TableColumn<>("Category");
        itemCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Item,String> itemNoteColumn = new TableColumn<>("Note");
        itemNoteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));

        tableView.getColumns().add(itemNameColumn);
        tableView.getColumns().add(itemPriceColumn);
        tableView.getColumns().add(itemCategoryColumn);
        tableView.getColumns().add(itemNoteColumn);

        List<Item> allItemList = clientSalesLogic.getCurrentClient().getItems();
        for (Item i: allItemList){
            tableView.getItems().add(i);
        }

        root.setCenter(tableView);
    }

    private void drawClientInfoPane(BorderPane root) {
        System.out.println("DRAWING CLIENT INFO PANE");
        GridPane labelGrid = new GridPane();
        //labelGrid.setPrefHeight(100.0);
        labelGrid.setPrefWidth(WINDOW_WIDTH);
        //labelGrid.setBackground(Background.fill(Color.BLACK));

        labelGrid.setHgap(30);
        labelGrid.setVgap(15);
        labelGrid.setGridLinesVisible(false);
        labelGrid.setPadding(new Insets(10));
        root.setTop(labelGrid);

        System.out.println("INITIALIZING LABELS");
        Label client_idLabel = new Label("client id: " + clientSalesLogic.getCurrentClient().getClientID());
        Label employee_idLabel = new Label("employee: "+ clientSalesLogic.getCurrentClient().getEmployee().getEmployeeLname());
        Label location_idLabel = new Label("location: "+ clientSalesLogic.getCurrentClient().getLocation().getLocationName());
        Label start_timeLabel = new Label("start time: " + new SimpleDateFormat("HH:MM").format(clientSalesLogic.getCurrentClient().getStartTime()));
        //Label end_timeLabel = new Label("end time: "+ new SimpleDateFormat("HH:MM").format(Optional.ofNullable(clientSalesLogic.getCurrentClient().getEndTime()).orElse(Timestamp.valueOf())));
        Label head_countLabel = new Label("head count: "+ clientSalesLogic.getCurrentClient().getHeadCount());
        Label employeeLoggedInLabel = new Label("you are: "+employeeLoggedIn.getEmployeeLname());


        System.out.println("Styling labels");
        labelStyling(client_idLabel);
        labelStyling(employee_idLabel);
        labelStyling(location_idLabel);
        labelStyling(start_timeLabel);
        //labelStyling(end_timeLabel);
        labelStyling(head_countLabel);

        System.out.println("Adding labels to grid pane");
        labelGrid.add(client_idLabel,0,0);
        labelGrid.add(employee_idLabel,1,0);
        labelGrid.add(location_idLabel,2,0);
        labelGrid.add(start_timeLabel,3,0);
        labelGrid.add(head_countLabel,0,1);
        labelGrid.add(employeeLoggedInLabel,1,1);
        //labelGrid.add(end_timeLabel,3,1);

    }

    private void labelStyling(Label label) {
        label.setFont(new Font("Arial",20));
        label.setBackground(Background.EMPTY);
        //label.setAlignment(Pos.BASELINE_LEFT);
    }

    private boolean findAndSetClientWithLocation(){
        List<Client> clientList = clientSalesLogic.getCurrentLocation().getClients();
        if (clientList.isEmpty()){
            return false;
        }
        for (int i = clientList.size()-1; i>=0;i--){
            Client client = clientList.get(i);
            if (client.getEndTime()==null) {
                clientSalesLogic.setClient(client);
                return true;
            }
        }
        return false;
    }

    private void checkEmployeeLoggedIn(){
        this.employeeLoggedIn = employeeIDDialog();
    }

    private void findAndSetLocation(){
        clientSalesLogic.setLocationByName(locationName);
    }

    private void checkIfClientAvailable() {
        clientSalesLogic.createNewClient(employeeLoggedIn,clientSalesLogic.getCurrentLocation(),headCountDialog());
    }

    private Employee employeeIDDialog(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Your Employee ID");
        dialog.setResizable(false);
        dialog.setHeaderText("Enter Your Employee ID:");
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String input = change.getControlNewText();
            return (input.matches("[0-9]{0,6}")) ? change:null;
        };
        dialog.getEditor().setTextFormatter(new TextFormatter<String>(integerFilter));
        long employeeID = Long.parseLong(dialog.showAndWait().orElse("0"));
        Employee found = clientSalesLogic.getEmployeeByID(employeeID);
        if (found != null)
            return found;
        return employeeIDDialog();
    }

    private int headCountDialog(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter head count.");
        dialog.setResizable(false);
        dialog.setHeaderText("Enter head count:");
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String input = change.getControlNewText();
            return (input.matches("[0-9]{0,3}")) ? change:null;
        };
        dialog.getEditor().setTextFormatter(new TextFormatter<String>(integerFilter));
        int headCount = Integer.parseInt(dialog.showAndWait().orElse("0"));
        return headCount;
    }

    private void findAndSetMenu(){
        MenuController mc = new MenuController();
        this.menuList = mc.getMenuList();
    }


    public void setClientLogic(ClientSalesLogic clientSalesLogic){
        this.clientSalesLogic = clientSalesLogic;
    }
}
