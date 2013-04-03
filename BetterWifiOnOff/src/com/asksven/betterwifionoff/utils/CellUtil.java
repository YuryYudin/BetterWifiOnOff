/*
 * Copyright (C) 2011 asksven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.asksven.betterwifionoff.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.asksven.betterwifionoff.data.AppWhitelistDBHelper;
import com.asksven.betterwifionoff.data.CellLogEntry;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

/**
 * Helper to list running apps
 * 
 * @author sven
 * 
 */
public class CellUtil
{
	
	static String TAG = "CellUtil";
	
	public static CellLogEntry getCurrentCell(Context ctx)  
	{
		CellLogEntry cell = null;
		final TelephonyManager telephony = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
		if ((telephony.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM)
			|| (telephony.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA))
		{
		    final GsmCellLocation location = (GsmCellLocation) telephony.getCellLocation();
		    if (location != null)
		    {
		        Log.i(TAG, "Detected cell LAC: " + location.getLac() + " CID: " + location.getCid());
		        cell = new CellLogEntry(location.getCid(), location.getLac());
		        
		    }
		}
		
		return cell;
	}
}
