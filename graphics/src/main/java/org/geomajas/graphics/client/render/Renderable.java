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
package org.geomajas.graphics.client.render;

import org.geomajas.graphics.client.service.HasAllMouseAndClickHandlers;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Implemented by all graphical objects. Rendering happens by adding to a {@link RenderContainer}.
 *
 * @author Jan De Moerloose
 * 
 */
public interface Renderable extends HasAllMouseAndClickHandlers {
	
	void setCursor(String css);
	
	void removeFromParent();
	
	void bringToFront();
	
	void sendToPosition(int index);
	
	int getPosition();
	
	void capture();
	
	void releaseCapture();

	void setOpacity(double opacity);

	void setVisible(boolean visible);

	boolean isSourceOf(GwtEvent<?> event);
}
