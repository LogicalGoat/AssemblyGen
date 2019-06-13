public class Plantilla implements Initializable{
	@FXML private TableView<AssemblyCode> tableAssembly;
    @FXML private TableColumn<AssemblyCode, Integer> colOrder;
    @FXML private TableColumn<AssemblyCode, String> colSection;
    @FXML private TableColumn<AssemblyCode, String> colLabel;
    @FXML private TableColumn<AssemblyCode, String> colMnemo;
    @FXML private TableColumn<AssemblyCode, String> colOperands;

    @FXML private TextField tfSection;
    @FXML private TextField tfLabel;
    @FXML private TextField tfMnemo;
    @FXML private TextField tfOperands;

    @FXML private Label assemblyWarning;

    static int num;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        assemblyWarning.setVisible(false);
        num = 0;
        //set up the columns in the table
        colOrder.setCellValueFactory(new PropertyValueFactory<AssemblyCode, Integer>("order"));
        colSection.setCellValueFactory(new PropertyValueFactory<AssemblyCode, String>("section"));
        colLabel.setCellValueFactory(new PropertyValueFactory<AssemblyCode, String>("label"));
        colMnemo.setCellValueFactory(new PropertyValueFactory<AssemblyCode, String>("mnemo"));
        colOperands.setCellValueFactory(new PropertyValueFactory<AssemblyCode, String>("operands"));
        
        //load dummy data
        tableAssembly.setItems(getCodes());
        
        tableAssembly.setEditable(true);
        colLabel.setCellFactory(TextFieldTableCell.forTableColumn());
        colOperands.setCellFactory(TextFieldTableCell.forTableColumn());
        
        //This will allow the table to select multiple rows at once
        tableAssembly.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tfSection.requestFocus();
        tfSection.selectAll();
    }

    private ObservableList<AssemblyCode> getCodes() {
        ObservableList<AssemblyCode> ol = FXCollections.observableArrayList();
        return ol;
    }

    // Acciones de los botones de la barra
    @FXML public void saveButtonAction(ActionEvent e){
        System.out.println("Saving....");
    }

    @FXML public void openButtonAction(ActionEvent e){
        System.out.println("Opening....");
    }

    @FXML public void closeButtonAction(ActionEvent e){
        System.exit(0);
    }
    
    //Acciones de los botones de edicion
    @FXML public void addButtonAction(ActionEvent e){
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
            tfMnemo.getText().equals("halt")||
            tfMnemo.getText().equals("")
        ){
            System.out.println("Adding.....OK");
            AssemblyCode newAssemblyCode = new AssemblyCode(tfSection.getText(),tfLabel.getText(),tfMnemo.getText(),tfOperands.getText());
            tableAssembly.getItems().add(newAssemblyCode);
            assemblyWarning.setVisible(false);
        } else if (tfSection.getText().equals(".code")||tfSection.getText().equals(".data")) {
            System.out.println("Adding.....Section");
            AssemblyCode newAssemblyCode = new AssemblyCode(tfSection.getText(),"","","");
            tableAssembly.getItems().add(newAssemblyCode);
            assemblyWarning.setVisible(false);
        } else {
            System.err.println("Adding.....Failed");
            assemblyWarning.setVisible(true);
        }
        tfSection.setText("");
        tfLabel.setText("");
        tfMnemo.setText("");
        tfOperands.setText("");
        tfSection.requestFocus();
        tfSection.selectAll();
    }


    @FXML public void delButtonAction(ActionEvent e){
        System.out.println("Deleting.....");
        ObservableList<AssemblyCode> selectedRows, allCodes;
        allCodes = tableAssembly.getItems();
        
        //this gives us the rows that were selected
        selectedRows = tableAssembly.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the AssemblyCode objects from the table
        for (AssemblyCode codes: selectedRows)
        {
            allCodes.remove(codes);
        }
    }

    @FXML public void sectionEdit(CellEditEvent edittedCell)
    {
        
        AssemblyCode AssemblyCodeSelected =  tableAssembly.getSelectionModel().getSelectedItem();
        AssemblyCodeSelected.setSection(edittedCell.getNewValue().toString());
        
    }
}
