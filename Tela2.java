/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urnaeletronica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

/**
 * FXML Controller class
 *
 * @author max
 */
public class Tela2 implements Initializable {
    
    @FXML private Pane pnSegundo;
    
    @FXML private TextField tfPrimeiro;
    @FXML private TextField tfSegundo;
    
    @FXML private ImageView ivPresidente;
    @FXML private ImageView ivVice;
    
    @FXML private Label lbPresidente;
    @FXML private Label lbPartido;
    @FXML private Label lbVice;
    
    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btn3;
    @FXML private Button btn4;
    @FXML private Button btn5;
    @FXML private Button btn6;
    @FXML private Button btn7;
    @FXML private Button btn8;
    @FXML private Button btn9;
    @FXML private Button btn0;
    @FXML private Button btnBranco;
    @FXML private Button btnCorrige;
    @FXML private Button btnConfirma;
    
    String AUDIO_URL = getClass().getResource("/sons/toque.mp3").toString();
    String FIM_URL = getClass().getResource("/sons/fim.mp3").toString();
    AudioClip toque = new AudioClip(AUDIO_URL);
    AudioClip fim = new AudioClip(FIM_URL);
    Integer marcador = 0;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pnSegundo.setVisible(false);
        
        btn1.setOnAction(e->{toque.play(); digitar(1);});
        btn2.setOnAction(e->{toque.play(); digitar(2);});
        btn3.setOnAction(e->{toque.play(); digitar(3);});
        btn4.setOnAction(e->{toque.play(); digitar(4);});
        btn5.setOnAction(e->{toque.play(); digitar(5);});
        btn6.setOnAction(e->{toque.play(); digitar(6);});
        btn7.setOnAction(e->{toque.play(); digitar(7);});
        btn8.setOnAction(e->{toque.play(); digitar(8);});
        btn9.setOnAction(e->{toque.play(); digitar(9);});
        btn0.setOnAction(e->{toque.play(); digitar(0);});
       
        btnCorrige.setOnAction(e->{
            toque.play();
            pnSegundo.setVisible(false);
            ivPresidente.setImage(null);
            ivVice.setImage(null);
            if (!tfSegundo.getText().isEmpty()) {
                tfSegundo.clear();
            } else {
                tfPrimeiro.clear();
                tfSegundo.clear();
                limparPane();
            }
        });
        
        btnConfirma.setOnAction(e->{
            fim.play();
            Integer voto = Integer.valueOf(tfPrimeiro.getText()+tfSegundo.getText());
            contagemFraudulenta(voto);
            pnSegundo.setVisible(false);
            tfPrimeiro.clear();
            tfSegundo.clear();
            limparPane();
        });
    }
    
    private void digitar(Integer c) {
        Integer voto = null;

        List<objDados> listaCandidato = new ArrayList();        
        listaCandidato.add(new objDados(13,"Fernando Haddad", "PT", "Manuela Dávila", "imagens/fernandohaddad.png", "imagens/manueladavila.png"));
        listaCandidato.add(new objDados(18,"Marina Silva", "REDE", "Eduardo Jorge", "imagens/marinasilva.png", "imagens/eduardojorge.png"));
        listaCandidato.add(new objDados(12,"Ciro Gomes", "PDT", "Kátia Abreu", "imagens/cirogomes.png", "imagens/katiaabreu.png"));
        listaCandidato.add(new objDados(17,"Jair Bolsonaro", "PSL", "General Mourão", "imagens/jairbolsonaro.png", "imagens/generalmourao.png"));

        if (tfPrimeiro.getText().isEmpty()) {
            tfPrimeiro.setText(String.valueOf(c));
            pnSegundo.setVisible(false);
            limparPane();
            voto = null;
        } else {
            tfSegundo.setText(String.valueOf(c));
            pnSegundo.setVisible(true);
            voto = Integer.valueOf(tfPrimeiro.getText()+tfSegundo.getText());
        }

        if (voto!=null) {
            limparPane();
            for (int i=0; i<listaCandidato.size(); i++) {
                if(Objects.equals(voto, listaCandidato.get(i).getI())) {
                    lbPresidente.setText(listaCandidato.get(i).getNomePresidente());
                    lbPartido.setText(listaCandidato.get(i).getPartido());
                    lbVice.setText(listaCandidato.get(i).getNomeVice());
                    ivPresidente.setImage(new Image(listaCandidato.get(i).getPathPresidente()));
                    ivVice.setImage(new Image(listaCandidato.get(i).getPathVice()));
                }
            }
            //ivPresidente.setImage(new Image("imagens/back.png"));
            //pnSegundo.setVisible(true);
            //ivVice.setImage(new Image("imagens/back.png"));
        }
    }

    private void limparPane() {
        lbPresidente.setText("");
        lbPartido.setText("");
        lbVice.setText("");
        ivPresidente.setImage(null);
        ivVice.setImage(null);
    }
    
    private void contagemFraudulenta(Integer voto) {
        //Se o voto for para o Bolsonaro, incluir 1 para o Haddad a cada 5 votos para o Bolsonaro:
        LocalDateTime hoje = LocalDateTime.now();
        LocalDate diaHoje = LocalDate.now();
        LocalDate diaEleicao = LocalDate.of(2018, 10, 7);  
        
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH");
        Integer horasAgora = Integer.valueOf(hoje.format(formatador));
        
        if (diaHoje.isEqual(diaEleicao)) {
            if (horasAgora > 13) {//Horário maior que 13 horas
                if (voto == 17) {
                    System.out.println(marcador);
                    if (marcador<3) {//Voto para o Bolsonaro
                        salvarLinha("17 - Jair Bolsonaro");
                        marcador++;
                    } else {//Voto para o Haddad
                        salvarLinha("13 - Fernando Haddad");
                        marcador = 0;
                    }
                } else {
                    contagemNormal(voto);
                }
            } else {//se horário for menor que 14 horas:
                contagemNormal(voto);
            }
        } else {
            contagemNormal(voto);
        }
    }
    
    private void contagemNormal(Integer voto) {
        if (voto == 12) {salvarLinha("12 - Ciro Gomes");}
        if (voto == 13) {salvarLinha("13 - Fernando Haddad");}
        if (voto == 17) {salvarLinha("17 - Jair Bolsonaro");}
        if (voto == 18) {salvarLinha("18 - Marina Silva");}
    }
    
    private void salvarLinha(String texto) {
        String Caminho = "C:\\easy\\UrnaEletronica\\contagem.txt";
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            String voto = "";
            FileWriter fw = new FileWriter(Caminho, true);
            BufferedWriter conexao = new BufferedWriter(fw);            
            
            Calendar myData = Calendar.getInstance(); //Classe abstrata (não utiliza new Calendar, como no new GregorianCalendar);
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");        
            voto = sd.format(myData.getTime())+"| "+texto;            
            conexao.write(voto);
            conexao.newLine();
            conexao.close();
        } catch (IOException e) {
        }
    }
    
    public class objDados {
        Integer i;
        String nomePresidente;
        String partido;
        String nomeVice;
        String pathPresidente;
        String pathVice;

        public objDados(Integer i, String nomePresidente, String partido, String nomeVice, String pathPresidente, String pathVice) {
            this.i = i;
            this.nomePresidente = nomePresidente;
            this.partido = partido;
            this.nomeVice = nomeVice;
            this.pathPresidente = pathPresidente;
            this.pathVice = pathVice;
        }

        public Integer getI() {return i;}        
        public String getNomePresidente() {return nomePresidente;}
        public void setNomePresidente(String nomePresidente) {this.nomePresidente = nomePresidente;}
        public String getPartido() {return partido;}
        public void setPartido(String partido) {this.partido = partido;}
        public String getNomeVice() {return nomeVice;}
        public void setNomeVice(String nomeVice) {this.nomeVice = nomeVice;}
        public String getPathPresidente() {return pathPresidente;}
        public void setPathPresidente(String pathPresidente) {this.pathPresidente = pathPresidente;}
        public String getPathVice() {return pathVice;}
        public void setPathVice(String pathVice) {this.pathVice = pathVice;}
        
        
        
    
    }
}
