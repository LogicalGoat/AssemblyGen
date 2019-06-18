package coding;

/**
 * Data
 */
public class Data {
    private String symbols;
    private String addr;
    private String cont;

    public Data(String symbols, String addr, String cont) {
        this.symbols = symbols;
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
     * @param symbols the symbols to set
     */
    public void setSymbols(String symbols) {
        this.symbols = symbols;
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
     * @return the symbols
     */
    public String getSymbols() {
        return symbols;
    }
}