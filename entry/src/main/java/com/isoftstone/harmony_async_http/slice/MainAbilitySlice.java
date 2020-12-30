package com.isoftstone.harmony_async_http.slice;

import com.isoftstone.harmony_async_http.data.ListHolder;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;

public class MainAbilitySlice extends AbilitySlice {
    private ListHolder listHolder;

    @Override
    public void onStart(Intent intent) {
        listHolder = new ListHolder(this);
        setUIContent(listHolder.createComponent());
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
