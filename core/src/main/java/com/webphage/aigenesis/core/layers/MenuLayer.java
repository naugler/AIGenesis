/*
 * -----------------------------------------------------------------------
 * Copyright 2012 - Alistair Rutherford - www.netthreads.co.uk
 * -----------------------------------------------------------------------
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.webphage.aigenesis.core.layers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.webphage.aigenesis.core.theater.AppActorEvents;
import com.webphage.aigenesis.core.theater.Director;
import com.webphage.aigenesis.core.theater.Layer;

/**
 * Scene layer.
 *
 */
public class MenuLayer extends Layer {

    private static final String UI_FILE = "data/uiskin60.json";
    private static final String UI_SKIN = "data/uiskin60.png";
    private Table table;
    private Skin skin;
    private Label titleLabel;
    private Button startButton;
    private Button settingsButton;
    private Button aboutButton;
    // Director of the action.
    private Director director;

    /**
     * Construct the screen.
     *
     * @param stage
     */
    public MenuLayer(Stage stage) {
        setWidth(stage.getWidth());
        setHeight(stage.getHeight());

        this.director = Director.instance();

        Gdx.input.setCatchBackKey(true);

        loadTextures();

        buildElements();
    }

    /**
     * Load view textures.
     *
     */
    private void loadTextures() {
//            TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal(UI_FILE));
//            skin = new Skin(Gdx.files.internal(UI_SKIN), textureAtlas);
        skin = new Skin();
    }

    /**
     * Build view elements.
     *
     */
    private void buildElements() {
        // Title
        BitmapFont font = new BitmapFont();
        LabelStyle labelStyle = new LabelStyle(font, Color.YELLOW);
        titleLabel = new Label("DEMO", labelStyle);

        // ---------------------------------------------------------------
        // Buttons.
        // ---------------------------------------------------------------
        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.font = font;
        startButton = new TextButton("Start", buttonStyle);
        settingsButton = new TextButton("Settings", buttonStyle);
        aboutButton = new TextButton("About", buttonStyle);

        // ---------------------------------------------------------------
        // Table
        // ---------------------------------------------------------------
        table = new Table();

        table.size((int) getWidth(), (int) getHeight());

        table.row();
        table.add(titleLabel).expandY().expandX();
        table.row();
        table.add(startButton).expandY().expandX();
        table.row();
        table.add(settingsButton).expandY().expandX();
        table.row();
        table.add(aboutButton).expandY().expandX();

        // Listener.
        startButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("howdy","howdy");
                director.sendEvent(AppActorEvents.EVENT_TRANSITION_TO_GAME_SCENE, event.getRelatedActor());
            }
        });

        // Listener.
        settingsButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                director.sendEvent(AppActorEvents.EVENT_TRANSITION_TO_SETTINGS_SCENE, event.getRelatedActor());
            }
        });

        // Listener.
        aboutButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                director.sendEvent(AppActorEvents.EVENT_TRANSITION_TO_ABOUT_SCENE, event.getRelatedActor());
            }
        });

        table.pack();

        // Add table to view
        addActor(table);

    }
}
