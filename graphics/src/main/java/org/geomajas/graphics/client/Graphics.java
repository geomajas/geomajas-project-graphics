/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2014 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.graphics.client;

/**
 * Starting class for the Graphics Project.
 *
 * @author Jan Venstermans
 *
 */
public final class Graphics {

	private static GraphicsViewManager viewManager;

	private Graphics() {
	}

	public static GraphicsViewManager getViewManager() {
		if (viewManager == null) {
			viewManager = new GraphicsViewManagerImpl();
		}
		return viewManager;
	}

	public static void setViewManager(GraphicsViewManager viewManager) {
		Graphics.viewManager = viewManager;
	}
}
