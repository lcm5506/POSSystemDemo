package com.c57lee.possystemdemo.userinterface;

import com.c57lee.possystemdemo.controller.MenuSettingController;
import com.c57lee.possystemdemo.obj.Menu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.converter.DoubleStringConverter;

import java.text.NumberFormat;

public class MenuSettingUI {

    Stage stage;
    Scene scene;
    Group root;
    MenuSettingController controller;
    TableView menuTable;


    public MenuSettingUI(Stage stage){
        this.stage = stage;
        root = new Group();


        controller = new MenuSettingController(this);

        initialize();
        populateTable();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void initialize(){
        stage.setTitle("Menu Setting");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

            }
        });

        Label label = new Label("Menu Setting");
        label.setFont(new Font("Arial",30));

        menuTable = new TableView();
        menuTable.setEditable(true);



        TableColumn<Menu,Long> menuIDTableColumn = new TableColumn<>("ID");
        menuIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("menuID"));
        menuIDTableColumn.setEditable(false);

        TableColumn<Menu,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("menuName"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Menu, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Menu, String> e) {
                Menu changed = (Menu) e.getTableView().getItems().get(e.getTablePosition().getRow());
                changed.setMenuName(e.getNewValue());
                controller.updateMenu(changed);
            }
        });

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        TableColumn<Menu,Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Menu, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Menu, Double> e) {
                Menu changed = (Menu) e.getTableView().getItems().get(e.getTablePosition().getRow());
                changed.setPrice(e.getNewValue());
                controller.updateMenu(changed);
            }
        });


        TableColumn<Menu,String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Menu, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Menu, String> e) {
                Menu changed = (Menu) e.getTableView().getItems().get(e.getTablePosition().getRow());
                changed.setCategory(e.getNewValue());
                controller.updateMenu(changed);
            }
        });

        menuTable.getColumns().addAll(menuIDTableColumn,nameColumn,priceColumn,categoryColumn);


        GridPane gridPane = new GridPane();
        gridPane.setHgap(2);

        Label idLabel = new Label("ID");
        gridPane.add(idLabel,0,0);
        TextField idTextField = new TextField();
        idTextField.setPromptText("ID");
        idTextField.setMaxWidth(menuIDTableColumn.getPrefWidth());
//        idTextField.setTextFormatter(new TextFormatter<String>(change->{
//            String input = change.getText();
//            if (input.matches("[0-9]+")) {
//                return change;
//            }
//            return null;
//        }));
        gridPane.add(idTextField,0,1);


        Label nameLabel = new Label("Name");
        gridPane.add(nameLabel,1,0);
        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Name");
        nameTextField.setMaxWidth(nameColumn.getPrefWidth());
        gridPane.add(nameTextField,1,1);

        Label priceLabel = new Label("Price");
        gridPane.add(priceLabel,2,0);
        TextField priceTextField = new TextField();
        priceTextField.setPromptText("Price");
        priceTextField.setMaxWidth(priceTextField.getPrefWidth());
//        priceTextField.setTextFormatter(new TextFormatter<String>(change->{
//            String input = change.getText();
//            if (input.matches("[0-9.]+")) {
//                return change;
//            }
//            return null;
//        }));
        gridPane.add(priceTextField,2,1);

        Label categoryLabel = new Label("Category");
        gridPane.add(categoryLabel,3,0);
        TextField categoryTextField = new TextField();
        categoryTextField.setPromptText("Category");
        categoryTextField.setMaxWidth(categoryTextField.getPrefWidth());
        gridPane.add(categoryTextField,3,1);


        Label errorLabel = new Label();
        errorLabel.setFont(new Font("Arial",15));
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);
        gridPane.add(errorLabel,0,2,4,1);

        Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (idTextField.getText().isBlank()){
                    errorLabel.setText("ID cannot be empty.");
                    errorLabel.setVisible(true);
                } else if (!idTextField.getText().matches("^[0-9]+$")) {
                    errorLabel.setText("ID must contain only numbers 0 to 9.");
                    errorLabel.setVisible(true);
                } else if (nameTextField.getText().isBlank()) {
                    errorLabel.setText("Name cannot be empty.");
                    errorLabel.setVisible(true);
                } else if (priceTextField.getText().isBlank()) {
                    errorLabel.setText("Price cannot be empty.");
                    errorLabel.setVisible(true);
                } else if (!priceTextField.getText().matches("[$€£¥]?\\x20?\\d+(?:[.,]\\d{0,2})?(?:[a-zA-Z]{3})?")) {
                    errorLabel.setText("Price is invalid.");
                    errorLabel.setVisible(true);
                } else {
                    errorLabel.setVisible(false);
                    Menu newMenu = new Menu();
                    newMenu.setMenuID(Long.parseLong(idTextField.getText()));
                    newMenu.setMenuName(nameTextField.getText());
                    if (!priceTextField.getText().isBlank()) {
                        newMenu.setPrice(Double.parseDouble(priceTextField.getText()));
                    }
                    newMenu.setCategory(categoryTextField.getText());

                    menuTable.getItems().add(newMenu);
                    controller.addMenu(newMenu);
                }
            }
        });
        gridPane.add(addButton,4,1);

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Menu selectedMenu = (Menu)menuTable.getSelectionModel().getSelectedItem();
                menuTable.getItems().remove(selectedMenu);
                controller.removeMenu(selectedMenu);
                menuTable.getSelectionModel().clearSelection();
                idTextField.clear();
                nameTextField.clear();
                priceTextField.clear();
                categoryTextField.clear();
                removeButton.setVisible(false);
                addButton.setVisible(true);
            }
        });
        gridPane.add(removeButton,4,1);
        removeButton.setVisible(false);
        addButton.prefWidthProperty().bind(removeButton.prefWidthProperty());

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menuTable.getSelectionModel().clearSelection();
                idTextField.clear();
                nameTextField.clear();
                priceTextField.clear();
                categoryTextField.clear();
                removeButton.setVisible(false);
                addButton.setVisible(true);
            }
        });
        gridPane.add(resetButton,5,1);

        menuTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Menu selectedMenu = (Menu)observable.getValue();
                if (selectedMenu!= null) {
                    idTextField.setText(String.valueOf(selectedMenu.getMenuID()));
                    nameTextField.setText(selectedMenu.getMenuName());
                    priceTextField.setText(selectedMenu.getPrice() + "");
                    categoryTextField.setText(selectedMenu.getCategory());
                    addButton.setVisible(false);
                    removeButton.setVisible(true);

                }
            }
        });

        VBox vBox = new VBox(label, menuTable,gridPane);
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(5,10,5,10));

        root.getChildren().addAll(vBox);
    }

    public void populateTable(){
        menuTable.getItems().addAll(controller.getAllMenu());
    }


}
