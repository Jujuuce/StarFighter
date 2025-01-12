package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import entity.*;
import game.*;
import gameModeStrategy.ClassicMode;
import gameModeStrategy.GameMode;
import gameModeStrategy.InfiniteMode;
import mvc.*;
import java.util.*;
import enemyFactory.*;
import random.EntityManager;

import ui.UI;
import ui.UIController;
import ui.UIView;

/**
 * `GameScreen` représente l'écran principal du jeu où se déroule l'action. Il gère les entités du jeu telles que le joueur,
 * les ennemis, et le boss, ainsi que la logique du jeu (mode infini ou classique). Il gère également les collisions, les
 * mises à jour d'entités et la gestion de l'UI.
 */
public class GameScreen extends ScreenType {

    private MapModel mapModel;
    private MapController mapController;

    private UI ui;
    private UIController uiController;

    private Entity boss;
    private Entity player;
    private PlayerController playerController;

    private EntityManager entityManager;
    private GameMode gameMode;
    private Map<String, EnemyFactory> enemyFactories;

    /**
     * Constructeur de `GameScreen`.
     * Initialise l'écran principal du jeu avec l'instance du jeu.
     *
     * @param game L'instance du jeu associée à cet écran.
     */
    public GameScreen(StarFighter game) {
        super(game);
    }

    /**
     * Méthode exécutée lors de l'affichage de l'écran.
     * Initialise les composants nécessaires pour le jeu, y compris les entités, les ennemis et l'interface utilisateur.
     */
    public void show() {
        if (!getGame().isPaused()) {

            // Initialisation des factories d'ennemis
            enemyFactories = new HashMap<>();
            enemyFactories.put("enemy1", new EnemyType1Factory());
            enemyFactories.put("enemy2", new EnemyType2Factory());

            // Changement de musique
            getGame().stopMusic("menu");
            getGame().playMusic("game");

            // Initialisation du gestionnaire d'entités
            entityManager = new EntityManager();
            if (getGame().getLevel().equals("Endless.tmx")) {
                gameMode = new InfiniteMode();
            } else {
                gameMode = new ClassicMode();
            }
            mapModel = new MapModel(getGame().getLevel());
            MapView mapView = new MapView(mapModel, mapModel.getMap());
            mapController = new MapController(mapModel, mapView);

            // Initialisation du joueur
            player = new Player(500, 400, 100, getGame().getVisuals().getBatch());
            View view = new View(player);

            // Ajout du joueur au gestionnaire d'entités
            entityManager.addAlly(player);
            if (!gameMode.isInfinite()) {
                loadEnemiesFromMap();
            }

            // Initialisation de l'UI
            ui = new UI((Player) player, getGame().getVisuals().getBatch());
            UIView uiView = new UIView(ui, getGame().getVisuals());
            uiController = new UIController(ui, uiView);

            // Définition de l'état du jeu
            setFirstShow(false);
            setNextScreen(null);
        } else {
            getGame().setPaused(false);
        }

        // Initialisation du contrôleur du joueur
        playerController = new PlayerController(player, entityManager, this);
        Gdx.input.setCursorCatched(true);
    }

    /**
     * Charge les ennemis depuis la carte et les ajoute au gestionnaire d'entités.
     */
    private void loadEnemiesFromMap() {
        for (MapObject object : mapModel.getObjects()) {
            String enemyType = object.getName();
            float x = object.getProperties().get("x", Float.class);
            float y = object.getProperties().get("y", Float.class);

            if (enemyFactories.containsKey(enemyType)) {
                EnemyFactory factory = enemyFactories.get(enemyType);
                if (factory != null) {
                    Entity enemy = factory.createEntity(x, y, 10, getGame().getVisuals().getBatch());
                    View view = factory.createView(enemy);
                    Controller controllerEnemy = factory.createController(enemy, entityManager);

                    // Ajouter l'ennemi au modèle et aux listes
                    entityManager.addEnemy(enemy);
                    entityManager.addController(controllerEnemy);
                }
            }
        }
    }

    /**
     * Crée le boss du niveau actuel et l'ajoute au gestionnaire d'entités.
     */
    public void createBoss() {
        Map<String, EnemyFactory> bossFactories = new HashMap<>();
        bossFactories.put("Level1.tmx", new BossType1Factory());
        bossFactories.put("Level2.tmx", new BossType2Factory());
        bossFactories.put("Level3.tmx", new BossType2Factory());

        if (!bossFactories.containsKey(getGame().getLevel())) {
            getGame().setGameOverScore(player.getScore());
            setNextScreen("GameOverScreen");
        }
        EnemyFactory factoryBoss = bossFactories.get(getGame().getLevel());

        if (factoryBoss == null) {
            throw new IllegalArgumentException("No boss factory defined for level: " + getGame().getLevel());
        }

        boss = factoryBoss.createEntity(500, 900, 10, getGame().getVisuals().getBatch());
        View view = factoryBoss.createView(boss);
        Controller controllerEnemy = factoryBoss.createController(boss, entityManager);

        // Ajouter le boss au modèle et aux listes
        entityManager.addEnemy(boss);
        entityManager.addController(controllerEnemy);
    }

    /**
     * Vérifie les conditions de fin de jeu, telles que la défaite du boss ou la victoire du joueur.
     */
    public void endGame() {
        if (boss != null && !boss.isAlive()) {
            getGame().setGameOverScore(player.getScore());
            setNextScreen("GameOverScreen");
        }

        if (!gameMode.isInfinite() && entityManager.isEmptyEnemies()) {
            createBoss();
        }

        if (!player.isAlive()) {
            getGame().setGameOverScore(player.getScore());
            setNextScreen("GameOverScreen");
        }
    }

    /**
     * Vérifie les collisions entre les entités du jeu (alliés, ennemis, projectiles).
     */
    public void checkCollisions() {
        for (Entity ally : entityManager.getAllies()) {
            for (Entity enemy : entityManager.getEnemies()) {
                if (ally.overlaps(enemy)) {

                    if (ally instanceof Projectile && ((Projectile) ally).isAlly() && enemy instanceof Enemy) {
                        player.setScore(player.getScore() + 1);
                        ally.takeDamage(1);
                        enemy.takeDamage(1);
                    } else if (ally instanceof Player && enemy instanceof Projectile) {
                        player.changePos(enemy.getVect());
                        ally.takeDamage(1);
                        enemy.takeDamage(1);
                    } else if (ally instanceof Player && enemy instanceof Enemy) {
                        player.changePos(new Vector2(0,-20));
                        ally.takeDamage(1);
                    }
                }
            }
        }
    }

    /**
     * Met le jeu en pause et affiche l'écran de pause.
     */
    public void pause() {
        setNextScreen("PauseScreen");
    }

    /**
     * Rendu graphique de l'écran de jeu, mettant à jour les entités et les contrôles du jeu.
     *
     * @param delta Le temps écoulé depuis le dernier rendu.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapModel.update();

        mapController.deplacement(gameMode.isInfinite(), Gdx.graphics.getDeltaTime());

        gameMode.handleSpawning(entityManager, mapModel, getGame());

        endGame();

        entityManager.updateEntities();

        checkCollisions();

        entityManager.updateControllers();

        playerController.handleInput();

        ((Player) player).updateInvincibility();

        ui.update();
    }

    /**
     * Méthode exécutée pour effectuer l'action de l'écran de jeu.
     *
     * @param game L'instance du jeu.
     * @return Le prochain écran à afficher.
     */
    @Override
    public String doAction(StarFighter game) {
        return getNextScreen();
    }

    /**
     * Getter pour obtenir le gestionnaire d'entités.
     *
     * @return Le gestionnaire d'entités.
     */
    public EntityManager getLists() {
        return entityManager;
    }
}



