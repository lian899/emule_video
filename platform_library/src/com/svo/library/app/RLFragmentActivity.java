/**
 * Copyright (c) 2013-2014, 杜卫宾 快速开发平台(http://1.playandroid.duapp.com/)
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
package com.svo.library.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.svo.library.R;
import com.svo.library.util.RLSysUtil;
/**
 * 快速开发平台 (http://1.playandroid.duapp.com/).
 * @author Administrator
 * <br>RLFragmentActivity主要完成对统计、反馈、crash的初始化及activity跳转动画的实现<br>
 * 建议项目中的FragmentActivity继承于RLFragmentActivity,自动包含activity动画的跳转
 */
public class RLFragmentActivity extends FragmentActivity{
	private boolean isEnableCrashHandler=true;
	private boolean isEnableAnalytics=false;
	private boolean isEnableFeedback=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String enableCrashHandler=RLSysUtil.getApplicationMetaData(this, "ENABLE_CRASH_HANDLER");
		if(enableCrashHandler!=null&&enableCrashHandler.equals("false")){
			isEnableCrashHandler=false;
		}
		String enableAnalytics=RLSysUtil.getApplicationMetaData(this, "ENABLE_ANALYTICS");
		if(enableAnalytics!=null&&enableAnalytics.equals("true")){
			isEnableAnalytics=true;
		}
		String enableFeedback=RLSysUtil.getApplicationMetaData(this, "ENABLE_FEEDBACK");
		if(enableFeedback!=null&&enableFeedback.equals("true")){
			isEnableFeedback=true;
		}
		
		if(isEnableCrashHandler){
			RLCrashHandler.getInstance().init(this);
		}
		
		((RLApplication)getApplication()).setDisplayInfo(RLSysUtil.getDisplayInfo(this));
	}
	
	@Override
	public void onNewIntent(Intent intent){
		super.onNewIntent(intent);
	}
	
	@Override
	protected void onStart(){
		super.onStart();
	}
	
	@Override
	protected void onRestart(){
		super.onRestart();
	}
	
	
	@Override
	protected void onStop(){
		super.onStop();
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
	}
	
	@Override
	public void startActivity(Intent intent){
		super.startActivity(intent);
		overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
	}
	
	@Override
	public void startActivityForResult(Intent intent,int requestCode){
		super.startActivityForResult(intent, requestCode);
		overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
	}
	
	@Override
	public void startActivityFromChild(Activity child,Intent intent,int requestCode){
		super.startActivityFromChild(child, intent, requestCode);
		overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
	}
	
	@Override
	public void onBackPressed(){
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
	}
	
	@Override
	public void finish(){
		super.finish();
		overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
	}
	
	@Override
	public void finishActivity(int requestCode){
		super.finishActivity(requestCode);
		overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
	}
	
	@Override
	public void finishFromChild(Activity child){
		super.finishFromChild(child);
		overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
	}
	
	@Override
	public void finishActivityFromChild(Activity child, int requestCode){
		super.finishActivityFromChild(child, requestCode);
		overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig){
		super.onConfigurationChanged(newConfig);
		((RLApplication)getApplication()).setDisplayInfo(RLSysUtil.getDisplayInfo(this));
	}
	
	@Override
	public void onLowMemory(){
		super.onLowMemory();
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
	}
}