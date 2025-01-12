package screens;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import game.StarFighter;
import patternCommand.Command;

import java.util.*;

/**
 * Classe abstraite `MenuScreens` qui étend `ScreenType` et sert de base pour tous les écrans de type menu dans le jeu.
 * Elle gère la création d'UI, l'affichage d'arrière-plan, et la gestion des boutons interactifs.
 */
public abstract class MenuScreens extends ScreenType {
    private Stage stage;  // Conteneur des éléments d'interface utilisateur
    private Skin skin;    // Style des éléments de l'UI

    /**
     * Constructeur de la classe `MenuScreens`.
     * Initialise l'écran de menu avec l'instance du jeu.
     *
     * @param game L'instance du jeu associée à cet écran.
     */
    public MenuScreens(StarFighter game) {
        super(game);
    }

    /**
     * Méthode qui est appelée lorsque l'écran devient visible.
     * Initialise le stage, le skin, et définit l'arrière-plan de l'écran.
     */
    @Override
    public void show() {
        Gdx.input.setCursorCatched(false);  // Permet de capturer ou non le curseur
        stage = new Stage();
        skin = createSkin();  // Crée le skin pour l'UI
        setBackground();  // Définit l'arrière-plan de l'écran
        Gdx.input.setInputProcessor(stage);  // Définit le stage comme processeur d'entrées
    }

    /**
     * Crée un skin personnalisé pour l'UI avec des styles pour les boutons.
     *
     * @return Le skin créé.
     */
    private Skin createSkin() {
        Skin skin = new Skin();

        // Définir une police par défaut
        BitmapFont font = getGame().getVisuals().getFont();  // Police par défaut
        skin.add("default-font", font);

        // Créer les textures pour les états des boutons (normal, survol, clic)
        Pixmap pixmapUp = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmapUp.setColor(Color.GRAY);
        pixmapUp.fill();
        TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmapUp)));

        Pixmap pixmapOver = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmapOver.setColor(Color.DARK_GRAY);
        pixmapOver.fill();
        TextureRegionDrawable overDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmapOver)));

        Pixmap pixmapDown = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmapDown.setColor(Color.BLACK);
        pixmapDown.fill();
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmapDown)));

        // Définir le style du bouton
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;  // Police du bouton
        style.fontColor = Color.WHITE;  // Couleur du texte
        style.up = upDrawable;  // Fond par défaut
        style.over = overDrawable;  // Fond au survol
        style.down = downDrawable;  // Fond lors du clic

        skin.add("default", style);  // Ajouter le style au skin

        return skin;
    }

    /**
     * Crée un bouton et l'ajoute à l'écran.
     *
     * @param x La position x du bouton.
     * @param y La position y du bouton.
     * @param label Le texte à afficher sur le bouton.
     * @param command La commande à exécuter lors du clic.
     */
    public void createButton(int x, int y, String label, final Command command) {
        TextButton button = new TextButton(label, skin);
        button.setPosition(x, y);
        button.setSize(200, 50);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().playSound("click");  // Joue le son de clic
                command.execute();  // Exécute la commande du bouton
            }
        });
        stage.addActor(button);  // Ajoute le bouton au stage
    }

    /**
     * Définit l'arrière-plan de l'écran en utilisant une image.
     */
    public void setBackground() {
        Texture texture = new Texture(Gdx.files.internal("background.jpg"));  // Charge l'image
        Image background = new Image(texture);  // Crée un acteur Image

        background.setPosition(0, 0);  // Positionne l'image
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());  // Redimensionne l'image
        getStage().addActor(background);  // Ajoute l'image au stage
    }

    /**
     * Affiche du texte normal à une position donnée.
     *
     * @param text Le texte à afficher.
     * @param x La position x du texte.
     * @param y La position y du texte.
     */
    public void renderText(String text, float x, float y) {
        renderText(text, x, y, getGame().getVisuals().getFont());
    }

    /**
     * Affiche le titre du jeu à une position donnée.
     *
     * @param text Le texte à afficher.
     * @param x La position x du texte.
     * @param y La position y du texte.
     */
    public void renderTitle(String text, float x, float y) {
        renderText(text, x, y, getGame().getVisuals().getFontTitle());
    }

    /**
     * Affiche le titre "STAR FIGHTER" au centre de l'écran.
     */
    public void renderStarFighter() {
        renderText("STAR FIGHTER", 300, 600, getGame().getVisuals().getFontTitle());
    }

    /**
     * Affiche du texte à une position donnée avec une police spécifiée.
     *
     * @param text Le texte à afficher.
     * @param x La position x du texte.
     * @param y La position y du texte.
     * @param font La police à utiliser pour le texte.
     */
    public void renderText(String text, float x, float y, BitmapFont font) {
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);  // Style du texte
        Label label = new Label(text, style);
        label.setPosition(x, y);

        stage.addActor(label);  // Ajoute le texte au stage
    }

    /**
     * Méthode appelée lorsque l'écran devient caché. Elle réinitialise l'écran.
     */
    @Override
    public void hide() {
        setNextScreen(null);
        stage.clear();  // Efface tous les acteurs du stage
    }

    /**
     * Rendu de l'écran à chaque frame.
     * Met à jour et dessine les éléments de l'UI.
     *
     * @param delta Le temps écoulé depuis le dernier rendu.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);  // Définit la couleur de fond (noir)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  // Efface l'écran

        stage.act(delta);  // Met à jour le stage
        stage.draw();      // Dessine le stage
    }

    /**
     * Redimensionne le stage en fonction des nouvelles dimensions de la fenêtre.
     *
     * @param width La nouvelle largeur de la fenêtre.
     * @param height La nouvelle hauteur de la fenêtre.
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);  // Met à jour la vue du stage
    }

    // Getters et Setters

    /**
     * Retourne le stage utilisé pour l'UI.
     *
     * @return Le stage.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Retourne le skin utilisé pour le style de l'UI.
     *
     * @return Le skin.
     */
    public Skin getSkin() {
        return skin;
    }

    /**
     * Définit le stage utilisé pour l'UI.
     *
     * @param stage Le stage à définir.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Définit le skin utilisé pour le style de l'UI.
     *
     * @param skin Le skin à définir.
     */
    public void setSkin(Skin skin) {
        this.skin = skin;
    }
}


