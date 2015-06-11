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
package org.geomajas.graphics.client.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.object.base.BaseRectangle;
import org.geomajas.graphics.client.operation.ResizeOperation;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer;
import org.geomajas.graphics.client.util.FlipState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.web.bindery.event.shared.SimpleEventBus;

@RunWith(GwtMockitoTestRunner.class)
public class GraphicsServiceImplTest {

	@Mock
	private GraphicsObjectContainer objectContainer;

	@Test
	public void testUndoRedo() {
		SimpleEventBus eventBus = new SimpleEventBus();
		GraphicsService service = new GraphicsServiceImpl(eventBus);
		service.setObjectContainer(objectContainer);
		List<Bbox> boxes = new ArrayList<Bbox>();
		boxes.add(new Bbox(0, 0, 17, 10));
		boxes.add(new Bbox(0, 0, 11, 11));
		boxes.add(new Bbox(0, 0, 20, 10));
		boxes.add(new Bbox(0, 0, 11, 31));
		boxes.add(new Bbox(0, 0, 10, 50));

		List<FlipState> states = new ArrayList<FlipState>();
		states.add(FlipState.FLIP_X);
		states.add(FlipState.FLIP_Y);
		states.add(FlipState.FLIP_XY);
		states.add(FlipState.FLIP_XY);
		states.add(FlipState.FLIP_Y);

		List<FlipState> result = new ArrayList<FlipState>();
		result.add(FlipState.FLIP_X);
		result.add(FlipState.FLIP_XY);
		result.add(FlipState.NONE);
		result.add(FlipState.FLIP_XY);
		result.add(FlipState.FLIP_X);

		// MockResizable m = new MockResizable(new Coordinate(0, 0), new Bbox(0, 0, 10, 10));
		TestRectangle m = new TestRectangle(0, 0, 10, 10);
		for (int i = 0; i < 5; i++) {
			Bbox prev = (i == 0 ? new Bbox(0, 0, 10, 10) : boxes.get(i - 1));
			service.execute(new ResizeOperation(m, prev, boxes.get(i), states.get(i)));
			Assert.assertTrue(BboxService.equals(m.getUserBounds(), boxes.get(i), 0.0001));
			Assert.assertEquals(result.get(i), m.getFlipState());
		}

		for (int i = 4; i >= 0; i--) {
			Assert.assertTrue(BboxService.equals(m.getUserBounds(), boxes.get(i), 0.0001));
			Assert.assertEquals(result.get(i), m.getFlipState());
			service.undo();
		}
		Assert.assertTrue(BboxService.equals(m.getUserBounds(), new Bbox(0, 0, 10, 10), 0.0001));
		Assert.assertEquals(FlipState.NONE, m.getFlipState());

		for (int i = 0; i < 5; i++) {
			service.redo();
			Assert.assertTrue(BboxService.equals(m.getUserBounds(), boxes.get(i), 0.0001));
			Assert.assertEquals(result.get(i), m.getFlipState());
		}

	}

	class TestRectangle extends BaseRectangle {

		private FlipState state = FlipState.NONE;

		public TestRectangle(double userX, double userY, double width, double height) {
			super(userX, userY, width, height);
		}

		@Override
		public void flip(FlipState newState) {
			if (newState == FlipState.FLIP_X) {
				switch (state) {
					case FLIP_X:
						state = FlipState.NONE;
						break;
					case FLIP_XY:
						state = FlipState.FLIP_Y;
						break;
					case FLIP_Y:
						state = FlipState.FLIP_XY;
						break;
					case NONE:
						state = FlipState.FLIP_X;
						break;
				}
			} else if (newState == FlipState.FLIP_Y) {
				switch (state) {
					case FLIP_X:
						state = FlipState.FLIP_XY;
						break;
					case FLIP_XY:
						state = FlipState.FLIP_X;
						break;
					case FLIP_Y:
						state = FlipState.NONE;
						break;
					case NONE:
						state = FlipState.FLIP_Y;
						break;
				}
			} else if (newState == FlipState.FLIP_XY) {
				switch (state) {
					case FLIP_X:
						state = FlipState.FLIP_Y;
						break;
					case FLIP_XY:
						state = FlipState.NONE;
						break;
					case FLIP_Y:
						state = FlipState.FLIP_X;
						break;
					case NONE:
						state = FlipState.FLIP_XY;
						break;
				}
			}
		}

		public FlipState getFlipState() {
			return state;
		}

	}
}
