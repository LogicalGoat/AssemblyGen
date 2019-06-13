package coding;

public class AssemblyCode {
    private String section;
    private String label;
    private String mnemo;
    private String operands;

    public AssemblyCode(String section, String label, String mnemo, String operands){
        this.section = section;
        this.label = label;
        this.mnemo = mnemo;
        this.operands = operands;
    }
    
    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }
    /**
     * @return the mnemo
     */
    public String getMnemo() {
        return mnemo;
    }
    /**
     * @return the operands
     */
    public String getOperands() {
        return operands;
    }
    /**
     * @return the section
     */
    public String getSection() {
        return section;
    }
    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
    /**
     * @param mnemo the mnemo to set
     */
    public void setMnemo(String mnemo) {
        this.mnemo = mnemo;
    }
    /**
     * @param operands the operands to set
     */
    public void setOperands(String operands) {
        this.operands = operands;
    }
    /**
     * @param section the section to set
     */
    public void setSection(String section) {
        this.section = section;
    }
}
