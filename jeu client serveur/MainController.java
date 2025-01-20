package dirogue.example.controllers;

import dirogue.example.App;
import dirogue.example.view.MainView;
import dirogue.example.view.ViewBase;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.*;

/**
 * Contrôleur principal pour la vue principale de l'application.
 * Ce contrôleur gère les interactions et les actions liées à la vue principale
 * de l'application, telles que le chargement de fichiers et la gestion des actions
 * liées aux boutons de la vue principale.
 *
 * Le contrôleur permet de charger un fichier texte et d'afficher son contenu dans
 * la vue principale. Il permet également de passer à une vue de replay après
 * avoir chargé un fichier.
 */
public class MainController extends ControllerBase {

    /**
     * Vue spécifique utilisée pour la vue principale.
     */
    private MainView mainView;

    /**
     * Constructeur pour le contrôleur principal.
     *
     * @param view La vue associée à ce contrôleur.
     */
    public MainController(ViewBase view) {
        super(view, null);
    }

    /**
     * Méthode d'initialisation du contrôleur principal.
     * Cette méthode configure les actions des boutons de la vue principale.
     */
    @Override
    protected void initialize() {
        this.mainView = (MainView) super.view;

        Button loadButton = mainView.getLoadButton();
        Button replayButton = mainView.getReplayButton();

        loadButton.setOnAction(event -> loadTextFile());

        replayButton.setOnAction(e -> {
            App.showView("Replay", mainView.getTextArea().getText());
        });
        replayButton.setDisable(true);
    }

    /**
     * Méthode privée pour charger un fichier texte dans la vue principale.
     * Cette méthode est déclenchée lorsqu'un utilisateur appuie sur le bouton de
     * chargement (load).
     *
     * Elle permet à l'utilisateur de choisir un fichier avec un {@link FileChooser},
     * puis affiche le contenu du fichier dans la zone de texte de la vue principale.
     * Après le chargement du fichier, le bouton "replay" est activé pour permettre
     * de passer à l'étape suivante.
     */
    private void loadTextFile() {


        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load report");
        File selectedFile = fileChooser.showOpenDialog(mainView.getRoot().getScene().getWindow());
        if (selectedFile != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }

                mainView.getTextArea().setText(content.toString());

                mainView.getReplayButton().setDisable(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun fichier sélectionné."); }

    }
    }
