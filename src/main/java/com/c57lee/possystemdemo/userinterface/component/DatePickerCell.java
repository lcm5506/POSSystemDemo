package com.c57lee.possystemdemo.userinterface.component;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Date;


public class DatePickerCell<T> extends TableCell<T, Date> {
    private final DatePicker datePicker;

    public DatePickerCell(TableColumn<T,Date> column){
        super();
        this.datePicker = new DatePicker();
        datePicker.getEditor().setEditable(false);
        datePicker.setMaxWidth(100);
        this.datePicker.editableProperty().bind(column.editableProperty());
        this.datePicker.disableProperty().bind(column.editableProperty().not());
        this.datePicker.setOnShowing(e->{
            final TableView<T> tableView = getTableView();
            tableView.getSelectionModel().select(getTableRow().getIndex());
            tableView.edit(tableView.getSelectionModel().getSelectedIndex(),column);
        });
        this.datePicker.valueProperty().addListener((observable,oldValue,newValue)->{
            if (isEditing())
                commitEdit(Date.valueOf(newValue));
        });
        //setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(Date item, boolean empty){
        super.updateItem(item,empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else if (item == null) {
            setText(null);
            this.datePicker.setValue(null);
            setGraphic(datePicker);
        } else {
            this.datePicker.setValue(item.toLocalDate());
            this.setGraphic(datePicker);
        }
    }


}
