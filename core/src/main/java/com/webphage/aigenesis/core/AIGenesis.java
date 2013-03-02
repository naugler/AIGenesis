package com.webphage.aigenesis.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.InputAdapter;
import com.webphage.aigenesis.core.scenes.*;
import com.webphage.aigenesis.core.theater.*;

public class AIGenesis extends InputAdapter implements ApplicationListener, ActorEventObserver {

    public static final int DEFAULT_WIDTH = 320;
    public static final int DEFAULT_HEIGHT = 480;
    private SplashScene splashScene;
    private AboutScene aboutScene;
    private MenuScene menuScene;
    private SettingsScene settingsScene;
    private GameScene gameScene;
    private Director director;
    private final Input input = new Input();

    @Override
    public void create() {
        this.director = Director.instance();
        this.director.setWidth(DEFAULT_WIDTH);
        this.director.setHeight(DEFAULT_HEIGHT);

        director.registerEventHandler(this);

        TextureCache.instance().load(AppActorTextures.TEXTURES);

        menuScene = getMenuScene();

        this.director.setScene(menuScene);
    }

    public Input getInput() {
        return input;
    }

    /**
     * Generate scene.
     *
     * @return The target scene.
     */
    public AboutScene getAboutScene() {
        if (aboutScene == null) {
            aboutScene = new AboutScene();
        }

        return aboutScene;
    }

    /**
     * Generate scene.
     *
     * @return The target scene.
     */
    public SettingsScene getSettingsScene() {
        if (settingsScene == null) {
            settingsScene = new SettingsScene();
        }

        return settingsScene;
    }

    /**
     * Generate scene.
     *
     * @return The target scene.
     */
    public GameScene getGameScene() {
        if (gameScene == null) {
            gameScene = new GameScene();
        }

        return gameScene;
    }

    /**
     * Generate scene.
     *
     * @return The target scene.
     */
    public MenuScene getMenuScene() {
        if (menuScene == null) {
            menuScene = new MenuScene();
        }

        return menuScene;
    }

    /**
     * Generate scene.
     *
     * @return The target scene.
     */
    public SplashScene getSplashScene() {
        if (splashScene == null) {
            splashScene = new SplashScene();
        }

        return splashScene;
    }

    /**
     * Resize.
     *
     */
    @Override
    public void resize(int width, int height) {
        // Recalculate scale factors for touch events.
        director.recalcScaleFactors(width, height);
    }

    /**
     * Render view.
     *
     */
    @Override
    public void render() {
        // Update director
        director.update();
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
//        // Graphics.
//        TextureCache.instance().dispose();
//
//        // Sounds.
//        SoundCache.instance().dispose();

        // Graphics
        Director.instance().dispose();
    }

    /**
     * Handle screen events.
     *
     */
    @Override
    public boolean handleEvent(ActorEvent event) {
        boolean handled = false;

        switch (event.getId()) {
            case AppActorEvents.EVENT_DISPLAY_SPLASH_SCREEN:
                displaySplashScene();
                handled = true;
                break;
            case AppActorEvents.EVENT_TRANSITION_TO_MENU_SCENE:
                transitionToMenuScene();
                handled = true;
                break;
            case AppActorEvents.EVENT_TRANSITION_TO_SETTINGS_SCENE:
                transitionToSettingsScene();
                handled = true;
                break;
            case AppActorEvents.EVENT_TRANSITION_TO_GAME_SCENE:
                transitionToGameScene();
                handled = true;
                break;
            case AppActorEvents.EVENT_TRANSITION_TO_ABOUT_SCENE:
                transitionToAboutScene();
                handled = true;
                break;
            default:
                break;
        }

        return handled;
    }

    /**
     * Run transition.
     *
     */
    private void displaySplashScene() {
        this.director.setScene(getSplashScene());
    }

    /**
     * Run transition to menu.
     *
     */
    private void transitionToMenuScene() {
        Scene inScene = getMenuScene();
        Scene outScene = this.director.getScene();

//        TransitionScene transitionScene = null;
//        if (outScene instanceof GameScene) {
//            transitionScene = MoveInTTransitionScene.$(inScene, outScene, DURATION_MENU_TRANSITION, Linear.INOUT);
//        } else {
//            if (outScene instanceof SettingsScene) {
//                transitionScene = MoveInRTransitionScene.$(inScene, outScene, DURATION_SETTINGS_TRANSITION, Bounce.OUT);
//            } else {
//                transitionScene = MoveInLTransitionScene.$(inScene, outScene, DURATION_ABOUT_TRANSITION, Bounce.OUT);
//            }
//        }

        this.director.setScene(inScene);
    }

    /**
     * Run transition.
     *
     */
    private void transitionToSettingsScene() {
        Scene inScene = getSettingsScene();
        Scene outScene = this.director.getScene();

//        TransitionScene transitionScene = MoveInLTransitionScene.$(inScene, outScene, DURATION_SETTINGS_TRANSITION, Bounce.OUT);

        this.director.setScene(inScene);
    }

    /**
     * Run transition.
     *
     */
    private void transitionToGameScene() {
        // Reset scores.
//        AppStats.instance().resetScore();

        Scene inScene = getGameScene();
        Scene outScene = this.director.getScene();

//        TransitionScene transitionScene = MoveInBTransitionScene.$(inScene, outScene, DURATION_GAME_TRANSITION, Linear.INOUT);

        this.director.setScene(inScene);
    }

    /**
     * Run transition.
     *
     */
    private void transitionToAboutScene() {
        Scene inScene = getAboutScene();
        Scene outScene = this.director.getScene();

//        TransitionScene transitionScene = MoveInRTransitionScene.$(inScene, outScene, DURATION_ABOUT_TRANSITION, Bounce.OUT);

        this.director.setScene(inScene);
    }
}
