package com.webphage.aigenesis.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.webphage.aigenesis.core.AIGenesis;

public class AIGenesisDesktop {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useGL20 = true;
		LwjglApplication app = new LwjglApplication(new AIGenesis(), config);
	}
}
