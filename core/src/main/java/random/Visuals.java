package random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Classe responsable de la gestion des visuels du jeu, y compris le rendu des formes, le texte et la gestion du mode fenêtre/plein écran.
 * Elle permet de configurer les polices, le curseur personnalisé, et de gérer l'affichage en mode fenêtre ou en plein écran.
 */
public class Visuals {
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private SpriteBatch batch;
    private BitmapFont font20;
    private BitmapFont font40;
    private BitmapFont font60;
    private BitmapFont font80;
    private boolean windowed = true;

    /**
     * Constructeur par défaut pour initialiser les éléments visuels.
     * Crée un `ShapeRenderer`, un `SpriteBatch`, et les polices de caractères pour différents tailles.
     * Charge également l'image d'un curseur personnalisé et l'applique au système.
     */
    public Visuals() {
        this.shapeRenderer = new ShapeRenderer();
        this.font = new BitmapFont(); // Par défaut, une police bitmap est utilisée
        this.font.setColor(Color.WHITE);
        this.font.getData().setScale(1.5f);
        batch = new SpriteBatch();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("police.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        font20 = generator.generateFont(parameter);
        parameter.size = 40;
        font40 = generator.generateFont(parameter);
        parameter.size = 60;
        font60 = generator.generateFont(parameter);
        parameter.size = 80;
        font80 = generator.generateFont(parameter);
        generator.dispose(); // Libérer les ressources du générateur

        // Charger l'image du curseur
        Pixmap pixmap = new Pixmap(Gdx.files.internal("Skins/cursor.png"));
        if (pixmap.getWidth() > 32 || pixmap.getHeight() > 32) {
            Pixmap resizedPixmap = new Pixmap(32, 32, pixmap.getFormat());
            resizedPixmap.drawPixmap(pixmap, 0, 0, pixmap.getWidth(), pixmap.getHeight(), 0, 0, 32, 32);
            pixmap.dispose(); // Libérer l'ancien Pixmap
            pixmap = resizedPixmap; // Utiliser le Pixmap redimensionné
        }

        // Créer le curseur avec un point actif (hotspot)
        int hotspotX = 0;
        int hotspotY = 0;
        Cursor customCursor = Gdx.graphics.newCursor(pixmap, hotspotX, hotspotY);

        // Appliquer le curseur personnalisé
        Gdx.graphics.setCursor(customCursor);

        // Libérer les ressources du Pixmap
        pixmap.dispose();
    }

    /**
     * Rend du texte à l'écran à une position donnée.
     *
     * @param text Le texte à afficher.
     * @param x La position X pour l'affichage du texte.
     * @param y La position Y pour l'affichage du texte.
     */
    public void renderText(String text, float x, float y) {
        getFont().draw(batch, text, x, y);
    }

    // Setters

    /**
     * Définit le `ShapeRenderer` à utiliser pour dessiner des formes.
     *
     * @param shapeRenderer Le `ShapeRenderer` à utiliser.
     */
    public void setShapeRenderer(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }

    /**
     * Définit la police à utiliser pour le rendu du texte.
     *
     * @param font La police à utiliser.
     */
    public void setFont(BitmapFont font) {
        this.font = font;
    }

    /**
     * Définit le `SpriteBatch` à utiliser pour dessiner les sprites.
     *
     * @param batch Le `SpriteBatch` à utiliser.
     */
    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    /**
     * Définit si le jeu est en mode fenêtre.
     */
    public void setWindowed() {
        Gdx.graphics.setWindowedMode(1000, 800); // Passer en mode fenêtre
        this.windowed = true;
    }

    /**
     * Définit si le jeu est en mode plein écran.
     */
    public void setFullScreen() {
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        this.windowed = false;
    }

    // Getters

    /**
     * Retourne le `ShapeRenderer` utilisé pour dessiner des formes.
     *
     * @return Le `ShapeRenderer` utilisé pour dessiner des formes.
     */
    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    /**
     * Retourne la police actuellement utilisée pour le texte. Utilise une taille de police différente en fonction du mode (fenêtre ou plein écran).
     *
     * @return La police actuellement utilisée.
     */
    public BitmapFont getFont() {
        if (windowed) {
            return font20;
        } else {
            return font40;
        }
    }

    /**
     * Retourne la police utilisée pour le titre, avec une taille de police différente en fonction du mode.
     *
     * @return La police utilisée pour le titre.
     */
    public BitmapFont getFontTitle() {
        if (windowed) {
            return font60;
        } else {
            return font80;
        }
    }

    /**
     * Retourne le `SpriteBatch` utilisé pour dessiner les sprites.
     *
     * @return Le `SpriteBatch` utilisé pour dessiner les sprites.
     */
    public SpriteBatch getBatch() {
        return batch;
    }

    /**
     * Retourne si le jeu est actuellement en mode fenêtre.
     *
     * @return true si le jeu est en mode fenêtre, false si le jeu est en mode plein écran.
     */
    public boolean isWindowed() {
        return windowed;
    }
}

