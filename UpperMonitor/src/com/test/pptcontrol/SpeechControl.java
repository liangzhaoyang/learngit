package com.test.pptcontrol;
/***
 * 定义一个信息设置界面
 */

import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class SpeechControl extends PreferenceActivity implements
OnPreferenceChangeListener{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//PreferenceManager初始化
		PreferenceManager preferenceManager = getPreferenceManager();
		preferenceManager.setSharedPreferencesName(getPackageName());
		preferenceManager.setSharedPreferencesMode(MODE_PRIVATE);
		addPreferencesFromResource(R.xml.preference_iat);
		
		//ip地址设置
		EditTextPreference ipPreference = (EditTextPreference)
				findPreference(getString(R.string
				.set_ip));
			ipPreference.setOnPreferenceChangeListener(this);
			ipPreference.setSummary(ipPreference.getText());
		//
		//端口地址设置
		EditTextPreference portPreference = (EditTextPreference)
				findPreference(getString(R.string
				.set_port));
			portPreference.setOnPreferenceChangeListener(this);
			portPreference.setSummary(portPreference.getText());
		//采样率参数列表.
		ListPreference rateListPreference = (ListPreference)
			findPreference(getString(R.string
			.preference_key_iat_rate));
		rateListPreference.setOnPreferenceChangeListener(this);
		rateListPreference.setSummary(rateListPreference.getEntry());
		//
	}

	/**
	 * OnPreferenceChangeListener回调接口，当设置参数列表
	 * 选中值修改之后被调用.
	 * @param preference
	 * @param newValue
	 */
	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		if (preference instanceof ListPreference) {
			//获取当前的弹出列表UI对象
			ListPreference listPreference = (ListPreference) preference;
			//
			CharSequence[] entries = listPreference.getEntries();
			//获取当前点击的是列表的第几个元素.
			int index = listPreference.findIndexOfValue((String) newValue);

			listPreference.setSummary(entries[index]);
		}
		//显示ip改变的值
		if(preference.getKey().equals("setIp"))
		{
			EditTextPreference ipPreference = (EditTextPreference)preference;
			ipPreference.setSummary(ipPreference.getText());
		}
		//显示port改变的值
		if(preference.getKey().equals("setPort"))
		{
			EditTextPreference portPreference = (EditTextPreference)preference;
			portPreference.setSummary(portPreference.getText());
		}
		refresh();
		/*
		if(preference instanceof EditTextPreference)
		{
			EditTextPreference ipPreference = (EditTextPreference)preference;
			ipPreference.setSummary(ipPreference.getText());
		}
		*/
		return true;
	}
	//
	public void finish() {
		super.finish();
	}
	//页面刷新
	private void refresh() {
        finish();
        Intent intent = new Intent(SpeechControl.this, SpeechControl.class);
        startActivity(intent);
    }
}
