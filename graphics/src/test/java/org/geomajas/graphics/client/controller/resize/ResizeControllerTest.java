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
package org.geomajas.graphics.client.controller.resize;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.controller.resize.ResizeController.ResizeHandler;
import org.geomajas.graphics.client.object.base.BaseRectangleObject;
import org.geomajas.graphics.client.render.RenderSpace;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.service.GraphicsServiceImpl;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer;
import org.geomajas.graphics.client.util.BboxPosition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.web.bindery.event.shared.SimpleEventBus;


@RunWith(GwtMockitoTestRunner.class)
public class ResizeControllerTest {
	
	@Mock
	private GraphicsObjectContainer objectContainer;

	@Before
	public void setUp() {
		when(objectContainer.transform(new Coordinate(105, 110), RenderSpace.SCREEN, RenderSpace.USER)).thenReturn(new Coordinate(105, 110));
		when(objectContainer.transform(new Coordinate(100, 100), RenderSpace.SCREEN, RenderSpace.USER)).thenReturn(new Coordinate(100, 100));
		when(objectContainer.transform(BboxPosition.CORNER_LL, RenderSpace.SCREEN, RenderSpace.USER)).thenReturn(BboxPosition.CORNER_UL);
		when(objectContainer.transform(BboxPosition.CORNER_LR, RenderSpace.SCREEN, RenderSpace.USER)).thenReturn(BboxPosition.CORNER_UR);
		when(objectContainer.transform(BboxPosition.CORNER_UL, RenderSpace.SCREEN, RenderSpace.USER)).thenReturn(BboxPosition.CORNER_LL);
		when(objectContainer.transform(BboxPosition.CORNER_UR, RenderSpace.SCREEN, RenderSpace.USER)).thenReturn(BboxPosition.CORNER_LR);
		when(objectContainer.transform(BboxPosition.MIDDLE_LEFT, RenderSpace.SCREEN, RenderSpace.USER)).thenReturn(BboxPosition.MIDDLE_LEFT);
		when(objectContainer.transform(BboxPosition.MIDDLE_LOW, RenderSpace.SCREEN, RenderSpace.USER)).thenReturn(BboxPosition.MIDDLE_UP);
		when(objectContainer.transform(BboxPosition.MIDDLE_RIGHT, RenderSpace.SCREEN, RenderSpace.USER)).thenReturn(BboxPosition.MIDDLE_RIGHT);
		when(objectContainer.transform(BboxPosition.MIDDLE_UP, RenderSpace.SCREEN, RenderSpace.USER)).thenReturn(BboxPosition.MIDDLE_LOW);		
	}


	@Test
	public void testResize() {
		BaseRectangleObject m = new BaseRectangleObject(30, 31, 50, 50);
		SimpleEventBus eventBus = new SimpleEventBus();
		GraphicsService service = new GraphicsServiceImpl(eventBus);
		service.setObjectContainer(objectContainer);
		ResizeController r = new ResizeController(m, service);
		List<ResizeHandler> handlers = new ArrayList<ResizeHandler>();
		for (BboxPosition type : BboxPosition.values()) {
			handlers.add(r.new ResizeHandler(type));
		}
		for (ResizeHandler h : handlers) {
			h.onDragStart(100, 100);
			h.onDragStop(105, 110, false);
			switch (h.getBboxPosition()) {
				case CORNER_LL:
					Assert.assertTrue(BboxService.equals(m.getUserBounds(), new Bbox(10, 6, 45, 60), 0.0001));
					break;
				case CORNER_LR:
					Assert.assertTrue(BboxService.equals(m.getUserBounds(), new Bbox(5, 6, 55, 60), 0.0001));
					break;
				case CORNER_UL:
					Assert.assertTrue(BboxService.equals(m.getUserBounds(), new Bbox(10, 16, 45, 40), 0.0001));
					break;
				case CORNER_UR:
					Assert.assertTrue(BboxService.equals(m.getUserBounds(), new Bbox(5, 16, 55, 40), 0.0001));
					break;
				case MIDDLE_LEFT:
					Assert.assertTrue(BboxService.equals(m.getUserBounds(), new Bbox(10, 6, 45, 50), 0.0001));
					break;
				case MIDDLE_LOW:
					Assert.assertTrue(BboxService.equals(m.getUserBounds(), new Bbox(5, 6, 50, 60), 0.0001));
					break;
				case MIDDLE_RIGHT:
					Assert.assertTrue(BboxService.equals(m.getUserBounds(), new Bbox(5, 6, 55, 50), 0.0001));
					break;
				case MIDDLE_UP:
					Assert.assertTrue(BboxService.equals(m.getUserBounds(), new Bbox(5, 16, 50, 40), 0.0001));
					break;
			}
			service.undo();
		}
	}
}
