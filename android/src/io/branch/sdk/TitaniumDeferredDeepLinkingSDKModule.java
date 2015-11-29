/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package io.branch.sdk;

import android.app.Activity;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.kroll.KrollDict;

import org.json.JSONObject;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;


@Kroll.module(name="TitaniumDeferredDeepLinkingSDK", id="io.branch.sdk")
public class TitaniumDeferredDeepLinkingSDKModule extends KrollModule
{

	// Standard Debugging variables
	private static final String LCAT = "TitaniumDeferredDeepLinkingSDKModule";
	private static final boolean DBG = TiConfig.LOGD;

	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;

	public TitaniumDeferredDeepLinkingSDKModule()
	{
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app)
	{
		Log.d(LCAT, "inside onAppCreate");
		// put module init code that needs to run when the application is created
	}

	//----------- Methods ----------//
	// Public Methods
	@Kroll.method
	public void initSession()
	{
		Log.d(LCAT, "start init");
		final Activity activity = this.getActivity();
		final Branch instance = Branch.getInstance(activity);

		instance.initSession(new SessionListener(), activity.getIntent().getData(), activity);
	}

	@Kroll.method
	public KrollDict getLatestReferringParams()
	{
		Log.d(LCAT, "start getLatestReferringParams");
		final Activity activity = this.getActivity();
		final Branch instance = Branch.getInstance(activity);

		JSONObject sessionParams = instance.getLatestReferringParams();
		if (sessionParams == null) {
    		Log.d(LCAT, "return is null");
    		return null;
    	} else {
    		Log.d(LCAT, "return is not null");
    		Log.d(LCAT, sessionParams.toString());
    	}

    	return createSessionDict(sessionParams);
	}

	@Kroll.method
	public KrollDict getFirstReferringParams()
	{
		Log.d(LCAT, "start getFirstReferringParams");
		final Activity activity = this.getActivity();
		final Branch instance = Branch.getInstance(activity);

		JSONObject installParams = instance.getFirstReferringParams();
		if (installParams == null) {
    		Log.d(LCAT, "return is null");
    		return null;
    	} else {
    		Log.d(LCAT, "return is not null");
    		Log.d(LCAT, installParams.toString());
    	}

    	return createSessionDict(installParams);
	}

	@Kroll.method
	public void setIdentity(String identity)
	{
		Log.d(LCAT, "start setIdentity");
		final Activity activity = this.getActivity();
		final Branch instance = Branch.getInstance(activity);

		instance.setIdentity(identity);
	}

	@Kroll.method
	public void userCompletedAction(String action)
	{
		Log.d(LCAT, "start userCompletedAction");
		final Activity activity = this.getActivity();
		final Branch instance = Branch.getInstance(activity);

		instance.userCompletedAction(action);
	}

	@Kroll.method
	public void userCompletedActionWithAppstate(String action, JSONObject appState)
	{
		Log.d(LCAT, "start userCompletedAction with appState");
		final Activity activity = this.getActivity();
		final Branch instance = Branch.getInstance(activity);

		instance.userCompletedAction(action, appState);
	}

	@Kroll.method
	public void logout()
	{
		Log.d(LCAT, "start logout");
		final Activity activity = this.getActivity();
		final Branch instance = Branch.getInstance(activity);

		instance.logout();
	}

	// Private Methods
	private KrollDict createSessionDict(JSONObject data) {
		KrollDict sessionDict = new KrollDict();
		if (data.has("~channel")) {
			sessionDict.put("~channel", data.optString("~channel"));
		}
    	if (data.has("~feature")) {
			sessionDict.put("~feature", data.optString("~feature"));
		}
		if (data.has("~tags")) {
			sessionDict.put("~tags", data.optString("~tags"));
		}
		if (data.has("~campaign")) {
			sessionDict.put("~campaign", data.optString("~campaign"));
		}
		if (data.has("~stage")) {
			sessionDict.put("~stage", data.optString("~stage"));
		}
		if (data.has("~creation_source")) {
			sessionDict.put("~creation_source", data.optString("~creation_source"));
		}
		if (data.has("+match_guaranteed")) {
			sessionDict.put("+match_guaranteed", data.optBoolean("+match_guaranteed"));
		}
		if (data.has("+referrer")) {
			sessionDict.put("+referrer", data.optString("+referrer"));
		}
		if (data.has("+phone_number")) {
			sessionDict.put("+phone_number", data.optString("+phone_number"));
		}
		if (data.has("+is_first_session")) {
			sessionDict.put("+is_first_session", data.optBoolean("+is_first_session"));
		}
		if (data.has("+clicked_branch_link")) {
			sessionDict.put("+clicked_branch_link", data.optBoolean("+clicked_branch_link"));
		}
		if (data.has("+click_timestamp")) {
			sessionDict.put("+click_timestamp", data.optBoolean("+click_timestamp"));
		}
    	return sessionDict;
	}

	//----------- Inner Classes: Listeners ----------//
    protected class SessionListener implements Branch.BranchReferralInitListener
    {
    	// Listener that implements BranchReferralInitListener for initSession
        @Override
        public void onInitFinished(JSONObject referringParams, BranchError error) {
        	Log.d(LCAT, "inside onInitFinished");
        	TitaniumDeferredDeepLinkingSDKModule self = TitaniumDeferredDeepLinkingSDKModule.this;
        	if (error == null) {
	            // params are the deep linked params associated with the link that the user clicked -> was re-directed to this app
	            // params will be empty if no data found
	        	if (referringParams == null) {
	        		Log.d(LCAT, "return is null");
	        		return;
	        	} else {
	        		Log.d(LCAT, "return is not null");
	        		Log.d(LCAT, referringParams.toString());
	        	}

	        	self.fireEvent("bio:initSession", createSessionDict(referringParams));
	        } else {
	            Log.d(LCAT, error.getMessage());
	            self.fireEvent("bio:initSession", error.getMessage());
	        }
        }
    }

}

