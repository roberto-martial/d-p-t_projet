package dirogue.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

/**
 * Vue pour le mode de replay.
 * Cette vue permet la visualisation d'un rapport sauvegardé avec des
 * fonctionnalités de lecture.
 */
public class ReplayView extends ViewBase {
    /**
     * Conteneur principal de la vue de replay.
     */
    private VBox rootPane;

    /**
     * Label pour afficher le message associé à la rencontre en cours.
     */
    private Label messageLabel;

    /**
     * ImageView pour afficher l'image correspondant à la rencontre en cours.
     */
    private ImageView imageView;

    /**
     * Bouton pour passer à la rencontre suivante dans le rapport.
     */
    private Button forwardButton;

    /**
     * Bouton pour revenir à la rencontre précédente dans le rapport.
     */
    private Button backwardButton;

    /**
     * Bouton pour quitter le mode de replay.
     */
    private Button exitButton;

    /**
     * Constructeur par défaut pour la vue de replay.
     */
    public ReplayView() {
        super();
    }

    /**
     * Retourne le nom de la vue de replay.
     *
     * @return Le nom de la vue de replay ("Replay").
     */
    @Override
    public String getName() {
        return "Replay";
    }

    /**
     * Renvoie la racine (root) de la vue de replay.
     *
     * @return La racine (root) de la vue de replay.
     */
    @Override
    public Parent getRoot() {
        return rootPane;
    }

    /**
     * Renvoie le label pour afficher le message de la rencontre.
     *
     * @return Le label pour afficher le message.
     */
    public Label getMessageLabel() {
        return messageLabel;
    }

    /**
     * Renvoie l'ImageView pour afficher l'image de la rencontre.
     *
     * @return L'ImageView pour afficher l'image.
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Renvoie le bouton pour passer à la rencontre suivante.
     *
     * @return Le bouton pour avancer dans le rapport.
     */
    public Button getForwardButton() {
        return forwardButton;
    }

    /**
     * Renvoie le bouton pour revenir à la rencontre précédente.
     *
     * @return Le bouton pour reculer dans le rapport.
     */
    public Button getBackwardButton() {
        return backwardButton;
    }

    /**
     * Renvoie le bouton pour quitter le mode de replay.
     *
     * @return Le bouton pour sortir du mode replay.
     */
    public Button getExitButton() {
        return exitButton;
    }


    /**
     * Crée et configure l'interface utilisateur pour l'application.
     *
     * Cette méthode initialise les éléments graphiques de la vue, tels que les
     * boutons de navigation, l'affichage du message et l'affichage de l'image.
     * Elle configure également la disposition des éléments à l'aide de boîtes
     * horizontales et verticales (HBox et VBox) et ajuste les tailles et les
     * espacements pour obtenir une interface utilisateur cohérente.
     */
    @Override
    protected void createUI() {
        // Créer la racine de la vue
        rootPane = new VBox(10);
        rootPane.setPadding(new Insets(10));

        // Initialiser les éléments graphiques
        messageLabel = new Label("Message de la rencontre");
        imageView = new ImageView(); // Vous pouvez aussi charger une image ici
        forwardButton = new Button("Suivant");
        backwardButton = new Button("Précédent");
        exitButton = new Button("Quitter");

        // Ajuster les tailles
        imageView.setFitWidth(200);  /// Taille de l'image
        imageView.setFitHeight(200); /// Taille de l'image

        forwardButton.setPrefWidth(100);  /// Largeur des boutons
        backwardButton.setPrefWidth(100);
        exitButton.setPrefWidth(100);


        HBox imageBox = new HBox(imageView);
        imageBox.setAlignment(Pos.CENTER);  /// Centrer l'image


        HBox messageBox = new HBox(messageLabel);
        messageBox.setAlignment(Pos.CENTER);  /// Centrer le message


        HBox buttonBox = new HBox(10, backwardButton, forwardButton, exitButton);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);


        rootPane.getChildren().addAll(messageBox, imageBox, buttonBox);


}}
