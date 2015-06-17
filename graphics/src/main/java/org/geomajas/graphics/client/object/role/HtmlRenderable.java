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

import org.geomajas.annotation.Api;
import org.geomajas.graphics.client.object.RoleInterface;
import org.geomajas.graphics.client.object.RoleType;

import com.google.gwt.user.client.ui.Widget;

/***
 * Role for {@link Widget}.
 * 
 * @author Jan De Moerloose
 * @since 1.0.0
 * 
 */
@Api(allMethods = true)
public interface HtmlRenderable extends RoleInterface {

	/**
	 * The role type.
	 */
	RoleType<HtmlRenderable> TYPE = new RoleType<HtmlRenderable>("HtmlRenderable");

	/**
	 * Get the GWT widget.
	 * 
	 * @return
	 */
	Widget asWidget();
}
