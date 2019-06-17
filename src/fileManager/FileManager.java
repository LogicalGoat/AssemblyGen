package fileManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
    
    public void saveCode(ObservableList<AssemblyCode> listCode, ObservableList<AssemblyCode> listData){
        try{
            File filetemp = new File(this.address);
            if (filetemp.exists()){
                filetemp.delete();
                System.out.println("File deleted");
                System.out.println("Creating new file...");
                filetemp.createNewFile();
            } else{
                System.out.println("File created");
                filetemp.createNewFile();
            }
            FileWriter fw = new FileWriter(filetemp);
            BufferedWriter bw = new BufferedWriter(fw);

            // leo todos los elementos de todos los objetos
            for (AssemblyCode tempCode : listCode) {
                bw.append(tempCode.getSection()+'\t'+tempCode.getLabel()+'\t'+tempCode.getMnemo()+'\t'+tempCode.getOperands()+'\n');
            }
            for (AssemblyCode tempData : listData) {
                bw.append(tempData.getSection()+'\t'+tempData.getLabel()+'\t'+tempData.getMnemo()+'\t'+tempData.getOperands()+'\n');
            }
            String cadena;
            FileReader fr = new FileReader(filetemp);
            BufferedReader br = new BufferedReader(fr);
            while((cadena = br.readLine())!=null) {
                System.out.println(cadena);
            }
            fr.close();
            br.close();

            // cierro el filereader y el buffered reader
            bw.close();
            fw.close();

        }catch(Exception e1){
          
        }
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