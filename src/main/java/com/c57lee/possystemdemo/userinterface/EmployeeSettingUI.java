package com.c57lee.possystemdemo.userinterface;

import com.c57lee.possystemdemo.controller.EmployeeSettingController;
import com.c57lee.possystemdemo.obj.Employee;
import com.c57lee.possystemdemo.userinterface.component.DatePickerCell;
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
import javafx.util.Callback;

import java.sql.Date;
import java.time.LocalDate;

public class EmployeeSettingUI {

    Stage stage;
    Scene scene;
    Group root;
    EmployeeSettingController controller;
    TableView employeeTable;


    public EmployeeSettingUI(Stage stage){
        this.stage = stage;
        root = new Group();


        controller = new EmployeeSettingController(this);

        initialize();
        populateTable();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void initialize(){
        stage.setTitle("Employee Setting");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

            }
        });

        Label label = new Label("Employee Setting");
        label.setFont(new Font("Arial",30));

        employeeTable = new TableView();
        employeeTable.setEditable(true);


        TableColumn<Employee,Long> employeeIDTableColumn = new TableColumn<>("ID");
        employeeIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        employeeIDTableColumn.setEditable(false);

        TableColumn<Employee,String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeFname"));
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employee, String> e) {
                Employee changed = (Employee) e.getTableView().getItems().get(e.getTablePosition().getRow());
                changed.setEmployeeFname(e.getNewValue());
                controller.updateEmployee(changed);
            }
        });

        TableColumn<Employee,String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeLname"));
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employee, String> e) {
                Employee changed = (Employee) e.getTableView().getItems().get(e.getTablePosition().getRow());
                changed.setEmployeeLname(e.getNewValue());
                controller.updateEmployee(changed);
            }
        });


        TableColumn<Employee, Date> dobColumn = new TableColumn<>("Date of Birth");
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
        dobColumn.setCellFactory(new Callback<TableColumn<Employee, Date>, TableCell<Employee, Date>>() {
            @Override
            public TableCell<Employee, Date> call(TableColumn<Employee, Date> param) {
                return new DatePickerCell<>(param);
            }
        });
        dobColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employee, Date> e) {
                Employee changed = (Employee) e.getTableView().getItems().get(e.getTablePosition().getRow());
                changed.setDob(e.getNewValue());
                controller.updateEmployee(changed);
            }
        });

        TableColumn<Employee, Date> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startDateColumn.setCellFactory(new Callback<TableColumn<Employee, Date>, TableCell<Employee, Date>>() {
            @Override
            public TableCell<Employee, Date> call(TableColumn<Employee, Date> param) {
                return new DatePickerCell<>(param);
            }
        });
        startDateColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employee, Date> e) {
                Employee changed = (Employee) e.getTableView().getItems().get(e.getTablePosition().getRow());
                changed.setStartDate(e.getNewValue());
                controller.updateEmployee(changed);
            }
        });

        TableColumn<Employee,Date> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        endDateColumn.setCellFactory(new Callback<TableColumn<Employee, Date>, TableCell<Employee, Date>>() {
            @Override
            public TableCell<Employee, Date> call(TableColumn<Employee, Date> param) {
                return new DatePickerCell<>(param);
            }
        });
        endDateColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employee, Date> e) {
                Employee changed = (Employee) e.getTableView().getItems().get(e.getTablePosition().getRow());
                changed.setEndDate(e.getNewValue());
                controller.updateEmployee(changed);
            }
        });


        TableColumn<Employee,Integer> socialSecurityColumn = new TableColumn<>("SSN");
        socialSecurityColumn.setCellValueFactory(new PropertyValueFactory<>("socialSecurity"));

        employeeTable.getColumns().addAll(employeeIDTableColumn,firstNameColumn,lastNameColumn,dobColumn,startDateColumn,endDateColumn,socialSecurityColumn);


        GridPane gridPane = new GridPane();
        gridPane.setHgap(2);

        Label idLabel = new Label("ID");
        gridPane.add(idLabel,0,0);
        TextField idTextField = new TextField();
        idTextField.setPromptText("ID");
        idTextField.setMaxWidth(employeeIDTableColumn.getPrefWidth());
        idTextField.setTextFormatter(new TextFormatter<String>(change->{
            String input = change.getText();
            if (input.matches("[0-9]+")) {
                return change;
            }
            return null;
        }));
        gridPane.add(idTextField,0,1);


        Label firstNameLabel = new Label("First Name");
        gridPane.add(firstNameLabel,1,0);
        TextField firstNameTextField = new TextField();
        firstNameTextField.setPromptText("First Name");
        firstNameTextField.setMaxWidth(firstNameColumn.getPrefWidth());
        gridPane.add(firstNameTextField,1,1);

        Label lastNameLabel = new Label("Last Name");
        gridPane.add(lastNameLabel,2,0);
        TextField lastNameTextField = new TextField();
        lastNameTextField.setPromptText("Last Name");
        lastNameTextField.setMaxWidth(lastNameTextField.getPrefWidth());
        gridPane.add(lastNameTextField,2,1);

        Label dobLabel = new Label("Date of Birth");
        gridPane.add(dobLabel,3,0);
        DatePicker dobDatePicker = new DatePicker();
        dobDatePicker.setMaxWidth(100);
        gridPane.add(dobDatePicker,3,1);

        Label startDateLabel = new Label("Start Date");
        gridPane.add(startDateLabel,4,0);
        DatePicker startDatePicker = new DatePicker(LocalDate.now());
        startDatePicker.setMaxWidth(100);
        gridPane.add(startDatePicker,4,1);

        Label endDateLabel = new Label("End Date");
        gridPane.add(endDateLabel,5,0);
        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setMaxWidth(100);
        gridPane.add(endDatePicker,5,1);

        Label ssnLabel = new Label("SSN");
        gridPane.add(ssnLabel,6,0);
        TextField ssnTextField = new TextField();
        ssnTextField.setPromptText("SSN");
        ssnTextField.setMaxWidth(ssnTextField.getPrefWidth());
        ssnTextField.setTextFormatter(new TextFormatter<String>(change->{
            String input = change.getText();
            if (input.matches("[0-9]+")) {
                return change;
            }
            return null;
        }));
        gridPane.add(ssnTextField,6,1);


        Label errorLabel = new Label();
        errorLabel.setFont(new Font("Arial",15));
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);
        gridPane.add(errorLabel,0,2,7,1);

        Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (idTextField.getText().isBlank()){
                    errorLabel.setText("ID cannot be empty.");
                    errorLabel.setVisible(true);
                } else {
                    errorLabel.setVisible(false);
                    Employee newEmployee = new Employee();
                    newEmployee.setEmployeeID(Long.parseLong(idTextField.getText()));
                    newEmployee.setEmployeeFname(firstNameTextField.getText());
                    newEmployee.setEmployeeLname(lastNameTextField.getText());
                    if (dobDatePicker.getValue()!=null) {
                        newEmployee.setDob(Date.valueOf(dobDatePicker.getValue()));
                    }
                    if (startDatePicker.getValue()!=null){
                        newEmployee.setStartDate(Date.valueOf(startDatePicker.getValue()));
                    }
                    if (endDatePicker.getValue()!=null) {
                        newEmployee.setEndDate(Date.valueOf(endDatePicker.getValue()));
                    }
                    if (!ssnTextField.getText().isBlank()) {
                        newEmployee.setSocialSecurity(Integer.parseInt(ssnTextField.getText()));
                    }
                    employeeTable.getItems().add(newEmployee);
                    controller.addEmployee(newEmployee);
                }
            }
        });
        gridPane.add(addButton,7,1);

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Employee selected = (Employee) employeeTable.getSelectionModel().getSelectedItem();
                if(selected!=null){
                    controller.deleteEmployee(selected);
                    employeeTable.getItems().remove(selected);

                    idTextField.clear();
                    firstNameTextField.clear();
                    lastNameTextField.clear();
                    ssnTextField.clear();
                    startDatePicker.setValue(LocalDate.now());
                    endDatePicker.setValue(null);
                    dobDatePicker.setValue(null);

                    employeeTable.getSelectionModel().clearSelection();

                    removeButton.setVisible(false);
                    addButton.setVisible(true);
                }

            }
        });
        removeButton.setVisible(false);
        gridPane.add(removeButton,7,1);

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                idTextField.clear();
                firstNameTextField.clear();
                lastNameTextField.clear();
                ssnTextField.clear();
                startDatePicker.setValue(LocalDate.now());
                endDatePicker.setValue(null);
                dobDatePicker.setValue(null);

                employeeTable.getSelectionModel().clearSelection();

                removeButton.setVisible(false);
                addButton.setVisible(true);
            }
        });
        gridPane.add(resetButton,8,1);

        employeeTable.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            Employee selected = (Employee) observable.getValue();
            if (selected!= null) {
                idTextField.setText(String.valueOf(selected.getEmployeeID()));
                firstNameTextField.setText(selected.getEmployeeFname());
                lastNameTextField.setText(selected.getEmployeeLname());
                ssnTextField.setText(selected.getSocialSecurity() + "");
                if (selected.getStartDate() != null) {
                    startDatePicker.setValue(selected.getStartDate().toLocalDate());
                } else {
                    startDatePicker.setValue(null);
                }
                if (selected.getDob() != null) {
                    dobDatePicker.setValue(selected.getDob().toLocalDate());
                } else {
                    dobDatePicker.setValue(null);
                }
                if (selected.getEndDate() != null) {
                    endDatePicker.setValue(selected.getEndDate().toLocalDate());
                } else {
                    endDatePicker.setValue(null);
                }

                addButton.setVisible(false);
                removeButton.setVisible(true);
            }

            });

        VBox vBox = new VBox(label,employeeTable,gridPane);
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(5,10,5,10));

        root.getChildren().addAll(vBox);
    }


    public void populateTable(){
        employeeTable.getItems().addAll(controller.getAllEmployee());
    }
}
