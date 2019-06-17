package gui;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import coding.*;
import fileManager.FileManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;;

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

        colAddrC.setCellValueFactory(new PropertyValueFactory<MachineCode, String>("addr"));
        colContC.setCellValueFactory(new PropertyValueFactory<MachineCode, String>("cont"));

        colAddrX.setCellValueFactory(new PropertyValueFactory<MachineCode, String>("addr"));
        colContX.setCellValueFactory(new PropertyValueFactory<MachineCode, String>("cont"));
        
        cbSection.getItems().add(".code");
        cbSection.getItems().add(".data");
        cbSection.setValue(".code");
        
        tableAssemblyCode.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableAssemblyData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableMachineC.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableMachineX.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tfLabel.requestFocus();
        tfLabel.selectAll();

        newButtonAction(null);
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
    @FXML private TitledPane tpCode;
    @FXML private TitledPane tpData;

    @FXML private TableView<AssemblyCode> tableAssemblyCode;
    @FXML private TableView<AssemblyCode> tableAssemblyData;
    
    @FXML private TableView<MachineCode> tableMachineC;
    @FXML private TableView<MachineCode> tableMachineX;
	
	@FXML private TableColumn<AssemblyCode, String> colSectionCode;
	@FXML private TableColumn<AssemblyCode, String> colLabelCode;
	@FXML private TableColumn<AssemblyCode, String> colMnemoCode;
    @FXML private TableColumn<AssemblyCode, String> colOperandsCode;
    
	@FXML private TableColumn<AssemblyCode, String> colSectionData;
	@FXML private TableColumn<AssemblyCode, String> colLabelData;
	@FXML private TableColumn<AssemblyCode, String> colMnemoData;
    @FXML private TableColumn<AssemblyCode, String> colOperandsData;
    
    @FXML private TableColumn<MachineCode, String> colAddrC;
    @FXML private TableColumn<MachineCode, String> colContC;

    @FXML private TableColumn<MachineCode, String> colAddrX;
    @FXML private TableColumn<MachineCode, String> colContX;

    @FXML private Label lbFile;
    @FXML private Label assemblyWarning;

    @FXML private MenuItem saveMenuItem;
    @FXML private MenuItem saveAsMenuItem;
    @FXML private MenuItem openMenuItem;
    @FXML private MenuItem newMenuItem;
    @FXML private MenuItem closeMenuItem;

    @FXML private Accordion acAssembly;

    @FXML private Button openButton;
    @FXML private Button saveButton;
    @FXML private Button runButton;
    @FXML private Button delButton;
    @FXML private Button addButton;
    @FXML private Button editButton;
    @FXML private Button newButton;
    
    @FXML private TextField tfLabel;
    @FXML private TextField tfMnemo;
    @FXML private TextField tfOperands;

    @FXML private ChoiceBox<String> cbSection;

    static FileManager fm;
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML public void newButtonAction(ActionEvent event) {
    	lbFile.setText("Nuevo proyecto");
    	while (!tableAssemblyCode.getItems().isEmpty()) {
            tableAssemblyCode.getItems().remove(0);
        }
        while (!tableAssemblyData.getItems().isEmpty()) {
            tableAssemblyData.getItems().remove(0);
        }
        while (!tableMachineC.getItems().isEmpty()){
            tableMachineC.getItems().remove(0);
        }
        while (!tableMachineX.getItems().isEmpty()){
            tableMachineX.getItems().remove(0);
        }
    	tableAssemblyCode.getItems().add(new AssemblyCode(".code","","",""));
        tableAssemblyData.getItems().add(new AssemblyCode(".data","","",""));
    }

    @FXML public void saveButtonAction(ActionEvent event) {
        if(lbFile.getText().equals("Nuevo proyecto")){
            saveAsButtonAction(null);
        }else{

        }
    }

    @FXML public void saveAsButtonAction(ActionEvent event) {

    }

    @FXML public void openButtonAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Abrir archivo de codigo AGF o archivo de texto TXT");
        fc.getExtensionFilters().add(new ExtensionFilter("Assembly Generator File", "*.agf"));
        fc.getExtensionFilters().add(new ExtensionFilter("Texto con codigo", "*.txt"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            fm = new FileManager(file.getAbsolutePath());
            lbFile.setText(file.getAbsolutePath());
            ArrayList<AssemblyCode> as = fm.loadCode();
            fillTables(as);
        }
    }
    
    @FXML public void runButtonAction(ActionEvent event) {
        System.out.println("Running.....");

        while (!tableMachineC.getItems().isEmpty()) {
            tableMachineC.getItems().remove(0);
        }
        while (!tableMachineX.getItems().isEmpty()) {
            tableMachineX.getItems().remove(0);
        }

        for (AssemblyCode aCode : tableAssemblyCode.getItems()) {
            if(aCode.getSection().equals("")){
                switch (aCode.getLabel()) {
                    case "load":
                        
                        break;
                    case "store":
                        
                        break;
                    case "add":

                        break;
                    case "sub":

                        break;
                    case "input":

                        break;
                    case "output":

                        break;
                    case "jpos":

                        break;
                    case "jneg":

                        break;
                    case "jz":

                        break;
                    case "jnz":

                        break;
                    case "jmp":

                        break;
                    case "halt":
                    
                        break;
                    default:
                        break;
                }
            } else {
                tableMachineC.getItems().add(new MachineCode("SECTION",aCode.getSection()));
                tableMachineX.getItems().add(new MachineCode("SECTION",aCode.getSection()));
            }
        }
        for (AssemblyCode aCode : tableAssemblyData.getItems()) {
            if(aCode.getSection().equals("")){

            } else {
                tableMachineC.getItems().add(new MachineCode("",""));
                tableMachineX.getItems().add(new MachineCode("",""));
                tableMachineC.getItems().add(new MachineCode("SECTION",aCode.getSection()));
                tableMachineX.getItems().add(new MachineCode("SECTION",aCode.getSection()));
            }
        }
    }

    public void fillTables(ArrayList<AssemblyCode> as) {
        ObservableList<AssemblyCode> olCode = FXCollections.observableArrayList();
        ObservableList<AssemblyCode> olData = FXCollections.observableArrayList();
        boolean wData = false;
        for (AssemblyCode temp : as) {
            if((temp.getSection().equals(".data")||(wData))){
                wData = true;
                olData.add(temp);
            }else{
                olCode.add(temp);
            }
        }
        while (!tableAssemblyCode.getItems().isEmpty()) {
            tableAssemblyCode.getItems().remove(0);
        }
        while (!tableAssemblyData.getItems().isEmpty()) {
            tableAssemblyData.getItems().remove(0);
        }
        tableAssemblyCode.setItems(olCode);
        tableAssemblyData.setItems(olData);
    }

    @FXML public void closeItemAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML public void addButtonAction(ActionEvent event) {
        String label = tfLabel.getText().toLowerCase();
        String mnemo = tfMnemo.getText().toLowerCase();
        String operands = tfOperands.getText().toLowerCase();
        if(cbSection.getSelectionModel().getSelectedItem().equals(".code")){
            if (
                mnemo.equals("load")||
                mnemo.equals("store")||
                mnemo.equals("add")||
                mnemo.equals("sub")||
                mnemo.equals("input")||
                mnemo.equals("output")||
                mnemo.equals("jpos")||
                mnemo.equals("jneg")||
                mnemo.equals("jz")||
                mnemo.equals("jnz")||
                mnemo.equals("jmp")||
                mnemo.equals("halt")
            ){
                System.out.println("Adding.....OK");
                AssemblyCode newAssemblyCode = new AssemblyCode("",label,mnemo,operands);
                tableAssemblyCode.getItems().add(newAssemblyCode);
                assemblyWarning.setVisible(false);
            } else {
                System.err.println("Adding.....Failed");
                assemblyWarning.setVisible(true);
            }
        } else {
            if (
                mnemo.equals("db")||
                mnemo.equals("equ")
            ){
                System.out.println("Adding.....OK");
                AssemblyCode newAssemblyCode = new AssemblyCode("",label,"db",operands);
                tableAssemblyData.getItems().add(newAssemblyCode);
                assemblyWarning.setVisible(false);
            } else {
                System.err.println("Adding.....Failed");
                assemblyWarning.setVisible(true);
            }
        }
        tfLabel.setText("");
        tfMnemo.setText("");
        tfOperands.setText("");
        tfLabel.requestFocus();
        tfLabel.selectAll();
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
            if(codes.getSection().equals("")){
                allCodesCode.remove(codes);
            }
        }

        ObservableList<AssemblyCode> selectedRowsData, allCodesData;
        allCodesData = tableAssemblyData.getItems();
        
        //this gives us the rows that were selected
        selectedRowsData = tableAssemblyData.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the AssemblyData objects from the table
        for (AssemblyCode codes: selectedRowsData)
        {
            if(codes.getSection().equals("")){
                allCodesData.remove(codes);
            }
        }
    }
    
    @FXML public void editButtonAction(ActionEvent event){
        String label = tfLabel.getText().toLowerCase();
        String mnemo = tfMnemo.getText().toLowerCase();
        String operands = tfOperands.getText().toLowerCase();
        ObservableList<AssemblyCode> selectedRowCode, allCodesCode;
        allCodesCode = tableAssemblyCode.getItems();
        selectedRowCode = tableAssemblyCode.getSelectionModel().getSelectedItems();
        for (int i = 0; i < allCodesCode.size(); i++){
            if(
                (allCodesCode.get(i) == selectedRowCode.get(0))&&
                selectedRowCode.get(0).getSection().equals("")
            ){
                if (
                    mnemo.equals("load")||
                    mnemo.equals("store")||
                    mnemo.equals("add")||
                    mnemo.equals("sub")||
                    mnemo.equals("input")||
                    mnemo.equals("output")||
                    mnemo.equals("jpos")||
                    mnemo.equals("jneg")||
                    mnemo.equals("jz")||
                    mnemo.equals("jnz")||
                    mnemo.equals("jmp")||
                    mnemo.equals("halt")
                ){
                    System.out.println("Editting.....OK");
                    AssemblyCode newAssemblyCode = new AssemblyCode("",label,mnemo,operands);
                    allCodesCode.set(i, newAssemblyCode);
                    assemblyWarning.setVisible(false);
                } else {
                    System.err.println("Editting.....Failed");
                    assemblyWarning.setVisible(true);
                }
            } else {
                System.err.println("Editting.....Failed");
                assemblyWarning.setVisible(true);
            }
        }
        ObservableList<AssemblyCode> selectedRowData, allCodesData;
        allCodesData = tableAssemblyData.getItems();
        selectedRowData = tableAssemblyData.getSelectionModel().getSelectedItems();
        for (int i = 0; i < allCodesData.size(); i++){
            if(
                (allCodesData.get(i) == selectedRowData.get(0))&&
                selectedRowData.get(0).getSection().equals("")
            ){
                if (
                    mnemo.equals("db")||
                    mnemo.equals("equ")
                ){
                    System.out.println("Editting.....OK");
                    AssemblyCode newAssemblyCode = new AssemblyCode("",label,mnemo,operands);
                    allCodesData.set(i, newAssemblyCode);
                    assemblyWarning.setVisible(false);
                } else {
                    System.err.println("Editting.....Failed");
                    assemblyWarning.setVisible(true);
                }
            } else {
                System.err.println("Editting.....Failed");
                assemblyWarning.setVisible(true);
            }
        }
        tfLabel.setText("");
        tfMnemo.setText("");
        tfOperands.setText("");
        tfLabel.requestFocus();
        tfLabel.selectAll();
    }
}