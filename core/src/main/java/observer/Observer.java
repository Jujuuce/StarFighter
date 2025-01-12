package observer;

/**
 * Interface représentant un observateur dans le cadre du patron de conception Observateur.
 * Les classes qui implémentent cette interface doivent définir la méthode `update()`, qui est appelée
 * lorsqu'un objet observable notifie un changement d'état.
 */
public interface Observer {

    /**
     * Méthode appelée pour notifier l'observateur d'un changement d'état.
     * Cette méthode doit être définie par les classes qui implémentent l'interface.
     */
    public void update();
}
