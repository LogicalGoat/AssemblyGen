package fileManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import coding.AssemblyCode;

/**
 * FileManager
 */
public class FileManager {
    private String ruta;

    public FileManager(String ruta) {
        this.ruta = ruta;
    }
    
    //El formato de guardado por linea es: NombreAssemblyCode,score,
    // public boolean saveFile(ArrayList<AssemblyCode> AssemblyCodes)throws FileNotFoundException, IOException{
    //     BufferedWriter salida = null;
        
    //     FileWriter fw = null;

    //     File file = new File(this.ruta);
    //     if (!file.exists()) {
    //         file.createNewFile();
    //     }
            
    //     //El true permite seguir escribiendo en el archivo sin que s sobreescriba lo ya existente
    //     fw = new FileWriter(file.getAbsoluteFile(), true);
    //     salida = new BufferedWriter(fw);
        
    //     for(AssemblyCode AssemblyCode : AssemblyCodes){
    //         salida.write(AssemblyCode.getNombre());
    //         salida.write(",");
    //         salida.write(String.valueOf(AssemblyCode.getScore()));
    //         salida.write(",\n");
    //     }
    //     salida.flush();
    //     salida.close();
    //     return true;
    // }
    
    public ArrayList<AssemblyCode> loadCode(){
        ArrayList<AssemblyCode> codes = new ArrayList<AssemblyCode>();
        Scanner sc;
        try {
            sc = new Scanner(new File(this.ruta));
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
            System.err.println("File not found -- " + this.ruta);
            e.printStackTrace();
	    }
	    return codes;
    }    
}