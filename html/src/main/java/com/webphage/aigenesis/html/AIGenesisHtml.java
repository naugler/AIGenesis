package com.webphage.aigenesis.html;

import com.webphage.aigenesis.core.AIGenesis;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class AIGenesisHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new AIGenesis();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
