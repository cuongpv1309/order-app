package com.example.orderapp.Fragment.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class useradapter extends FragmentStatePagerAdapter {

    public useradapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 : return new MainUser();
            case 1 : return new OrderUser();
            case 2 : return new EditUser();
            default: return new MainUser();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0 : return "Home";
            case 1 : return "Order";
            case 2 : return "Edit";
            default: return "Home";
        }
    }
}
