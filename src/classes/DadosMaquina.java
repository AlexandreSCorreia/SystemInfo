package classes;

/**
 *
 * @author AlexandreSCorreia
 */
public class DadosMaquina {

    private String CsManufacturer;
    private String CsModel;
    private String WindowsProductName;
    private String CsSystemType;
    private String BiosSeralNumber;
    private String CsProcessors;
    private String CsPhyicallyInstalledMemory;
    private String SMBIOSMemoryType;
    private String Speed;

    private String CsCaption;
    private String CsDomain;
    private String IP;
    private String user;
    private String FormFactor;

    
    public DadosMaquina() {}

    public DadosMaquina(String CsManufacturer, String CsModel, String WindowsProductName, String CsSystemType, String BiosSeralNumber, 
            String CsPhyicallyInstalledMemory, String SMBIOSMemoryType, String Speed) 
    {
        this.CsManufacturer = CsManufacturer;
        this.CsModel = CsModel;
        this.WindowsProductName = WindowsProductName;
        this.CsSystemType = CsSystemType;
        this.BiosSeralNumber = BiosSeralNumber;
        this.CsPhyicallyInstalledMemory = CsPhyicallyInstalledMemory;
        this.SMBIOSMemoryType = SMBIOSMemoryType;
        this.Speed = Speed;
    }

    public String getCsManufacturer() {
        return CsManufacturer;
    }

    public void setCsManufacturer(String CsManufacturer) {
        this.CsManufacturer = CsManufacturer;
    }

    public String getCsModel() {
        return CsModel;
    }

    public void setCsModel(String CsModel) {
        this.CsModel = CsModel;
    }

    public String getWindowsProductName() {
        return WindowsProductName;
    }

    public void setWindowsProductName(String WindowsProductName) {
        this.WindowsProductName = WindowsProductName;
    }

    public String getCsSystemType() {
        return CsSystemType;
    }

    public void setCsSystemType(String CsSystemType) {
        this.CsSystemType = CsSystemType;
    }

    public String getBiosSeralNumber() {
        return BiosSeralNumber;
    }

    public void setBiosSeralNumber(String BiosSeralNumber) {
        this.BiosSeralNumber = BiosSeralNumber;
    }

    public String getCsProcessors() {
        return CsProcessors;
    }

    public void setCsProcessors(String CsProcessors) {
        this.CsProcessors = CsProcessors;
    }

    public String getCsPhyicallyInstalledMemory() {
        return CsPhyicallyInstalledMemory;
    }

    public void setCsPhyicallyInstalledMemory(String CsPhyicallyInstalledMemory) {
        this.CsPhyicallyInstalledMemory = CsPhyicallyInstalledMemory;
    }

    public String getSMBIOSMemoryType() {
        return SMBIOSMemoryType;
    }

    public void setSMBIOSMemoryType(String SMBIOSMemoryType) {
        this.SMBIOSMemoryType = SMBIOSMemoryType;
    }

    public String getSpeed() {
        return Speed;
    }

    public void setSpeed(String Speed) {
        this.Speed = Speed;
    }

    public String getCsCaption() {
        return CsCaption;
    }

    public void setCsCaption(String CsCaption) {
        this.CsCaption = CsCaption;
    }

    public String getCsDomain() {
        return CsDomain;
    }

    public void setCsDomain(String CsDomain) {
        this.CsDomain = CsDomain;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFormFactor() {
        return FormFactor;
    }

    public void setFormFactor(String FormFactor) {
        this.FormFactor = FormFactor;
    }

    @Override
    public String toString() {
        return "DADOS CAPTURADOS ABAIXO:\n\n"
                + "Máquina" + CsManufacturer + CsModel + " | " + WindowsProductName + CsSystemType + "\n"
                + "-Usuario: " + user + "\n"
                + "-Hostname:" + CsCaption + "\n"
                + "-Dominio:" + CsDomain + "\n"
                + "-IP:" + IP + "\n"
                + "-Etiqueta de serviço: " + BiosSeralNumber + "\n"
                + "-Processador:" + CsProcessors + "\n"
                + "-Memória: " + CsPhyicallyInstalledMemory + "GB de RAM " + SMBIOSMemoryType + " " + FormFactor + " " + Speed + "MHz";
    }

}
