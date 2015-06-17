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
package org.geomajas.graphics.client.object.updateable;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.GraphicsBaseTest;
import org.geomajas.graphics.client.object.role.HasMarker;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.updateable.hasmarker.MarkerShape;
import org.geomajas.graphics.client.util.HasFill;
import org.geomajas.graphics.client.util.HasStroke;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gwtmockito.GwtMockitoTestRunner;

@RunWith(GwtMockitoTestRunner.class)
public class AnchoredIconTest extends GraphicsBaseTest {

	private AnchoredIcon anchoredIcon;

	@Test
	public void testConstructorCreatesRoles() throws Exception {
		String href = "hrefTest";
		anchoredIcon = createAnchoredIcon(new Coordinate(15, 20), 5, 5, href, new Coordinate(-1, -2), MarkerShape.CIRCLE);

		// draggable
		Assert.assertTrue(anchoredIcon.hasRole(Draggable.TYPE));
		Draggable draggable = anchoredIcon.getRole(Draggable.TYPE);
		Assert.assertNotNull(draggable);
		// anchored
		Assert.assertTrue(anchoredIcon.hasRole(HasMarker.TYPE));
		HasMarker anchored = anchoredIcon.getRole(HasMarker.TYPE);
		Assert.assertNotNull(anchored);
	}

	@Test
	public void testCloneObjectDraggable() throws Exception {
		String href = "hrefTest";
		anchoredIcon = createAnchoredIcon(new Coordinate(15, 20), 5, 5, href, new Coordinate(-1, -2), MarkerShape.CIRCLE);
		Draggable expected = anchoredIcon.getRole(Draggable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = anchoredIcon.cloneObject();

		AnchoredIcon anchoredIconClone = assertIsCorrectObject(clone);
		Assert.assertTrue(anchoredIconClone.hasRole(Draggable.TYPE));
		assertRoleEqualityDraggable(anchoredIcon.getRole(Draggable.TYPE), anchoredIconClone.getRole(Draggable.TYPE));
	}

	@Test
	public void testCloneObjectAnchored() throws Exception {
		String href = "hrefTest";
		anchoredIcon = createAnchoredIcon(new Coordinate(15, 20), 5, 5, href, new Coordinate(-1, -2), MarkerShape.CIRCLE);
		HasMarker expected = anchoredIcon.getRole(HasMarker.TYPE);
		Strokable anchorLineStrokable = expected.getMarkerLineStrokable();
		anchorLineStrokable.setStrokeColor("strokeColor");
		anchorLineStrokable.setStrokeOpacity(0.3);
		anchorLineStrokable.setStrokeWidth(8);
		HasStroke anchorMarkerStrokable = expected.getMarker();
		anchorMarkerStrokable.setStrokeColor("strokeColor2");
		anchorMarkerStrokable.setStrokeOpacity(0.7);
		anchorMarkerStrokable.setStrokeWidth(2);
		HasFill anchorMarkerFillable = expected.getMarker();
		anchorMarkerFillable.setFillColor("fillColor");
		anchorMarkerFillable.setFillOpacity(0.4);
		expected.getMarker().setUserPosition(new Coordinate(20, -18));

		Object clone = anchoredIcon.cloneObject();

		AnchoredIcon anchoredIconClone = assertIsCorrectObject(clone);
		Assert.assertTrue(anchoredIconClone.hasRole(HasMarker.TYPE));
		HasMarker anchoredClone = anchoredIconClone.getRole(HasMarker.TYPE);
		assertRoleEqualityAnchored(expected, anchoredClone);
	}


	private AnchoredIcon createAnchoredIcon(Coordinate iconCoordinate, int iconWidth, int iconHeight,
											String iconHref, Coordinate anchorCoordinate, MarkerShape markerShape) {
		return new AnchoredIcon(iconCoordinate, iconWidth, iconHeight, iconHref, anchorCoordinate, markerShape);
	}

	private AnchoredIcon assertIsCorrectObject(Object clone) {
		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof AnchoredIcon);
		return (AnchoredIcon) clone;
	}

}
