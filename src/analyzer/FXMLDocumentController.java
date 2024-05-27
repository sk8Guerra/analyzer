/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package analyzer;

import static analyzer.Token.CADENA;
import static analyzer.Token.ERROR;
import static analyzer.Token.NUM;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 *
 * @author jorge.guerra
 */
public class FXMLDocumentController implements Initializable {
    
    List<identificador> tokenslist;

    @FXML
    private Label label;
    @FXML
    private TextArea textArea1;

    @FXML
    private TextArea textArea2;

    @FXML
    private void handleButtonAction(ActionEvent event) throws FileNotFoundException, IOException {
        Lexer lexer = new Lexer(new StringReader(textArea1.getText()));
        tokenslist = new LinkedList<identificador>();
        
        int contIDs=0;
        String resultado = "\n";
        while (true) {
            Token token = lexer.yylex();
            if (token == null) {
                for (int i = 0; i < tokenslist.size(); i++) {
                    System.out.println(tokenslist.get(i).nombre + "=" + tokenslist.get(i).ID);
                }
                textArea2.setText("");
                textArea2.setText(resultado);
                return;
            }
            switch (token) {

                case ERROR:
                    resultado = resultado + "Error, simbolo no reconocido ";
                    break;

                case CADENA: {
                    contIDs++;
                    identificador tokenitem = new identificador();
                    tokenitem.nombre = lexer.lexeme;
                    tokenitem.ID = contIDs;
                    tokenslist.add(tokenitem);
                    resultado = resultado + "<TOKEN: STRING" + contIDs + "> ";
                    break;
                }
                case NUM:
                    resultado = resultado + "<TOKEN:NUMEROS " + lexer.lexeme + "> ";
                    break;
                default:
                    resultado = resultado + "TOKEN <" + lexer.lexeme + ">\n ";
            }
        }

    }
    
    @FXML
    private void analizarSintacticamente(ActionEvent event) throws FileNotFoundException, IOException {
        // Reader reader = new BufferedReader(new FileReader("fichero.txt"));
        Parser lexer = new Parser(new StringReader(textArea1.getText()));
        textArea2.setText("");
        while(true){
            ParserTokens token = lexer.yylex();
            if(token == null) { return; }
            textArea2.setText(textArea2.getText() + "Sintaxis: " + token + "\n");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
