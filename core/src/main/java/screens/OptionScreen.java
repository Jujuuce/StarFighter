package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import game.StarFighter;
import patternCommand.ChangeScreenCommand;
import patternCommand.ChangeWindowCommand;
import random.AudioManager;

import java.util.Map;

/**
 * `OptionScreen` représente l'écran des options dans le jeu. Cet écran permet à l'utilisateur de modifier les paramètres
 * du jeu tels que les touches de contrôle, le mode fenêtre/plein écran, et le volume. Il gère également l'affichage des
 * éléments d'interface utilisateur (UI) et l'interaction avec ces éléments.
 */
public class OptionScreen extends MenuScreens {

    private boolean awaitingInput = false; // Suit si une touche est attendue
    private TextButton activeButton; // Bouton actuellement modifié
    private Label infoLabel; // Label d'information pour les entrées utilisateur

    /**
     * Constructeur de `OptionScreen`.
     * Initialise l'écran des options avec l'instance du jeu.
     *
     * @param game L'instance du jeu associée à cet écran.
     */
    public OptionScreen(StarFighter game) {
        super(game);
    }

    /**
     * Méthode exécutée lors de l'affichage de l'écran des options.
     * Crée les boutons et les éléments d'interface utilisateur nécessaires pour l'écran.
     */
    @Override
    public void show() {
        super.show();
        awaitingInput = false;

        // Création du bouton "Retour"
        if (getGame().isPaused()) {
            createButton(100, 100, "Back", new ChangeScreenCommand(this, "PauseScreen"));
        } else {
            createButton(100, 100, "Back", new ChangeScreenCommand(this, "MainMenuScreen"));
        }

        // Création du bouton pour passer entre plein écran et fenêtre
        if (getGame().getVisuals().isWindowed()) {
            createButton(100, 200, "Full Screen", new ChangeWindowCommand(this));
        } else {
            createButton(100, 200, "Windowed", new ChangeWindowCommand(this));
        }

        // Rendu du titre et de l'image StarFighter
        renderStarFighter();
        renderTitle("Options", 100, 500);

        // Création de la table pour les entrées de touches
        inputTable();

        // Création du slider pour le volume
        inputSlider();
    }

    /**
     * Crée et affiche les boutons pour chaque action du jeu qui peut être liée à une touche,
     * et permet à l'utilisateur de changer la touche associée à une action.
     */
    public void inputTable() {
        Table table = new Table();
        table.setFillParent(false);
        table.setPosition(Gdx.graphics.getWidth() * 0.7f, Gdx.graphics.getHeight() / 2f);

        getStage().addActor(table);

        // Label pour afficher les informations à propos de la touche active
        infoLabel = new Label("Click to change Key.", new Label.LabelStyle(getGame().getVisuals().getFont(), Color.WHITE));
        table.add(infoLabel).colspan(2).center().pad(10);
        table.row();

        // Création dynamique des boutons pour chaque action
        Map<String, String> inputs = getGame().getInputs(); // Récupère la Map des actions et des touches
        for (Map.Entry<String, String> entry : inputs.entrySet()) {
            String action = entry.getKey();
            String key = entry.getValue();

            TextButton button = new TextButton(action + " (" + key + ")", getSkin());
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!awaitingInput) {
                        awaitingInput = true;
                        activeButton = button;
                        infoLabel.setText("Tap a Key for : " + action);
                    }
                }
            });

            table.add(button).width(300).pad(10);
            table.row();
        }
    }

    /**
     * Crée et affiche un slider pour ajuster le volume du jeu.
     */
    public void inputSlider() {
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();

        // Définir les éléments du SliderStyle
        sliderStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Skins/SliderBar.bmp"))));
        sliderStyle.knob = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Skins/Slider.bmp"))));

        // Créer le Slider avec le SliderStyle personnalisé
        Slider volumeSlider = new Slider(0f, 1f, 0.01f, false, sliderStyle);
        AudioManager audioManager = AudioManager.getInstance();
        volumeSlider.setValue(audioManager.getGlobalVolume()); // Définir la valeur initiale du volume

        // Ajouter un écouteur pour changer le volume
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float volume = volumeSlider.getValue();
                audioManager.setGlobalVolume(volume);
            }
        });

        // Positionner et afficher le slider
        volumeSlider.setPosition(100, 300);
        volumeSlider.setSize(200, 50);
        getStage().addActor(volumeSlider);

        renderText("Volume", 130, 350);
    }

    /**
     * Méthode exécutée pour effectuer l'action de l'écran des options.
     *
     * @param game L'instance du jeu.
     * @return Le prochain écran à afficher.
     */
    @Override
    public String doAction(StarFighter game) {
        return getNextScreen();
    }

    /**
     * Rendu graphique de l'écran des options.
     * Vérifie si une touche est en attente et gère l'affichage et la mise à jour des éléments de l'interface.
     *
     * @param delta Le temps écoulé depuis le dernier rendu.
     */
    @Override
    public void render(float delta) {
        // Vérifie si une touche est en attente
        if (awaitingInput && activeButton != null) {
            for (int key = 0; key < 256; key++) {
                if (Gdx.input.isKeyJustPressed(key)) {
                    String action = activeButton.getText().toString().split(" ")[0]; // Action associée au bouton
                    String keyString = Input.Keys.toString(key); // Touche associée

                    // Vérifie si cette touche est déjà utilisée
                    if (!getGame().getInputs().containsValue(keyString)) {
                        // La touche n'est pas déjà utilisée, on l'assigne
                        getGame().getInputs().put(action, keyString); // Mise à jour de la map des entrées

                        // Mise à jour du texte du bouton
                        activeButton.setText(action + " (" + keyString + ")");
                        infoLabel.setText("Key saved for : " + action);
                        awaitingInput = false; // Quitte le mode d'attente
                        break;
                    } else {
                        // La touche est déjà utilisée, on affiche un message d'erreur
                        infoLabel.setText("Key already used for another action!");
                    }
                }
            }
        }

        // Efface l'écran
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        // Mise à jour et dessin des acteurs sur le stage
        getStage().act();
        getStage().draw();
    }
}


