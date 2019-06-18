package gui;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import coding.*;
import fileManager.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
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

        colSymbolsIns.setCellValueFactory(new PropertyValueFactory<Instruction, String>("symbols"));
        colAddrIns.setCellValueFactory(new PropertyValueFactory<Instruction, String>("addr"));

        colSymbolsDat.setCellValueFactory(new PropertyValueFactory<Data, String>("symbols"));
        colAddrDat.setCellValueFactory(new PropertyValueFactory<Data, String>("addr"));
        colContDat.setCellValueFactory(new PropertyValueFactory<Data, String>("cont"));
        
        cbSection.getItems().add(".code");
        cbSection.getItems().add(".data");
        cbSection.setValue(".code");

        tableAssemblyCode.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableAssemblyData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableMachineC.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableMachineX.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableInstructions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableDatas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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

    @FXML private TableView<Instruction> tableInstructions;
    @FXML private TableView<Data> tableDatas;
	
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

    @FXML private TableColumn<Instruction, String> colSymbolsIns;
    @FXML private TableColumn<Instruction, String> colAddrIns;

    @FXML private TableColumn<Data, String> colSymbolsDat;
    @FXML private TableColumn<Data, String> colAddrDat;
    @FXML private TableColumn<Data, String> colContDat;

    @FXML private Label lbFile;
    @FXML private Label assemblyWarning;

    @FXML private MenuItem saveMenuItem;
    @FXML private MenuItem saveAsMenuItem;
    @FXML private MenuItem openMenuItem;
    @FXML private MenuItem newMenuItem;
    @FXML private MenuItem closeMenuItem;
    @FXML private MenuItem aboutMenuItem;

    @FXML private Accordion acAssembly;

    @FXML private Button openButton;
    @FXML private Button saveButton;
    @FXML private Button runButton;
    @FXML private Button delButton;
    @FXML private Button addButton;
    @FXML private Button editButton;
    @FXML private Button newButton;
    @FXML private Button copyCodeCButton;
    @FXML private Button copyDataCButton;
    @FXML private Button copyCodeXButton;
    @FXML private Button copyDataXButton;
    
    @FXML private TextField tfLabel;
    @FXML private TextField tfMnemo;
    @FXML private TextField tfOperands;

    @FXML private ChoiceBox<String> cbSection;

    static FileManager fm;

    static ObservableList<MachineCode> olCodeC = FXCollections.observableArrayList();
    static ObservableList<MachineCode> olDataC = FXCollections.observableArrayList();
    static ObservableList<MachineCode> olCodeX = FXCollections.observableArrayList();
    static ObservableList<MachineCode> olDataX = FXCollections.observableArrayList();
    
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
        while (!tableInstructions.getItems().isEmpty()){
            tableInstructions.getItems().remove(0);
        }
        while (!tableDatas.getItems().isEmpty()){
            tableDatas.getItems().remove(0);
        }
    	tableAssemblyCode.getItems().add(new AssemblyCode(".code","","",""));
        tableAssemblyData.getItems().add(new AssemblyCode(".data","","",""));
    }

    @FXML public void saveButtonAction(ActionEvent event) {
        if(lbFile.getText().equals("Nuevo proyecto")){
            saveAsButtonAction(null);
        }else{
            fm.saveCode(tableAssemblyCode.getItems(), tableAssemblyData.getItems());
        }
    }

    @FXML public void saveAsButtonAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Elegir ubicación para archivo de codigo AGF o archivo de texto TXT");
        fc.getExtensionFilters().add(new ExtensionFilter("Assembly Generator File", "*.agf"));
        fc.getExtensionFilters().add(new ExtensionFilter("Texto con codigo", "*.txt"));
        File file = fc.showSaveDialog(null);
        if (file != null) {
            fm = new FileManager(file.getAbsolutePath());
            lbFile.setText(file.getAbsolutePath());
            fm.saveCode(tableAssemblyCode.getItems(), tableAssemblyData.getItems());
        }
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
    
    public static String xilinx(String n){
        return "X"+'"'+n+'"';
    }

    public static String cpp(String n){
        return "0x"+n;
    }

    @FXML public void runButtonAction(ActionEvent event) {

        olCodeC = FXCollections.observableArrayList();
        olDataC = FXCollections.observableArrayList();

        olCodeX = FXCollections.observableArrayList();
        olDataX = FXCollections.observableArrayList();

        System.out.println("Running.....");

        while (!tableMachineC.getItems().isEmpty()) {
            tableMachineC.getItems().remove(0);
        }
        while (!tableMachineX.getItems().isEmpty()) {
            tableMachineX.getItems().remove(0);
        }
        int j = 0;
        for (AssemblyCode aData : tableAssemblyData.getItems()) {
            if(aData.getSection().equals("")){
                String x = String.format("%X", j);
                if (x.length() == 1) {
                    x = "0"+x;
                }
                if(aData.getOperands().equals("")) aData.setOperands("0");
                String y = String.format("%X", Integer.valueOf(aData.getOperands()));
                if (y.length() == 1) {
                    y = "0"+y;
                }
                olDataC.add(new MachineCode(x, cpp(y), aData.getLabel()));
                olDataX.add(new MachineCode(x, xilinx(y), aData.getLabel()));
                tableDatas.getItems().add(new Data(aData.getLabel(),x,y));
                j++;
            } else {
                olDataC.add(new MachineCode("",""));
                olDataX.add(new MachineCode("",""));
                olDataC.add(new MachineCode("SECTION",aData.getSection()));
                olDataX.add(new MachineCode("SECTION",aData.getSection()));
            }
        }
        int k = 0;
        for (AssemblyCode aCode : tableAssemblyCode.getItems()) {
            if(aCode.getSection().equals("")){
                String x = String.format("%X", k);
                if (x.length() == 1) {
                    x = "0"+x;
                }
                if(!aCode.getLabel().equals("")){
                    tableInstructions.getItems().add(new Instruction(aCode.getLabel(),x));
                }
                k++;
            }
        }
        int i = 0;
        for (AssemblyCode aCode : tableAssemblyCode.getItems()) {
            if(aCode.getSection().equals("")){
                String x = String.format("%X", i);
                if (x.length() == 1) {
                    x = "0"+x;
                }
                String order = "0";
                String acc = "00";
                for (MachineCode mc : olDataC) {
                    try {
                        if (mc.getVar().equals(aCode.getOperands())){
                            acc = mc.getAddr();
                        }
                    } catch (Exception e) {}
                }
                switch (aCode.getMnemo()) {
                    case "load":
                        order = "0";
                        break;
                    case "store":
                        order = "1";
                        break;
                    case "add":
                        order = "2";
                        break;
                    case "sub":
                        order = "3";
                        break;
                    case "input":
                        order = "4";
                        acc = "00";
                        break;
                    case "output":
                        order = "5";
                        acc = "00";
                        break;
                    case "jpos":
                        order = "6";
                        for (Instruction ins : tableInstructions.getItems()) {
                            if(aCode.getOperands().equals(ins.getSymbols())){
                                acc = ins.getAddr();
                            }
                        }
                        break;
                    case "jneg":
                        order = "7";
                        for (Instruction ins : tableInstructions.getItems()) {
                            if(aCode.getOperands().equals(ins.getSymbols())){
                                acc = ins.getAddr();
                            }
                        }
                        break;
                    case "jz":
                        order = "8";
                        for (Instruction ins : tableInstructions.getItems()) {
                            if(aCode.getOperands().equals(ins.getSymbols())){
                                acc = ins.getAddr();
                            }
                        }
                        break;
                    case "jnz":
                        order = "9";
                        for (Instruction ins : tableInstructions.getItems()) {
                            if(aCode.getOperands().equals(ins.getSymbols())){
                                acc = ins.getAddr();
                            }
                        }
                        break;
                    case "jmp":
                        order = "A";
                        for (Instruction ins : tableInstructions.getItems()) {
                            if(aCode.getOperands().equals(ins.getSymbols())){
                                acc = ins.getAddr();
                            }
                        }
                        break;
                    case "halt":
                        order = "B";
                        acc = "00";
                        break;
                    default:
                        break;
                }
                olCodeC.add(new MachineCode(x, cpp(order+acc)));
                olCodeX.add(new MachineCode(x, xilinx(order+acc)));
                i++;
            } else {
                olCodeC.add(new MachineCode("SECTION",aCode.getSection()));
                olCodeX.add(new MachineCode("SECTION",aCode.getSection()));
            }
        }
        System.out.println("olcodec");
        for (MachineCode mcdc : olCodeC) {
            System.out.println(mcdc.toString());
            tableMachineC.getItems().add(mcdc);
        }
        System.out.println("oldatac");
        for (MachineCode mcdc : olDataC) {
            System.out.println(mcdc.toString());
            tableMachineC.getItems().add(mcdc);
        }
        System.out.println("olcodex");
        for (MachineCode mcdx : olCodeX) {
            System.out.println(mcdx.toString());
            tableMachineX.getItems().add(mcdx);
        }
        System.out.println("oldatax");
        for (MachineCode mcdx : olDataX) {
            System.out.println(mcdx.toString());
            tableMachineX.getItems().add(mcdx);
        }
    }

    public void fillTables(ArrayList<AssemblyCode> as) {
        ObservableList<AssemblyCode> olCodeC = FXCollections.observableArrayList();
        ObservableList<AssemblyCode> olDataC = FXCollections.observableArrayList();
        boolean wData = false;
        for (AssemblyCode temp : as) {
            if((temp.getSection().equals(".data")||(wData))){
                wData = true;
                olDataC.add(temp);
            }else{
                olCodeC.add(temp);
            }
        }
        while (!tableAssemblyCode.getItems().isEmpty()) {
            tableAssemblyCode.getItems().remove(0);
        }
        while (!tableAssemblyData.getItems().isEmpty()) {
            tableAssemblyData.getItems().remove(0);
        }
        tableAssemblyCode.setItems(olCodeC);
        tableAssemblyData.setItems(olDataC);
    }

    @FXML public void closeItemAction(ActionEvent event) {
        Alert dialog = new Alert(AlertType.CONFIRMATION);
        dialog.setTitle("Ventana de Confirmacion");
        dialog.setHeaderText("¿Salir?");
        dialog.initStyle(StageStyle.DECORATED);
        dialog.setContentText("Los cambios no guardados se PERDERÁN");
        Optional<ButtonType>result = dialog.showAndWait();
        if(result.get() == ButtonType.OK){
            System.exit(0);
        }
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
                    //assemblyWarning.setVisible(true);
                }
            } else {
                System.err.println("Editting.....Failed");
                //assemblyWarning.setVisible(true);
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
                    //assemblyWarning.setVisible(true);
                }
            } else {
                System.err.println("Editting.....Failed");
                //assemblyWarning.setVisible(true);
            }
        }
        tfLabel.setText("");
        tfMnemo.setText("");
        tfOperands.setText("");
        tfLabel.requestFocus();
        tfLabel.selectAll();
    }

    @FXML public void aboutButtonAction(ActionEvent event){
        Alert about = new Alert(AlertType.INFORMATION);
        about.setTitle("Assembly Generator v2.5 Alpha");
        about.setHeaderText("Assembly Generator v2.5 Alpha");
        about.setContentText("Hecho por: \n - Andrés Silva \n - Julio Rodriguez \n - Sebastián Leon \n - Fabián Perdomo");
        about.initStyle(StageStyle.UTILITY);
        about.showAndWait();
    }
    @FXML public void copyCodeCButtonAction(ActionEvent event){
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        String s = "";
        for (MachineCode mc : olCodeC) {
            if (!mc.getAddr().equals("SECTION")) {
                s = s + mc.getCont() + "," + "\n";
            }
        }
        content.putString(s);
        clipboard.setContent(content);
    }
    @FXML public void copyDataCButtonAction(ActionEvent event){
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        String s = "";
        for (MachineCode mc : olDataC) {
            if (!mc.getAddr().equals("SECTION")) {
                s = s + mc.getCont() + "," + "\n";
            }
        }
        content.putString(s);
        clipboard.setContent(content);
    }
    @FXML public void copyCodeXButtonAction(ActionEvent event){
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        String s = "";
        for (MachineCode mc : olCodeX) {
            if (!mc.getAddr().equals("SECTION")) {
                s = s + mc.getCont() + "," + "\n";
            }
        }
        content.putString(s);
        clipboard.setContent(content);
    }
    @FXML public void copyDataXButtonAction(ActionEvent event){
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        String s = "";
        for (MachineCode mc : olDataX) {
            if (!mc.getAddr().equals("SECTION")) {
                s = s + mc.getCont() + "," + "\n";
            }
        }
        content.putString(s);
        clipboard.setContent(content);
    }
}