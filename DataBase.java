package com.example.medicaldatabase;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.sql.*;

public class DataBase extends Application {
    private ObservableList<Medic> medicList = FXCollections.observableArrayList();
    private ObservableList<Pacient> pacientList = FXCollections.observableArrayList();
    private ObservableList<Programari> programariList = FXCollections.observableArrayList();
    private TableView currentTable;
    private VBox layout = new VBox(5);
    private BorderPane borderPane = new BorderPane();
    private TextField idProgramareInput = new TextField();
    private TextField idPacientInput = new TextField();
    private TextField idMedicInput = new TextField();
    private TextField dataOraInput = new TextField();
    private TextField motivInput = new TextField();
    private TextField noteInput = new TextField();
    private TextField numeInput = new TextField();
    private TextField prenumeInput = new TextField();
    private TextField dataNasteriiInput = new TextField();
    private TextField idnpInput = new TextField();
    private TextField sexInput = new TextField();
    private TextField adresaInput = new TextField();
    private TextField telefonInput = new TextField();
    private TextField grupaDeSangeInput = new TextField();
    private TextField alergiiInput = new TextField();
    private TextField specializareInput = new TextField();
    private TextField emailInput = new TextField();
    private Button addProgramareButton = new Button("Adauga Programare");
    private Button addPacientButton = new Button("Adauga Pacient");
    private Button addMedicButton = new Button("Adauga Medic");


    TableView<Pacient> table1 = createTablePacient();
    TableView<Medic> table2 = createTableMedic();
    TableView<Programari> table3 = createTableProgramari();

    @Override
    public void start(Stage stage) throws Exception {
        borderPane.setStyle("-fx-background-color: #ADD8E6;");

        Button btnTable1 = new Button("Pacienti");
        Button btnTable2 = new Button("Medici");
        Button btnTable3 = new Button("Programari");


        currentTable = table1;

        btnTable1.setOnAction(e -> switchTable(table1));
        btnTable2.setOnAction(e -> switchTable(table2));
        btnTable3.setOnAction(e -> switchTable(table3));

        HBox buttonBox = new HBox(10, btnTable1, btnTable2, btnTable3);
        buttonBox.setAlignment((Pos.CENTER));
        buttonBox.setPadding(new Insets(10));
        borderPane.setTop(buttonBox);

        idProgramareInput.setPromptText("ID Programare");
        idPacientInput.setPromptText("ID Pacient");
        idMedicInput.setPromptText("ID Medic");
        dataOraInput.setPromptText("Data si Ora");
        motivInput.setPromptText("Motivul");
        noteInput.setPromptText("Note");
        numeInput.setPromptText("Numele");
        prenumeInput.setPromptText("Prenumele");
        idnpInput.setPromptText("Idnp-ul");
        dataNasteriiInput.setPromptText("Data nasterii");
        sexInput.setPromptText("Sexul");
        adresaInput.setPromptText("Adresa");
        telefonInput.setPromptText("Telefonul");
        grupaDeSangeInput.setPromptText("Grupa de sange");
        alergiiInput.setPromptText("Alergii");
        emailInput.setPromptText("Email");
        specializareInput.setPromptText("Specializare");

        addProgramareButton.setOnAction(e -> addProgramare());
        addPacientButton.setOnAction(e -> addPacient());
        addMedicButton.setOnAction(e -> addMedic());


        layout.setAlignment((Pos.CENTER));
        layout.setPadding(new Insets(10));
        borderPane.setBottom(layout);

        loadDataBase();

        table1.setItems(pacientList);
        table2.setItems(medicList);
        table3.setItems(programariList);

        switchTable(table1);

        Scene scene = new Scene(borderPane, 1200, 800);
        stage.setScene(scene);
        stage.setTitle("Medical DataBase");
        stage.show();
    }

    private void addProgramare() {
        try {
            int idProgramare = Integer.parseInt(idProgramareInput.getText());
            int idPacient = Integer.parseInt(idPacientInput.getText());
            int idMedic = Integer.parseInt(idMedicInput.getText());
            String dataOra = dataOraInput.getText();
            String motiv = motivInput.getText();
            String note = noteInput.getText();

            Programari newProgramare = new Programari(idProgramare, idPacient, idMedic, dataOra, motiv, note);

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistem_de_gestionare_medical", "root", "Grigore.123");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO programari(ID_Programare, ID_Pacient, ID_Medic, DataOra, Motivul, Note) VALUES(?, ?, ?, ?, ?, ?);");
            pstmt.setInt(1, idProgramare);
            pstmt.setInt(2, idPacient);
            pstmt.setInt(3, idMedic);
            pstmt.setString(4, dataOra);
            pstmt.setString(5, motiv);
            pstmt.setString(6, note);
            pstmt.executeUpdate();
            conn.close();

            programariList.add(newProgramare);
        } catch (NumberFormatException e) {
            // Aici puteți gestiona cazul în care introducerea nu este un număr valid
            System.out.println("Te rog să introduci un număr valid pentru ID-uri.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        idProgramareInput.clear();
        idPacientInput.clear();
        idMedicInput.clear();
        dataOraInput.clear();
        motivInput.clear();
        noteInput.clear();
    }
    private void addMedic() {
        try {
            int idMedic = Integer.parseInt(idMedicInput.getText());
            String nume = numeInput.getText();
            String prenume = prenumeInput.getText();
            String specializare = specializareInput.getText();
            String telefon = telefonInput.getText();
            String email = emailInput.getText();

            Medic newMedic = new Medic(idMedic, nume, prenume, specializare, telefon, email);

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistem_de_gestionare_medical", "root", "Grigore.123");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO medic(ID, Nume, Prenume, Specializare, Telefon, Email) VALUES(?, ?, ?, ?, ?, ?);");
            pstmt.setInt(1, idMedic);
            pstmt.setString(2, nume);
            pstmt.setString(3, prenume);
            pstmt.setString(4, specializare);
            pstmt.setString(5, telefon);
            pstmt.setString(6, email);
            pstmt.executeUpdate();
            conn.close();

            medicList.add(newMedic);
        } catch (NumberFormatException e) {
            // Aici puteți gestiona cazul în care introducerea nu este un număr valid
            System.out.println("Te rog să introduci un număr valid pentru ID-uri.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        idMedicInput.clear();
        numeInput.clear();
        prenumeInput.clear();
        specializareInput.clear();
        telefonInput.clear();
        emailInput.clear();
    }
    private void addPacient() {
        try {
            int idPacient = Integer.parseInt(idPacientInput.getText());
            String idnp = idnpInput.getText();
            String nume = numeInput.getText();
            String prenume = prenumeInput.getText();
            String dataNasterii = dataNasteriiInput.getText();
            String sex = sexInput.getText();
            String adresa = adresaInput.getText();
            String telefon = telefonInput.getText();
            String grupaDeSange = grupaDeSangeInput.getText();
                String alergii = alergiiInput.getText();

            Pacient newPacient = new Pacient(idPacient, idnp, nume, prenume, dataNasterii, sex, adresa, telefon, grupaDeSange, alergii);

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistem_de_gestionare_medical", "root", "Grigore.123");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO pacient(ID, idnp,  Nume, Prenume, DataNasterii, Sex, Adresa, Telefon, GrupaDeSange, Alergii) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            pstmt.setInt(1, idPacient);
            pstmt.setString(2, idnp);
            pstmt.setString(3, nume);
            pstmt.setString(4, prenume);
            pstmt.setString(5, dataNasterii);
            pstmt.setString(6, sex);
            pstmt.setString(7, adresa);
            pstmt.setString(8, telefon);
            pstmt.setString(9, grupaDeSange);
            pstmt.setString(10, alergii);
            pstmt.executeUpdate();
            conn.close();

            pacientList.add(newPacient);
        } catch (NumberFormatException e) {
            // Aici puteți gestiona cazul în care introducerea nu este un număr valid
            System.out.println("Te rog să introduci un număr valid pentru ID-uri.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        idPacientInput.clear();
        idnpInput.clear();
        numeInput.clear();
        prenumeInput.clear();
        dataNasteriiInput.clear();
        sexInput.clear();
        adresaInput.clear();
        telefonInput.clear();
        grupaDeSangeInput.clear();
        alergiiInput.clear();
    }
    private void loadDataBase() {
        try {
            medicList.clear();
            pacientList.clear();
            programariList.clear();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistem_de_gestionare_medical", "root", "Grigore.123");
            System.out.println("Conexiunea la baza de date a fost stabilită cu succes.");

            Statement stmt = conn.createStatement();
            ResultSet med = stmt.executeQuery("SELECT * FROM medic");
            Statement stmt2 = conn.createStatement();
            ResultSet pac = stmt2.executeQuery("SELECT * FROM pacient");
            Statement stmt3 = conn.createStatement();
            ResultSet pro = stmt3.executeQuery("SELECT * FROM programari");


            while (med.next()) {
                Medic medic = new Medic(med.getInt("ID"), med.getString("Nume"), med.getString("Prenume"), med.getString("Specializare"),
                        med.getString("Telefon"), med.getString("Email"));
                medicList.add(medic);
            }
            while (pac.next()) {
                Pacient pacient = new Pacient(pac.getInt("ID"), pac.getString("idnp"), pac.getString("Nume"),
                        pac.getString("Prenume"), pac.getString("DataNasterii"), pac.getString("Sex"), pac.getString("Adresa"),
                        pac.getString("Telefon"), pac.getString("GrupaDeSange"), pac.getString("Alergii"));
                pacientList.add(pacient);
            }
            while (pro.next()) {
                Programari programari = new Programari(pro.getInt("ID_Programare"), pro.getInt("ID_Pacient"), pro.getInt("ID_Medic"),
                        pro.getString("DataOra"), pro.getString("Motivul"), pro.getString("Note"));
                programariList.add(programari);
            }

            conn.close();
        } catch (Exception e) {
            // Dacă o excepție este prinsă, înseamnă că a existat o problemă
            System.err.println("A apărut o eroare la stabilirea conexiunii cu baza de date.");
            e.printStackTrace();
        }
    }

    private void updateMedictInDatabase(Medic medic) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistem_de_gestionare_medical", "root", "Grigore.123");
            PreparedStatement pstmt = conn.prepareStatement("UPDATE medic SET Nume=?, Prenume=?, Specializare=?, Telefon=?, Email=? WHERE ID=?");
            pstmt.setString(1, medic.getNume());
            pstmt.setString(2, medic.getPrenume());
            pstmt.setString(3, medic.getSpecializare());
            pstmt.setString(4, medic.getTelefon());
            pstmt.setString(5, medic.getEmail());
            pstmt.setInt(6, medic.getId());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updatePacientInDatabase(Pacient pacient) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistem_de_gestionare_medical", "root", "Grigore.123");
            PreparedStatement pstmt = conn.prepareStatement("UPDATE pacient SET idnp=?, Nume=?, Prenume=?, DataNasterii=?, Sex=?, Adresa=?, Telefon=?, GrupaDeSange=?, Alergii=? WHERE ID=?");
            pstmt.setString(1, pacient.getIdnp());
            pstmt.setString(2, pacient.getNume());
            pstmt.setString(3, pacient.getPrenume());
            pstmt.setString(4, pacient.getDataNasterii());
            pstmt.setString(5, pacient.getSex());
            pstmt.setString(6, pacient.getAdresa());
            pstmt.setString(7, pacient.getTelefon());
            pstmt.setString(8, pacient.getGrupaDeSange());
            pstmt.setString(9, pacient.getAlergii());
            pstmt.setInt(10, pacient.getId());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateProgramariInDatabase(Programari programari) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistem_de_gestionare_medical", "root", "Grigore.123");
            PreparedStatement pstmt = conn.prepareStatement("UPDATE programari SET ID_Pacient=?, ID_Medic=?, DataOra=?, Motivul=?, Note=? WHERE ID_Programare=?");
            pstmt.setInt(1, programari.getIdPacient());
            pstmt.setInt(2, programari.getIdMedic());
            pstmt.setString(3, programari.getDataOra());
            pstmt.setString(4, programari.getMotiv());
            pstmt.setString(5, programari.getNote());
            pstmt.setInt(6, programari.getIdProgramare());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void switchTable(TableView newTable) {
        currentTable = newTable;
        borderPane.setCenter(newTable);
        currentTable.setEditable(true);
        currentTable.setMaxSize(1010, 800);

        layout.getChildren().clear();

        if (currentTable == table1) {
            layout.getChildren().addAll(idPacientInput, idnpInput, numeInput, prenumeInput, dataNasteriiInput, sexInput, adresaInput, telefonInput, grupaDeSangeInput, alergiiInput, addPacientButton);
        } else if (currentTable == table2) {
            layout.getChildren().addAll(idMedicInput, numeInput, prenumeInput, specializareInput, telefonInput, emailInput, addMedicButton);
        } else if (currentTable == table3) {
            layout.getChildren().addAll(idProgramareInput, idPacientInput, idMedicInput, dataOraInput, motivInput, noteInput, addProgramareButton);
        }
    }

    private TableView<Medic> createTableMedic() {
        TableView table = new TableView<>();


        TableColumn<Medic, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        idColumn.setOnEditCommit((TableColumn.CellEditEvent<Medic, Integer> event) -> {
            Medic medic = event.getRowValue();
            medic.setId(event.getNewValue());
            updateMedictInDatabase(medic);
        });

        TableColumn<Medic, String> numeColumn = new TableColumn<>("Nume");
        numeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        numeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        numeColumn.setOnEditCommit((TableColumn.CellEditEvent<Medic, String> event) -> {
            Medic medic = event.getRowValue();
            medic.setNume(event.getNewValue());
            updateMedictInDatabase(medic);
        });

        TableColumn<Medic, String> prenumeColumn = new TableColumn<>("Prenume");
        prenumeColumn.setCellValueFactory(new PropertyValueFactory<>("prenume"));
        prenumeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        prenumeColumn.setOnEditCommit((TableColumn.CellEditEvent<Medic, String> event) -> {
            Medic medic = event.getRowValue();
            medic.setPrenume(event.getNewValue());
            updateMedictInDatabase(medic);
        });

        TableColumn<Medic, String> specializareColumn = new TableColumn<>("Specializare");
        specializareColumn.setCellValueFactory(new PropertyValueFactory<>("specializare"));
        specializareColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        specializareColumn.setOnEditCommit((TableColumn.CellEditEvent<Medic, String> event) -> {
            Medic medic = event.getRowValue();
            medic.setSpecializare(event.getNewValue());
            updateMedictInDatabase(medic);
        });


        TableColumn<Medic, String> telefonColumn = new TableColumn<>("Telefon");
        telefonColumn.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        telefonColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        telefonColumn.setOnEditCommit((TableColumn.CellEditEvent<Medic, String> event) -> {
            Medic medic = event.getRowValue();
            medic.setTelefon(event.getNewValue());
            updateMedictInDatabase(medic);
        });

        TableColumn<Medic, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        emailColumn.setOnEditCommit((TableColumn.CellEditEvent<Medic, String> event) -> {
            Medic medic = event.getRowValue();
            medic.setEmail(event.getNewValue());
            updateMedictInDatabase(medic);
        });

        table.getColumns().addAll(idColumn, numeColumn, prenumeColumn, specializareColumn,telefonColumn, emailColumn);

        return table;
    }

    private TableView<Pacient> createTablePacient() {
        TableView table = new TableView<>();

        TableColumn<Pacient, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        idColumn.setOnEditCommit((TableColumn.CellEditEvent<Pacient, Integer> event) -> {
            Pacient pacient = event.getRowValue();
            pacient.setId(event.getNewValue());
            updatePacientInDatabase(pacient);
        });

        TableColumn<Pacient, String> idnpColumn = new TableColumn<>("Idnp");
        idnpColumn.setCellValueFactory(new PropertyValueFactory<>("idnp"));
        idnpColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        idnpColumn.setOnEditCommit((TableColumn.CellEditEvent<Pacient, String> event) -> {
            Pacient pacient = event.getRowValue();
            pacient.setNume(event.getNewValue());
            updatePacientInDatabase(pacient);
        });

        TableColumn<Pacient, String> numeColumn = new TableColumn<>("Nume");
        numeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        numeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        numeColumn.setOnEditCommit((TableColumn.CellEditEvent<Pacient, String> event) -> {
            Pacient pacient = event.getRowValue();
            pacient.setNume(event.getNewValue());
            updatePacientInDatabase(pacient);
        });

        TableColumn<Pacient, String> prenumeColumn = new TableColumn<>("Prenume");
        prenumeColumn.setCellValueFactory(new PropertyValueFactory<>("prenume"));
        prenumeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        prenumeColumn.setOnEditCommit((TableColumn.CellEditEvent<Pacient, String> event) -> {
            Pacient pacient = event.getRowValue();
            pacient.setPrenume(event.getNewValue());
            updatePacientInDatabase(pacient);
        });

        TableColumn<Pacient, String> dataNasteriiColumn = new TableColumn<>("DataNasterii");
        dataNasteriiColumn.setCellValueFactory(new PropertyValueFactory<>("dataNasterii"));
        dataNasteriiColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        dataNasteriiColumn.setOnEditCommit((TableColumn.CellEditEvent<Pacient, String> event) -> {
            Pacient pacient = event.getRowValue();
            pacient.setDataNasterii(event.getNewValue());
            updatePacientInDatabase(pacient);
        });

        TableColumn<Pacient, String> sexColumn = new TableColumn<>("Sex");
        sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        sexColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        sexColumn.setOnEditCommit((TableColumn.CellEditEvent<Pacient, String> event) -> {
            Pacient pacient = event.getRowValue();
            pacient.setSex(event.getNewValue());
            updatePacientInDatabase(pacient);
        });

        TableColumn<Pacient, String> adresaColumn = new TableColumn<>("Adresa");
        adresaColumn.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        adresaColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        adresaColumn.setOnEditCommit((TableColumn.CellEditEvent<Pacient, String> event) -> {
            Pacient pacient = event.getRowValue();
            pacient.setAdresa(event.getNewValue());
            updatePacientInDatabase(pacient);
        });

        TableColumn<Pacient, String> telefonColumn = new TableColumn<>("Telefon");
        telefonColumn.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        telefonColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        telefonColumn.setOnEditCommit((TableColumn.CellEditEvent<Pacient, String> event) -> {
            Pacient pacient = event.getRowValue();
            pacient.setTelefon(event.getNewValue());
            updatePacientInDatabase(pacient);
        });

        TableColumn<Pacient, String> grupaDeSangeColumn = new TableColumn<>("Grupa de sange");
        grupaDeSangeColumn.setCellValueFactory(new PropertyValueFactory<>("grupaDeSange"));
        grupaDeSangeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        grupaDeSangeColumn.setOnEditCommit((TableColumn.CellEditEvent<Pacient, String> event) -> {
            Pacient pacient = event.getRowValue();
            pacient.setGrupaDeSange(event.getNewValue());
            updatePacientInDatabase(pacient);
        });

        TableColumn<Pacient, String> alergiiColumn = new TableColumn<>("Alergii");
        alergiiColumn.setCellValueFactory(new PropertyValueFactory<>("alergii"));
        alergiiColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        alergiiColumn.setOnEditCommit((TableColumn.CellEditEvent<Pacient, String> event) -> {
            Pacient pacient = event.getRowValue();
            pacient.setAlergii(event.getNewValue());
            updatePacientInDatabase(pacient);
        });
        table.getColumns().addAll(idColumn,idnpColumn, numeColumn, prenumeColumn, dataNasteriiColumn, sexColumn, adresaColumn,
                telefonColumn, grupaDeSangeColumn, alergiiColumn);
        return table;
    }

    private TableView<Programari> createTableProgramari() {
        TableView table = new TableView<>();

        TableColumn<Programari, Integer> idProgramareColumn = new TableColumn<>("ID Programare");
        idProgramareColumn.setCellValueFactory(new PropertyValueFactory<>("idProgramare"));
        idProgramareColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        idProgramareColumn.setOnEditCommit((TableColumn.CellEditEvent<Programari, Integer> event) -> {
            Programari programari = event.getRowValue();
            programari.setIdProgramare(event.getNewValue());
            updateProgramariInDatabase(programari);
        });

        TableColumn<Programari, Integer> idPacientColumn = new TableColumn<>("ID Pacient");
        idPacientColumn.setCellValueFactory(new PropertyValueFactory<>("idPacient"));
        idPacientColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        idPacientColumn.setOnEditCommit((TableColumn.CellEditEvent<Programari, Integer> event) -> {
            Programari programari = event.getRowValue();
            programari.setIdPacient(event.getNewValue());
            updateProgramariInDatabase(programari);
        });

        TableColumn<Programari, Integer> idMedicColumn = new TableColumn<>("ID Medic");
        idMedicColumn.setCellValueFactory(new PropertyValueFactory<>("idMedic"));
        idMedicColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        idPacientColumn.setOnEditCommit((TableColumn.CellEditEvent<Programari, Integer> event) -> {
            Programari programari = event.getRowValue();
            programari.setIdMedic(event.getNewValue());
            updateProgramariInDatabase(programari);
        });

        TableColumn<Programari, String> dataOraColumn = new TableColumn<>("Data si ora");
        dataOraColumn.setCellValueFactory(new PropertyValueFactory<>("dataOra"));
        dataOraColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        dataOraColumn.setOnEditCommit((TableColumn.CellEditEvent<Programari, String> event) -> {
            Programari programari = event.getRowValue();
            programari.setDataOra(event.getNewValue());
            updateProgramariInDatabase(programari);
        });

        TableColumn<Programari, String> motivColumn = new TableColumn<>("Motivul");
        motivColumn.setCellValueFactory(new PropertyValueFactory<>("motiv"));
        motivColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        motivColumn.setOnEditCommit((TableColumn.CellEditEvent<Programari, String> event) -> {
            Programari programari = event.getRowValue();
            programari.setMotiv(event.getNewValue());
            updateProgramariInDatabase(programari);
        });

        TableColumn<Programari, String> noteColumn = new TableColumn<>("Note");
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
        noteColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        noteColumn.setOnEditCommit((TableColumn.CellEditEvent<Programari, String> event) -> {
            Programari programari = event.getRowValue();
            programari.setNote(event.getNewValue());
            updateProgramariInDatabase(programari);
        });

        table.getColumns().addAll(idProgramareColumn, idMedicColumn, idPacientColumn, dataOraColumn, motivColumn, noteColumn);
        return table;
    }


    public static void main(String[] args) {
        launch(args);
    }
}