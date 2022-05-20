package com.example.orderapp.Fragment.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class adminfragment extends FragmentStatePagerAdapter {

    public adminfragment(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new fragment_user();
            case 1:
                return new fragment_coffee();
            default:
                return new fragment_user();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "User";
            case 1:
                return "Coffee";
            default:
                return "User";
        }
    }
}
