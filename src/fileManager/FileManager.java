package fileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import coding.AssemblyCode;
import javafx.collections.ObservableList;

/**
 * FileManager
 */
public class FileManager {
    private String address;

    public FileManager(String address) {
        this.address = address;
    }
    
    public void saveCode(ObservableList<AssemblyCode> code, ObservableList<AssemblyCode> data) {
    }
    
    public ArrayList<AssemblyCode> loadCode(){
        ArrayList<AssemblyCode> codes = new ArrayList<AssemblyCode>();
        Scanner sc;
        try {
            sc = new Scanner(new File(this.address));
            while (sc.hasNext()) {
                String line = sc.nextLine(), temp = "";
                ArrayList<String> sCode = new ArrayList<String>();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != '\t') {
                        temp = temp + line.charAt(i);
                    } else {
                        sCode.add(temp);
                        temp = "";
                    }
                }
                sCode.add(temp);
                for (int i = 0; i < sCode.size(); i++) {
                    System.out.println(sCode.get(i));
                }
                if(!(sCode.get(0).equals("")&&sCode.get(1).equals("")&&sCode.get(2).equals("")&&sCode.get(3).equals(""))){
                    AssemblyCode code = new AssemblyCode(sCode.get(0), sCode.get(1), sCode.get(2), sCode.get(3));
                    code.toString();
                    codes.add(code);
                }
            }
	    }catch (FileNotFoundException e) {
            System.err.println("404 not found -- " + this.address);
            e.printStackTrace();
	    }
	    return codes;
    }    
}