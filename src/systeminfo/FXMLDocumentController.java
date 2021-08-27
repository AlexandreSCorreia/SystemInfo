package systeminfo;

import classes.DadosMaquina;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

/**
 *
 * @author AlexandreSCorreia
 */
public class FXMLDocumentController implements Initializable {

    private DadosMaquina dadosMaquina;
    private ProcessBuilder builder;
    private BufferedReader r;
    private String path;

    @FXML
    private Label label;

    @FXML
    private Label finish;

    @FXML
    private Label error;

    @FXML
    private ProgressIndicator progress;

    @FXML
    private Button btn;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        btn.setVisible(false);
        progress.setVisible(true);

        start();
        setTextLabel(label, "Aguarde o processo finalizar....");
    }

    public void start() {

        //inicio da task
        Task servidor = new Task() {

            @Override
            protected Object call() throws Exception {

                //codigo pego do run
                try {

                    preencherObjeto();
                    CriarTXT();

                    Platform.runLater(() -> {

                        progress.setVisible(false);
                        finish.setVisible(true);
                        setTextLabel(finish, "Processo finalizado com êxito,"
                                + "foi criado um arquivo com o nome DadosCapturados.txt com a copia do texto acima, "
                                + "ele está localizado em sua Área de Trabalho");

                        setTextLabel(label, dadosMaquina.toString());
                    });

                    Runtime.getRuntime().exec("explorer " + path + "\\desktop\\");

                } catch (IOException ex) {

                    Platform.runLater(() -> {
                        setTextLabel(error, "Ocorreu um erro \n" + ex.getMessage());
                        progress.setVisible(false);
                        error.setVisible(true);
                    });
                }
                //fim do codigo pego do run
                return null;
            }

        };
        //fim da task    

        Platform.runLater(() -> {
            Thread t = new Thread(servidor);
            t.start();
        });
    }

    public void setTextLabel(Label labelRecebida, String texto) {
        Platform.runLater(() -> {
            labelRecebida.setText(texto);
        });
    }

    public void CriarTXT() {

        String pathInner = path + "\\desktop\\DadosCapturados.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathInner))) {

            bw.write(dadosMaquina.toString());
            bw.newLine();

        } catch (IOException e) {

            Platform.runLater(() -> {
                setTextLabel(error, "Ocorreu um erro \n" + e.getMessage());
                progress.setVisible(false);
                error.setVisible(true);
            });
        }

    }

    public void preencherObjeto() throws IOException {

        builder = new ProcessBuilder(
                "powershell.exe", "Get-ComputerInfo -Property \"CsManufacturer\",\"CsModel\",\"WindowsProductName\",\"BiosSeralNumber\",\"CsProcessors\",\"CsPhyicallyInstalledMemory\",\"CsSystemType\",\"CsCaption\",\"CsDomain\" | Format-List \"CsManufacturer\",\"CsModel\",\"WindowsProductName\",\"BiosSeralNumber\",\"CsProcessors\",\"CsSystemType\",\"CsCaption\",\"CsDomain\",@{label=\'CsPhyicallyInstalledMemory (GB)\';expression={$_.CsPhyicallyInstalledMemory/1MB}}; Get-WmiObject Win32_PhysicalMemory -Property \"SMBIOSMemoryType\",\"Speed\",\"FormFactor\"");

        builder.redirectErrorStream(true);
        Process p;
        p = builder.start();
        r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            if (line.contains("CsManufacturer")) {

                dadosMaquina.setCsManufacturer(line.split(":")[1]);

            } else if (line.contains("CsModel")) {

                dadosMaquina.setCsModel(line.split(":")[1]);

            } else if (line.contains("WindowsProductName")) {

                dadosMaquina.setWindowsProductName(line.split(":")[1]);

            } else if (line.contains("CsSystemType")) {

                dadosMaquina.setCsSystemType(line.split(":")[1].split("-")[0]);

            } else if (line.contains("BiosSeralNumber")) {

                dadosMaquina.setBiosSeralNumber(line.split(":")[1]);

            } else if (line.contains("CsProcessors")) {

                dadosMaquina.setCsProcessors(line.split(":")[1].replace("{", "").replace("}", ""));

            } else if (line.contains("CsPhyicallyInstalledMemory")) {

                dadosMaquina.setCsPhyicallyInstalledMemory(line.split(":")[1]);

            } else if (line.contains("SMBIOSMemoryType")) {

                dadosMaquina.setSMBIOSMemoryType(line.split(":")[1]);

                switch (dadosMaquina.getSMBIOSMemoryType()) {

                    case " 20":
                        dadosMaquina.setSMBIOSMemoryType("DDR");
                        break;

                    case " 21":
                        dadosMaquina.setSMBIOSMemoryType("DDR2");
                        break;

                    case " 22":
                        dadosMaquina.setSMBIOSMemoryType("DDR2 FB-DIMM");
                        break;

                    case " 24":
                        dadosMaquina.setSMBIOSMemoryType("DDR3");
                        break;

                    case " 26":
                        dadosMaquina.setSMBIOSMemoryType("DDR4");
                        break;

                    default:
                        dadosMaquina.setSMBIOSMemoryType("Não foi possivel identificar o tipo de memoria");
                }

            } else if (line.contains("Speed")) {

                dadosMaquina.setSpeed(line.split(":")[1]);

            } else if (line.contains("CsDomain")) {

                dadosMaquina.setCsDomain(line.split(":")[1]);

            } else if (line.contains("CsCaption")) {

                dadosMaquina.setCsCaption(line.split(":")[1]);

            } else if (line.contains("FormFactor")) {

                dadosMaquina.setFormFactor(line.split(":")[1]);

                switch (dadosMaquina.getFormFactor()) {

                    case " 8":
                        dadosMaquina.setFormFactor("DIMM");
                        break;

                    case " 12":
                        dadosMaquina.setFormFactor("SODIMM");
                        break;
                }

            }

        }

        //Capturar o IP - Dominio
        InetAddress data[] = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
        int index = data[1].toString().split("/")[1].length() <= 15 ? 1 : 0;
        dadosMaquina.setIP(data[index].toString().split("/")[1]);

        //UserName
        dadosMaquina.setUser(pegarUmaLinha("powershell.exe", "$env:UserName"));

        //Rota diretorio principal
        path = pegarUmaLinha("powershell.exe", "$env:USERPROFILE");
    }

    public String pegarUmaLinha(String terminal, String command) throws IOException {
        String line = "";
        builder = new ProcessBuilder(terminal, command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        r = new BufferedReader(new InputStreamReader(p.getInputStream()));

        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            return line;
        }
        return "Ocorreu algum erro no comando";
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dadosMaquina = new DadosMaquina();

    }

}
