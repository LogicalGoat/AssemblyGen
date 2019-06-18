package coding;

/**
 * MachineCode
 */
public class MachineCode {
    
    private String addr;
    private String cont;
    private String var;


    public MachineCode(String addr, String cont) {
        this.addr = addr;
        this.cont = cont;
    }

    public MachineCode(String addr, String cont, String var){
        this.addr = addr;
        this.cont = cont;
        this.var = var;
    }

    /**
     * @param addr the addr to set
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }
    /**
     * @param cont the cont to set
     */
    public void setCont(String cont) {
        this.cont = cont;
    }
    /**
     * @param var the var to set
     */
    public void setVar(String var) {
        this.var = var;
    }
    /**
     * @return the addr
     */
    public String getAddr() {
        return addr;
    }
    /**
     * @return the cont
     */
    public String getCont() {
        return cont;
    }
    /**
     * @return the var
     */
    public String getVar() {
        return var;
    }
    @Override
    public String toString() {
        if(var == null){
            return "addr: "+this.addr+", cont:"+this.cont;
        }else{
            return "addr: "+this.addr+", cont:"+this.cont+", var:"+this.var;
        }
    }
}