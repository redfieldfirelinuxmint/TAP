package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Locale;
import java.util.Random;

public class Controller {

    @FXML AnchorPane father;
    @FXML HBox wordPane;
    @FXML Pane imagePane;
    @FXML VBox letterPane;

    //------------------------------------------

    ImageView head,body,leftLeg,rightLeg,rightHand,leftHand;
    int center,numberWord,limit,lives = 6;
    Random random;
    char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    String[]
            words = {"HACKER","SOFTWARE","HARDWARE","VIRUS","APPLICATION","PROGRAMMER","SYSTEM"};
    String
            dark = "DARK", gray = "GRAY" , darkGray = dark + gray,
            style = "-fx-background-color: BLACK; -fx-background-radius: 50px; -fx-border-color: " + darkGray + "; -fx-border-radius: 50px; -fx-text-fill: " + darkGray + ";",
            wordSelected = "",
            wordTextFieldText = "";
    TextField[] word;
    Button[] letter;
    Alert alert;
    Boolean found = false;

    //------------------------------------------

    @FXML void initialize(){
        start();
        createImage();
    }//initialize

    private void start(){

        random = new Random();
        numberWord = random.nextInt(words.length-1);
        wordSelected = words[numberWord];

        word = new TextField[words[numberWord].length()];
        limit = word.length;

        giveActions();

        addLetters();

    }//start

    private void giveActions(){

        for(int x = 0; x < limit; x++){

            int end = x;

            word[x] = new TextField();
            word[x].setFont(new Font("Ubuntu Light",40));
            word[x].setPrefSize(90,60);
            word[x].setAlignment(Pos.CENTER);
            word[x].setStyle(style);

                if(x == 0) word[x].setOnKeyReleased(event -> {
                        word[end].setText(word[end].getText().toUpperCase(Locale.ROOT));
                        if (!(event.getCode().getName().equals("Backspace"))) {
                            checkForKeyboard(word[end].getText().charAt(0),end);
                            word[end + 1].requestFocus();
                        }//if
                    });//action
                else if(x == limit-1) word[x].setOnKeyReleased(event -> {
                        word[end].setText(word[end].getText().toUpperCase(Locale.ROOT));
                        if (event.getCode().getName().equals("Backspace")) {
                            word[end].setText("");
                            word[end - 1].requestFocus();
                        } //if
                        else {
                            checkForKeyboard(word[end].getText().charAt(0),end);
                        } //else
                    });//action
                else word[x].setOnKeyReleased(event -> {
                        word[end].setText(word[end].getText().toUpperCase(Locale.ROOT));
                        if (event.getCode().getName().equals("Backspace")) {
                            word[end].setText("");
                            word[end - 1].requestFocus();
                        } //if
                        else {
                            checkForKeyboard(word[end].getText().charAt(0),end);
                            word[end+1].requestFocus();
                        } //else
                    });//action

            wordPane.getChildren().add(word[x]);

        }//for

    }//giveActions
    private void createImage(){

        center = (int) (imagePane.getMinWidth()/2);

        head = new ImageView(new Image("./sample/Images/head.png"));
        body = new ImageView(new Image("./sample/Images/body.png"));
        leftHand = new ImageView(new Image("sample/Images/leftHand.png"));
        leftLeg = new ImageView(new Image("sample/Images/leftLeg.png"));
        rightHand = new ImageView(new Image("./sample/Images/rightHand.png"));
        rightLeg = new ImageView(new Image("./sample/Images/rightLeg.png"));

        head.setVisible(false);
        body.setVisible(false);
        leftHand.setVisible(false);
        leftLeg.setVisible(false);
        rightLeg.setVisible(false);
        rightHand.setVisible(false);

        head.setLayoutX(center - 50);
        head.setLayoutY(30);

        body.setLayoutX(center - 50);
        body.setLayoutY(130);

        leftLeg.setLayoutX(center - 100);
        leftLeg.setLayoutY(230);

        leftHand.setLayoutX(center - 100);
        leftHand.setLayoutY(150);

        rightLeg.setLayoutX(center);
        rightLeg.setLayoutY(230);

        rightHand.setLayoutX(center);
        rightHand.setLayoutY(150);

        imagePane.getChildren().addAll(head,body,leftHand,leftLeg,rightHand,rightLeg);

    }//createImage
    private void addLetters(){

        letter = new Button[letters.length];

        for(int x = 0; x < letter.length;x++){

            int end = x;

            letter[x] = new Button(letters[x]+"");
            letter[x].setStyle(style);
            letter[x].setFont(new Font("Ubuntu light",40));
            letter[x].setOnAction(event ->{
                check(letters[end]);
                letter[end].setVisible(false);
            });

            letterPane.getChildren().add(letter[x]);
        }//for

    }//addLetters

    private void getWordTextFields(){
        for (TextField textField : word) wordTextFieldText = wordTextFieldText + textField.getText();
    }//getWordOfTextFields

    private void check(char letter){

        for(int x = 0; x < wordSelected.length(); x++){
        if(wordSelected.charAt(x) == letter){
            word[x].setText(letter+"");
            found = true;
        }//if
    }//for
        if (!found){
        lives--;
        switch (lives){
            case 5:
                head.setVisible(true);
                break;
            case 4:
                body.setVisible(true);
                break;
            case 3:
                leftHand.setVisible(true);
                break;
            case 2:
                rightHand.setVisible(true);
                break;
            case 1:
                leftLeg.setVisible(true);
                break;
            case 0:
                rightLeg.setVisible(true);
                break;
        }//switch
    }//if found
        found = false;

    win();

}//Check
    private void checkForKeyboard(char letter, int number){

        if(!(wordSelected.charAt(number) == letter)) {
        lives--;
        word[number].setText("");
        switch (lives){
            case 5:
                head.setVisible(true);
                break;
            case 4:
                body.setVisible(true);
                break;
            case 3:
                leftHand.setVisible(true);
                break;
            case 2:
                rightHand.setVisible(true);
                break;
            case 1:
                leftLeg.setVisible(true);
                break;
            case 0:
                rightLeg.setVisible(true);
                break;
        }//switch

    }//if found

        win();

    }//Check
    private void win(){
        getWordTextFields();

        if(wordSelected.equals(wordTextFieldText)) alertSent("We've a winner","Winner");
        else if(lives==0) alertSent("We've a loser","Loser");

        wordTextFieldText = "";
    }//checkForWinner

    private void alertSent(String title,String header){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setTitle(title);
        alert.show();
    }//sentAlert


}
