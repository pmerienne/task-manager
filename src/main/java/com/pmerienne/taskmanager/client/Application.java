package com.pmerienne.taskmanager.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LinkElement;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.MGWTSettings.ViewPort;
import com.googlecode.mgwt.ui.client.MGWTSettings.ViewPort.DENSITY;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.pmerienne.taskmanager.client.activity.desktop.DesktopActivityMapper;
import com.pmerienne.taskmanager.client.activity.mobile.MobileActivityMapper;
import com.pmerienne.taskmanager.client.messaging.RemoteMessageDispatcher;
import com.pmerienne.taskmanager.client.place.desktop.DashBoardPlace;
import com.pmerienne.taskmanager.client.place.desktop.DesktopPlaceHistoryMapper;
import com.pmerienne.taskmanager.client.place.mobile.HomePlace;
import com.pmerienne.taskmanager.client.place.mobile.MobileAnimationMapper;
import com.pmerienne.taskmanager.client.place.mobile.MobilePlaceHistoryMapper;
import com.pmerienne.taskmanager.client.resource.ResourceBundle;
import com.pmerienne.taskmanager.client.theme.TaskManagerTheme;
import com.pmerienne.taskmanager.client.utils.Services;
import com.pmerienne.taskmanager.shared.model.User;

public class Application implements EntryPoint {

	private void start() {
		if (!MGWT.getOsDetection().isDesktop()) {
			createDesktopApplication();
		} else {
			createMobileApplication();
		}
	}

	@SuppressWarnings("deprecation")
	private void createMobileApplication() {
		// MGWT settings
		MGWTStyle.setTheme(TaskManagerTheme.get());
		ViewPort viewPort = new MGWTSettings.ViewPort();
		viewPort.setTargetDensity(DENSITY.MEDIUM);
		viewPort.setUserScaleAble(false).setMinimumScale(1.0).setMinimumScale(1.0).setMaximumScale(1.0);
		MGWTSettings settings = new MGWTSettings();
		settings.setViewPort(viewPort);
		settings.setIconUrl("logo.png");
		settings.setAddGlosToIcon(true);
		settings.setFullscreen(true);
		settings.setPreventScrolling(true);
		MGWT.applySettings(settings);

		// Init display
		final MobileClientFactory clientFactory = new MobileClientFactoryImpl();
		AnimatableDisplay display = GWT.create(AnimatableDisplay.class);
		RootPanel.get().add(display);

		// Init activity and places mappers
		MobileActivityMapper appActivityMapper = new MobileActivityMapper(clientFactory);
		MobileAnimationMapper appAnimationMapper = new MobileAnimationMapper();
		AnimatingActivityManager activityManager = new AnimatingActivityManager(appActivityMapper, appAnimationMapper,
				clientFactory.getEventBus());
		activityManager.setDisplay(display);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		MobilePlaceHistoryMapper historyMapper = GWT.create(MobilePlaceHistoryMapper.class);
		final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(clientFactory.getPlaceController(), (EventBus) clientFactory.getEventBus(),
				new HomePlace());

		// check user is logged
		Services.userService.getCurrentUser(new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				if (user != null) {
					historyHandler.handleCurrentHistory();
					// overrideMGWTStyleSheet();
				} else {
					clientFactory.getPlaceController().goTo(new HomePlace());
					// overrideMGWTStyleSheet();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				clientFactory.getPlaceController().goTo(new HomePlace());
				// overrideMGWTStyleSheet();
			}
		});
	}

	@SuppressWarnings("deprecation")
	private void createDesktopApplication() {
		// Init display
		final DesktopClientFactory clientFactory = new DesktopClientFactoryImpl();
		SimplePanel appWidget = new SimplePanel();
		RootPanel.get().add(appWidget);
		ResourceBundle.INSTANCE.style().ensureInjected();
		ResourceBundle.INSTANCE.bootstrap().ensureInjected();
		overrideBootstrapStyleSheet();

		// Init data-push
		RemoteMessageDispatcher.start(clientFactory.getEventBus());

		// Init activity and places mappers
		DesktopActivityMapper appActivityMapper = new DesktopActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(appActivityMapper, clientFactory.getEventBus());
		activityManager.setDisplay(appWidget);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		DesktopPlaceHistoryMapper historyMapper = GWT.create(DesktopPlaceHistoryMapper.class);
		final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(clientFactory.getPlaceController(), (EventBus) clientFactory.getEventBus(),
				new DashBoardPlace());

		// check user is logged
		Services.userService.getCurrentUser(new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				if (user != null) {
					historyHandler.handleCurrentHistory();
				} else {
					clientFactory.getPlaceController().goTo(new DashBoardPlace());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				clientFactory.getPlaceController().goTo(new DashBoardPlace());
			}
		});
	}

	@Override
	public void onModuleLoad() {
		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void onUncaughtException(Throwable e) {
				Window.alert("uncaught: " + e.getMessage());
				e.printStackTrace();
			}
		});

		new Timer() {
			@Override
			public void run() {
				start();

			}
		}.schedule(1);
	}

	// private void overrideMGWTStyleSheet() {
	// // this will create a link element at the end of head
	// MGWTStyle.getTheme().getMGWTClientBundle().getMainCss().ensureInjected();
	//
	// // CSS overriding
	// Element head = Document.get().getElementsByTagName("head").getItem(0);
	// LinkElement linkElement = Document.get().createLinkElement();
	// linkElement.setRel("stylesheet");
	// linkElement.setType("text/css");
	// linkElement.setHref(GWT.getModuleBaseURL() + "mgwt-override.css");
	// head.appendChild(linkElement);
	// }

	private void overrideBootstrapStyleSheet() {
		// CSS overriding
		Element head = Document.get().getElementsByTagName("head").getItem(0);
		LinkElement linkElement = Document.get().createLinkElement();
		linkElement.setRel("stylesheet");
		linkElement.setType("text/css");
		linkElement.setHref(GWT.getModuleBaseURL() + "bootstrap-override.css");
		head.appendChild(linkElement);
	}
}
