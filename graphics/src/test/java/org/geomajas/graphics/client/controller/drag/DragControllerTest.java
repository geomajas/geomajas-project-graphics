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
package org.geomajas.graphics.client.controller.drag;

import static org.mockito.Mockito.when;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.controller.resize.ResizeController;
import org.geomajas.graphics.client.object.base.BaseRectangleObject;
import org.geomajas.graphics.client.render.RenderSpace;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.service.GraphicsServiceImpl;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.web.bindery.event.shared.SimpleEventBus;

@RunWith(GwtMockitoTestRunner.class)
public class DragControllerTest {

	@Mock
	private GraphicsObjectContainer objectContainer;

	private SimpleEventBus eventBus = new SimpleEventBus();

	private GraphicsService service;

	private static final double DOUBLE_DELTA = 0.0001;

	@Before
	public void setUp() {
		when(objectContainer.transform(new Coordinate(105, 110), RenderSpace.SCREEN, RenderSpace.USER)).thenReturn(
				new Coordinate(105, 110));
		when(objectContainer.transform(new Coordinate(100, 100), RenderSpace.SCREEN, RenderSpace.USER)).thenReturn(
				new Coordinate(100, 100));
		service = new GraphicsServiceImpl(eventBus);
		service.setObjectContainer(objectContainer);
	}

	@Test
	public void testDrag() {
		BaseRectangleObject m = new BaseRectangleObject(5, 6, 50, 50);
		ResizeController r = new ResizeController(m, service);
		GraphicsObjectDragHandler h = new GraphicsObjectDragHandler(m, service, r);

		h.onDragStart(new Coordinate(100, 100));
		h.onDragStop(new Coordinate(105, 110));

		Assert.assertTrue(m.getUserPosition().equalsDelta(new Coordinate(10, 16), DOUBLE_DELTA));
		service.undo();
		Assert.assertTrue(m.getUserPosition().equalsDelta(new Coordinate(5, 6), DOUBLE_DELTA));
	}

}
