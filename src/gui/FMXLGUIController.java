package gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import coding.AssemblyCode;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FMXLGUIController implements Initializable{
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		assemblyWarning.setVisible(false);
		
		colSectionCode.setCellValueFactory(new PropertyValueFactory<AssemblyCode, String>("section"));
        colLabelCode.setCellValueFactory(new PropertyValueFactory<AssemblyCode, String>("label"));
        colMnemoCode.setCellValueFactory(new PropertyValueFactory<AssemblyCode, String>("mnemo"));
        colOperandsCode.setCellValueFactory(new PropertyValueFactory<AssemblyCode, String>("operands"));
        colSectionData.setCellValueFactory(new PropertyValueFactory<AssemblyCode, String>("section"));
        colLabelData.setCellValueFactory(new PropertyValueFactory<AssemblyCode, String>("label"));
        colMnemoData.setCellValueFactory(new PropertyValueFactory<AssemblyCode, String>("mnemo"));
        colOperandsData.setCellValueFactory(new PropertyValueFactory<AssemblyCode, String>("operands"));
        
        cbSection.getItems().add(".code");
        cbSection.getItems().add(".data");
        cbSection.setValue(".code");
        
        newButtonAction(null);
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@FXML private TableView<AssemblyCode> tableAssemblyCode;
	@FXML private TableView<AssemblyCode> tableAssemblyData;
	
	@FXML private TableColumn<AssemblyCode, String> colSectionCode;
	@FXML private TableColumn<AssemblyCode, String> colLabelCode;
	@FXML private TableColumn<AssemblyCode, String> colMnemoCode;
	@FXML private TableColumn<AssemblyCode, String> colOperandsCode;
	@FXML private TableColumn<AssemblyCode, String> colSectionData;
	@FXML private TableColumn<AssemblyCode, String> colLabelData;
	@FXML private TableColumn<AssemblyCode, String> colMnemoData;
	@FXML private TableColumn<AssemblyCode, String> colOperandsData;

    @FXML private Label lbFile;
    @FXML private Label assemblyWarning;

    @FXML private MenuItem saveMenuItem;
    @FXML private MenuItem openMenuItem;
    @FXML private MenuItem newMenuItem;
    @FXML private MenuItem closeMenuItem;

    @FXML private Accordion acAssembly;

    @FXML private Button openButton;
    @FXML private Button saveButton;
    @FXML private Button delButton;
    @FXML private Button addButton;
    @FXML private Button newButton;
    
    @FXML private TextField tfLabel;
    @FXML private TextField tfMnemo;
    @FXML private TextField tfOperands;

    @FXML private ChoiceBox<String> cbSection;
    
    static File currentFile;
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML public void newButtonAction(ActionEvent event) {
    	lbFile.setText("Nuevo proyecto");
    	tableAssemblyCode.getItems().removeAll();
    	tableAssemblyData.getItems().removeAll();
    	tableAssemblyCode.getItems().add(new AssemblyCode(".code","","",""));
        tableAssemblyData.getItems().add(new AssemblyCode(".data","","",""));
    }

    @FXML public void saveButtonAction(ActionEvent event) {

    }

    @FXML public void openButtonAction(ActionEvent event) {

    }

    @FXML public void closeItemAction(ActionEvent event) {

    }

    @FXML public void sectionEdit(ActionEvent event) {

    }

    @FXML public void addButtonAction(ActionEvent event) {
        if(cbSection.getSelectionModel().getSelectedItem().equals(".code")){
            if (
                tfMnemo.getText().equals("load")||
                tfMnemo.getText().equals("store")||
                tfMnemo.getText().equals("add")||
                tfMnemo.getText().equals("sub")||
                tfMnemo.getText().equals("input")||
                tfMnemo.getText().equals("output")||
                tfMnemo.getText().equals("jpos")||
                tfMnemo.getText().equals("jneg")||
                tfMnemo.getText().equals("jz")||
                tfMnemo.getText().equals("jnz")||
                tfMnemo.getText().equals("jmp")||
                tfMnemo.getText().equals("halt")
            ){
                System.out.println("Adding.....OK");
                AssemblyCode newAssemblyCode = new AssemblyCode("",tfLabel.getText(),tfMnemo.getText(),tfOperands.getText());
                tableAssemblyCode.getItems().add(newAssemblyCode);
                assemblyWarning.setVisible(false);
            } else {
                System.err.println("Adding.....Failed");
                assemblyWarning.setVisible(true);
            }
            tfLabel.setText("");
            tfMnemo.setText("");
            tfOperands.setText("");
            tfLabel.requestFocus();
            tfLabel.selectAll();
        } else {
            if (
                tfMnemo.getText().equals("db")||
                tfMnemo.getText().equals("equ")
            ){
                System.out.println("Adding.....OK");
                AssemblyCode newAssemblyCode = new AssemblyCode("",tfLabel.getText(),"db",tfOperands.getText());
                tableAssemblyData.getItems().add(newAssemblyCode);
                assemblyWarning.setVisible(false);
            } else {
                System.err.println("Adding.....Failed");
                assemblyWarning.setVisible(true);
            }
            tfLabel.setText("");
            tfMnemo.setText("");
            tfOperands.setText("");
            tfLabel.requestFocus();
            tfLabel.selectAll();
        }
    }

    @FXML public void delButtonAction(ActionEvent event) {
        System.out.println("Deleting.....");
        ObservableList<AssemblyCode> selectedRowsCode, allCodesCode;
        allCodesCode = tableAssemblyCode.getItems();
        
        //this gives us the rows that were selected
        selectedRowsCode = tableAssemblyCode.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the AssemblyCode objects from the table
        for (AssemblyCode codes: selectedRowsCode)
        {
            allCodesCode.remove(codes);
        }

        ObservableList<AssemblyCode> selectedRowsData, allCodesData;
        allCodesData = tableAssemblyData.getItems();
        
        //this gives us the rows that were selected
        selectedRowsData = tableAssemblyData.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the AssemblyData objects from the table
        for (AssemblyCode codes: selectedRowsData)
        {
            allCodesData.remove(codes);
        }
    }
	
}
