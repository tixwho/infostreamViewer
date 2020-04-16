package gui.helper;

import java.util.prefs.Preferences;

public class PrefHelper {
    public Preferences pref;
    public PrefHelper(Class<?> cls) {
        pref = Preferences.userRoot().node(cls.getName());
    }

}
