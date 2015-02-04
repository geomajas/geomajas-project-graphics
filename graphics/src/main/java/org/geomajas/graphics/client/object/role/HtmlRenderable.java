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
package org.geomajas.graphics.client.object.role;

import com.google.gwt.user.client.ui.Widget;
import org.geomajas.graphics.client.object.RoleInterface;
import org.geomajas.graphics.client.object.RoleType;

/***
 * Role for {@link Widget}.
 * 
 * @author Jan De Moerloose
 * 
 */
public interface HtmlRenderable extends RoleInterface {

	RoleType<HtmlRenderable> TYPE = new RoleType<HtmlRenderable>("HtmlRenderable");

	Widget asWidget();
}
