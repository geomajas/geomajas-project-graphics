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

import org.geomajas.graphics.client.object.updateable.hasmarker.MarkerShape;
import org.geomajas.graphics.client.util.HasFill;
import org.geomajas.graphics.client.util.HasPosition;
import org.geomajas.graphics.client.util.HasStroke;

/**
 * 
 * @author Jan De Moerloose
 *
 */
public interface Marker extends HasPosition, HasFill, HasStroke, IsRenderable {

	void setVisible(boolean visible);

	MarkerShape getMarkerShape();
}
