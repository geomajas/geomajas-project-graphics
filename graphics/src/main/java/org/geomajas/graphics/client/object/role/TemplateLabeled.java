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
import org.geomajas.graphics.client.object.RoleType;

/**
 * Implemented by templateLabeled graphics objects.
 * 
 * @author Jan Venstermans
 * @since 1.0.0
 * 
 */
@Api(allMethods = true)
public interface TemplateLabeled extends Textable {

	/**
	 * The role type.
	 */
	RoleType<TemplateLabeled> TYPE = new RoleType<TemplateLabeled>("TemplateLabeled");

	/**
	 * Get the label template text.
	 * 
	 * @return
	 */
	String getLabelTemplateText();

	/**
	 * Set the label template text.
	 * 
	 * @param templateText
	 */
	void setLabelTemplateText(String templateText);

	/**
	 * Get the actually rendered text.
	 * 
	 * @return
	 */
	String getLabelRenderedText();

	/**
	 * Set the actually rendered text.
	 * 
	 * @param renderedText
	 */
	void setLabelRenderedText(String renderedText);

	/**
	 * Render the template text. Performs template processing ?
	 */
	void renderTemplateText();
}
