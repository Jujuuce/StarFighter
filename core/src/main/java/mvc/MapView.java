package mvc;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import observer.*;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Vue représentant la carte dans le jeu. Cette classe est responsable du rendu de la carte et de la gestion de la caméra.
 * Elle est un observateur du modèle `MapModel` et est mise à jour lorsque la carte change.
 */
public class MapView implements Observer {
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private float mapHeight;

    /**
     * Constructeur de la classe `MapView`.
     * Ce constructeur initialise le rendu de la carte et configure la caméra pour l'affichage.
     *
     * @param model Le modèle `MapModel` qui contient les données de la carte.
     * @param map La carte (`TiledMap`) à afficher.
     */
    public MapView(MapModel model, TiledMap map) {
        model.add(this); // S'abonner aux notifications du modèle

        // Initialiser la carte et le rendu
        renderer = new OrthogonalTiledMapRenderer(map, 1 / 32f);
        camera = new OrthographicCamera();

        int tileWidth = map.getProperties().get("tilewidth", Integer.class);
        int tileHeight = map.getProperties().get("tileheight", Integer.class);
        int mapWidthInTiles = map.getProperties().get("width", Integer.class);
        int mapHeightInTiles = map.getProperties().get("height", Integer.class);
        this.mapHeight = mapHeightInTiles * tileHeight / 32f;

        // Configurer la caméra
        camera.setToOrtho(false, 30, 20);  // Par exemple, taille de la fenêtre de la caméra
        camera.update();
    }

    /**
     * Méthode appelée pour mettre à jour le rendu de la carte.
     * Cette méthode est appelée lorsqu'un changement survient dans le modèle (`MapModel`).
     */
    @Override
    public void update() {
        renderer.setView(camera);  // Mettre à jour la vue de la carte avec la caméra
        renderer.render();
    }

    // Setters

    /**
     * Définit la caméra utilisée pour afficher la carte.
     *
     * @param camera La caméra à utiliser.
     */
    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    /**
     * Définit la hauteur de la carte.
     *
     * @param mapHeight La hauteur de la carte en unités de jeu.
     */
    public void setMapHeight(float mapHeight) {
        this.mapHeight = mapHeight;
    }

    /**
     * Définit le rendu de la carte.
     *
     * @param renderer Le rendu de la carte.
     */
    public void setRenderer(OrthogonalTiledMapRenderer renderer) {
        this.renderer = renderer;
    }

    // Getters

    /**
     * Retourne la caméra utilisée pour afficher la carte.
     *
     * @return La caméra.
     */
    public OrthographicCamera getCamera() {
        return camera;
    }

    /**
     * Retourne la hauteur de la carte.
     *
     * @return La hauteur de la carte.
     */
    public float getMapHeight() {
        return mapHeight;
    }

    /**
     * Retourne le rendu de la carte.
     *
     * @return Le rendu de la carte.
     */
    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }
}

