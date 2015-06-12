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

import org.geomajas.annotation.Api;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.updateable.anchored.AnchorMarker;

/**
 * Interface for specific view elements.
 *
 * @author Jan Venstermans
 * @since 1.0.0
 *
 */
@Api(allMethods = true)
public interface RenderElementFactory {

	/**
	 * Create a rendering container (detached !).
	 * 
	 * @return
	 */
	RenderContainer createRenderContainer();

	/**
	 * Create a root area for rendering.
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	RenderArea createRenderArea(int width, int height);

	/**
	 * Create a text with the anchor point at a specific user location. The anchor point is defined in relative
	 * coordinates w.r.t. the lower-left corner of the text box : (0,5, 0.5) = center.
	 * 
	 * @param userX
	 * @param userY
	 * @param text
	 * @param anchorX
	 * @param anchorY
	 * @return
	 */
	AnchoredText createAnchoredText(double userX, double userY, String text, double anchorX, double anchorY);

	/**
	 * Create a fixed-size rectangle with anchor point at a specific user location. The anchor point is defined in
	 * pixels w.r.t the lower-left corner..
	 * 
	 * @param userX
	 * @param userY
	 * @param userWidth
	 * @param userHeight
	 * @param anchorX
	 * @param anchorY
	 * @return
	 */
	AnchoredRectangle createAnchoredRectangle(double userX, double userY, double userWidth, double userHeight,
			int anchorX, int anchorY);

	/**
	 * Create a fixed-size rectangle with anchor point at a specific user location. The anchor point is defined in
	 * pixels w.r.t the lower-left corner.
	 * 
	 * @param userX
	 * @param userY
	 * @param width
	 * @param height
	 * @param margin
	 * @return
	 */
	AnchoredRectangle createMarginAnchoredRectangle(double userX, double userY, double width, double height, int margin);

	/**
	 * Create a path (polygon or line) with the specified user coordinates.
	 * 
	 * @param coordinates
	 * @param closed
	 * @return
	 */
	CoordinatePath createCoordinatePath(Coordinate[] coordinates, boolean closed);

	/**
	 * Create a rectangle with the specified dimensions.
	 * 
	 * @param userX
	 * @param userY
	 * @param width
	 * @param height
	 * @return
	 */
	BaseRectangle createRectangle(double userX, double userY, double width, double height);

	/**
	 * Create an ellipse with the specified dimensions.
	 * 
	 * @param ellipseCenterX
	 * @param ellipseCenterY
	 * @param userRadiusX
	 * @param userRadiusY
	 * @return
	 */
	BaseEllipse createEllipse(double ellipseCenterX, double ellipseCenterY, double userRadiusX, double userRadiusY);

	/**
	 * Create a circle with the specified dimensions.
	 * 
	 * @param circleCenterX
	 * @param circleCenterY
	 * @param radius
	 * @return
	 */
	BaseCircle createCircle(double circleCenterX, double circleCenterY, double radius);

	/**
	 * Create a base image with the specified properties.
	 * 
	 * @param userX
	 * @param userY
	 * @param width
	 * @param height
	 * @param href
	 * @param preserveRatio
	 * @return
	 */
	BaseImage createImage(double userX, double userY, int width, int height, String href, boolean preserveRatio);

	/**
	 * Create a text with the specified properties.
	 * 
	 * @param userX
	 * @param userY
	 * @param text
	 * @return
	 */
	BaseText createText(double userX, double userY, String text);

	/**
	 * Create a fixed-size circle with anchor point at a specific user location. The anchor point is defined in pixels
	 * w.r.t the lower-left corner.
	 * 
	 * @param circleCenterX
	 * @param circleCenterY
	 * @param radius
	 * @param anchorX
	 * @param anchorY
	 * @return
	 */
	AnchoredCircle createAnchoredCircle(double circleCenterX, double circleCenterY, double radius, int anchorX,
			int anchorY);

	/**
	 * Create a fixed-size image with anchor point at a specific user location. The anchor point is defined in pixels
	 * w.r.t the lower-left corner.
	 * 
	 * @param userX
	 * @param userY
	 * @param width
	 * @param height
	 * @param href
	 * @param preserveRatio
	 * @param anchorX
	 * @param anchorY
	 * @return
	 */
	AnchoredImage createAnchoredImage(double userX, double userY, int width, int height, String href,
			boolean preserveRatio, double anchorX, double anchorY);

	/**
	 * Create an anchor marker of rectangle type.
	 * 
	 * @param userX
	 * @param userY
	 * @param userWidth
	 * @param userHeight
	 * @param anchorX
	 * @param anchorY
	 * @return
	 */
	AnchorMarker createMarkerAnchoredRectangle(double userX, double userY, double userWidth, double userHeight,
			int anchorX, int anchorY);

	/**
	 * Create an anchor marker of circle type.
	 * 
	 * @param circleCenterX
	 * @param circleCenterY
	 * @param radius
	 * @param anchorX
	 * @param anchorY
	 * @return
	 */
	AnchorMarker createMarkerAnchoredCircle(double circleCenterX, double circleCenterY, double radius, int anchorX,
			int anchorY);

	/**
	 * Create an anchor marker of cross type.
	 * 
	 * @param userX
	 * @param userY
	 * @param crossHeightPixels
	 * @return
	 */
	AnchorMarker createMarkerAnchoredCross(double userX, double userY, int crossHeightPixels);

}
