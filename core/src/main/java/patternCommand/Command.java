package patternCommand;

/**
 * Interface représentant une commande dans le cadre du patron de conception Command.
 * Chaque implémentation de cette interface représente une action ou un ensemble d'actions spécifiques
 * qui peuvent être exécutées de manière encapsulée.
 * <p>
 * L'objectif de cette interface est de permettre une exécution différée ou contrôlée de commandes dans
 * un système, en séparant l'invocation d'une action de sa logique réelle.
 * </p>
 */
public interface Command {

    /**
     * Exécute la commande.
     * Cette méthode permet à chaque implémentation de définir la logique d'exécution de la commande.
     * Par exemple, une commande pourrait effectuer des actions comme déplacer un objet, modifier un état,
     * ou lancer une animation.
     */
    void execute();
}
