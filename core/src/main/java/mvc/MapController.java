package mvc;

import gameModeStrategy.GameMode;

/**
 * Contrôleur de la carte qui gère le déplacement de la caméra sur la carte et la mise à jour de sa position.
 * Il contrôle la position de la caméra en fonction de la vitesse de déplacement et de la taille de la carte.
 */
public class MapController {
    private MapView mapView;
    private float posCamera;
    private float cameraSpeed = 2f;  // Vitesse de déplacement de la caméra

    /**
     * Constructeur de la classe `MapController`.
     *
     * @param model Le modèle de la carte qui contient les données de la carte.
     * @param mapView La vue de la carte qui gère l'affichage de la carte.
     */
    public MapController(MapModel model, MapView mapView) {
        this.mapView = mapView;
        this.posCamera = 20;  // Position initiale de la caméra
    }

    /**
     * Méthode pour déplacer la caméra sur la carte.
     * La position de la caméra est mise à jour en fonction du temps écoulé (deltaTime) et de la vitesse de déplacement.
     * La position de la caméra est également limitée pour ne pas dépasser la taille de la carte.
     *
     * @param infinite Si true, la caméra continuera à se déplacer sans limite. Si false, le mouvement est limité.
     * @param deltaTime Le temps écoulé entre deux images pour ajuster le déplacement de la caméra.
     */
    public void deplacement(boolean infinite, float deltaTime) {
        // Limiter le mouvement de la caméra en basant la position sur la taille de la carte
        if (posCamera + mapView.getCamera().viewportHeight / 2f > mapView.getMapHeight()) {
            posCamera = 20;  // Réinitialise la position de la caméra si on dépasse la hauteur de la carte
        } else {
            posCamera += cameraSpeed * deltaTime;  // Déplace la caméra en fonction du deltaTime
        }

        // Mettre à jour la position de la caméra
        mapView.getCamera().position.y = posCamera;
        mapView.getCamera().update();
    }

    // Setter

    /**
     * Définit la vue de la carte associée à ce contrôleur.
     *
     * @param mapView La vue de la carte.
     */
    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }

    /**
     * Définit la position actuelle de la caméra.
     *
     * @param posCamera La position de la caméra.
     */
    public void setPosCamera(float posCamera) {
        this.posCamera = posCamera;
    }

    /**
     * Définit la vitesse de déplacement de la caméra.
     *
     * @param speed La vitesse de déplacement de la caméra.
     */
    public void setCameraSpeed(float speed) {
        this.cameraSpeed = speed;
    }

    // Getter

    /**
     * Retourne la vue de la carte associée à ce contrôleur.
     *
     * @return La vue de la carte.
     */
    public MapView getMapView() {
        return mapView;
    }

    /**
     * Retourne la position actuelle de la caméra.
     *
     * @return La position de la caméra.
     */
    public float getPosCamera() {
        return posCamera;
    }

    /**
     * Retourne la vitesse actuelle de déplacement de la caméra.
     *
     * @return La vitesse de déplacement de la caméra.
     */
    public float getCameraSpeed() {
        return cameraSpeed;
    }
}

