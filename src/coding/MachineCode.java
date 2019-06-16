package coding;

/**
 * MachineCode
 */
public class MachineCode {
    
    private String addr;
    private String cont;

    public MachineCode(String addr, String cont) {
        this.addr = addr;
        this.cont = cont;
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
    @Override
    public String toString() {
        return "addr: "+this.addr+", cont:"+this.cont;
    }
}