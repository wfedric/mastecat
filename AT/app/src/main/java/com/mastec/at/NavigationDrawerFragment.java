package com.mastec.at;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class NavigationDrawerFragment extends android.support.v4.app.Fragment {

    public static final String PREF_FILE_NAME="atpref";
    public static final String KEY_USER_LEARNED_DRAWER="user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private static AnimationDrawable logoAnimation;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;

    ImageView logoM;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        mUserLearnedDrawer=Boolean.valueOf(readFromPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,"false"));
        if(savedInstanceState!=null){
            mFromSavedInstanceState=true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View logoView = inflater.inflate(R.layout.fragment_navigation_drawer,container, false);
       // ImageView logoImage = (ImageView) logoView.findViewById(R.id.logoanimation);
       // logoImage.setBackgroundResource(R.drawable.logoanimation);
       // logoAnimation = (AnimationDrawable) logoImage.getBackground();

       // Inflate the layout for this fragment



       return logoView;

    }

    public void setUp(int fragmentID, DrawerLayout drawerLayout, Toolbar toolbar) {
        containerView=getActivity().findViewById(fragmentID);

        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer=true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
                ImageView logoMout = (ImageView) containerView.findViewById(R.id.logomanimout);
                ImageView logoMin = (ImageView) containerView.findViewById(R.id.logomanimin);
                ImageView logoZ = (ImageView) containerView.findViewById(R.id.logozanim);
                Animation animationmout = AnimationUtils.loadAnimation(getActivity(),R.anim.logo_m_anim_out);
                Animation animationmin = AnimationUtils.loadAnimation(getActivity(), R.anim.logo_m_anim_in);
                Animation animationz = AnimationUtils.loadAnimation(getActivity(), R.anim.logo_z_anim);


                logoMout.startAnimation(animationmout);
                logoMin.startAnimation(animationmin);
                logoZ.startAnimation(animationz);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        if(!mUserLearnedDrawer&& !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable(){

                    public void run(){
               mDrawerToggle.syncState();
            }

        });

    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();

    }
    public static String readFromPreferences(Context context, String preferenceName, String defaultValue){

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue);

    }


}
