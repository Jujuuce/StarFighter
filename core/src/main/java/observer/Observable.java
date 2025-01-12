package observer;

import java.util.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite représentant un objet observable dans le cadre du patron de conception Observateur.
 * Les objets qui héritent de cette classe peuvent être observés par des instances de la classe `Observer`.
 */
public abstract class Observable {
    private List<Observer> observers = new ArrayList<>();

    /**
     * Ajoute un observateur à la liste des observateurs.
     *
     * @param observer l'observateur à ajouter
     */
    public void add(Observer observer) {
        observers.add(observer);
    }

    /**
     * Supprime un observateur spécifique de la liste des observateurs.
     *
     * @param observer l'observateur à supprimer
     */
    public void del(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Supprime un observateur à partir de son indice dans la liste.
     *
     * @param observer l'indice de l'observateur à supprimer
     */
    public void del(int observer) {
        observers.remove(observer);
    }

    /**
     * Supprime tous les observateurs de la liste.
     */
    public void delObservers() {
        observers.clear();
    }

    /**
     * Notifie tous les observateurs enregistrés en appelant leur méthode `update()`.
     * Cette méthode est généralement appelée lorsqu'un changement d'état se produit.
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
