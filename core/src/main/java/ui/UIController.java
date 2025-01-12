package ui;

import mvc.MapModel;
import mvc.MapView;

public class UIController {
    private final UI ui;          // Référence à l'objet UI qui contient les données du joueur
    private final UIView uiView;  // Référence à la vue qui gère l'affichage des informations

    /**
     * Constructeur pour initialiser le contrôleur UI avec l'UI et la vue.
     *
     * @param ui L'objet UI contenant les données du joueur.
     * @param uiView La vue qui affiche les données à l'écran.
     */
    public UIController(UI ui, UIView uiView) {
        this.ui = ui;
        this.uiView = uiView;
    }

}
