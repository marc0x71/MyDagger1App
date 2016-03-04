package com.marc0x71.mydagger1app.provider;

import android.content.Context;

/**
 * Created by marc0x71 on 03/03/2016.
 */
public class ResourceProvider implements IResourceProvider {

    Context context;

    public ResourceProvider(Context context) {
        this.context = context;
    }

    @Override
    public String getString(int resId) {
        return context.getString(resId);
    }
}
