package coding;

/**
 * Instructions
 */
public class Instruction {
    private String symbols;
    private String addr;

    public Instruction(String symbols, String addr) {
        this.symbols = symbols;
        this.addr = addr;
    }

    /**
     * @param addr the addr to set
     */
    public void setAddr(String addr) {
        this.addr = addr;
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
     * @return the symbols
     */
    public String getSymbols() {
        return symbols;
    }
}