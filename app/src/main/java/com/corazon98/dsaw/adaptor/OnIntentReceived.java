package com.corazon98.dsaw.adaptor;

import android.content.Intent;
import android.net.Uri;

public interface OnIntentReceived {
    void onIntent(Intent i, int resultCode, Uri uri);
}
