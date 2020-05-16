package com.gamewae.iqra.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gamewae.iqra.fragment.HintDuaFragment;
import com.gamewae.iqra.fragment.HintSatuFragment;

public class HintFragmentAdapter extends FragmentStatePagerAdapter {
	public HintFragmentAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			return new HintSatuFragment();
		case 1:
			return new HintDuaFragment();
		default:
			return null;
		}
	}
}