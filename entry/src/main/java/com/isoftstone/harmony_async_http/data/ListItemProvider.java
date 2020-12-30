package com.isoftstone.harmony_async_http.data;

import com.isoftstone.harmony_async_http.ResourceTable;

import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.RecycleItemProvider;
import ohos.agp.components.Text;

import java.util.ArrayList;

public class ListItemProvider extends RecycleItemProvider {
    private ArrayList<String> data = new ArrayList<>();
    private AbilitySlice mSlice;

    ListItemProvider(AbilitySlice abilitySlice) {
        mSlice = abilitySlice;
        data.add("GET");
        data.add("POST");
        data.add("DELETE");
        data.add("PUT");
        data.add("GET TO FILE");
        data.add("GET TO Directory");
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public Component getComponent(int position, Component convertView, ComponentContainer parent) {
        Component component = LayoutScatter.getInstance(mSlice).parse(ResourceTable.Layout_list_item, null, false);
        if (!(component instanceof ComponentContainer)) {
            return null;
        }
        ComponentContainer rootLayout = (ComponentContainer) component;
        Text leftText = (Text) rootLayout.findComponentById(ResourceTable.Id_left_content);
        leftText.setText(data.get(position));
        return component;
    }
}
