/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2015 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.graphics.client;

import org.geomajas.annotation.Api;
import org.geomajas.graphics.client.render.RenderElementFactory;
import org.geomajas.graphics.client.render.shape.VectorRenderElementFactoryImpl;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.service.GraphicsServiceImpl;

import com.google.web.bindery.event.shared.EventBus;

/**
 * Starting class for the Graphics Project.
 *
 * @author Jan Venstermans
 * @since 1.0.0
 *
 */
@Api(allMethods = true)
public final class Graphics {

	private static GraphicsViewManager viewManager;

	private static RenderElementFactory renderElementFactory;

	private static GraphicsConstants graphicsConstants;

	private Graphics() {
	}

	/**
	 * Get the view manager.
	 * 
	 * @return
	 */
	public static GraphicsViewManager getViewManager() {
		if (viewManager == null) {
			viewManager = new GraphicsViewManagerImpl();
		}
		return viewManager;
	}

	/**
	 * Set the view manager.
	 * 
	 * @param viewManager
	 */
	public static void setViewManager(GraphicsViewManager viewManager) {
		Graphics.viewManager = viewManager;
	}

	/**
	 * Get the render element factory.
	 * 
	 * @return
	 */
	public static RenderElementFactory getRenderElementFactory() {
		if (renderElementFactory == null) {
			renderElementFactory = new VectorRenderElementFactoryImpl();
		}
		return renderElementFactory;
	}

	/**
	 * Set the render element factory.
	 * 
	 * @param renderElementFactory
	 */
	public static void setRenderElementFactory(RenderElementFactory renderElementFactory) {
		Graphics.renderElementFactory = renderElementFactory;
	}

	/**
	 * Get the graphics constants.
	 * 
	 * @return
	 */
	public static GraphicsConstants getGraphicsConstants() {
		if (graphicsConstants == null) {
			graphicsConstants = new GraphicsConstants();
		}
		return graphicsConstants;
	}

	/**
	 * Create a graphics service. Make sure to set the object container and start the service !
	 * 
	 * @param eventBus
	 * @return
	 */
	public static GraphicsService createGraphicsService(EventBus eventBus) {
		return new GraphicsServiceImpl(eventBus);
	}

}
