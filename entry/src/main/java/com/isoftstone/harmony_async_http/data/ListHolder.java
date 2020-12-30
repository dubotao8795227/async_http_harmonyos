package com.isoftstone.harmony_async_http.data;


import com.example.httplibrary.utils.AsyncHttpClient;
import com.example.httplibrary.utils.JsonHttpResponseHandler;
import com.example.httplibrary.utils.RequestParams;
import com.isoftstone.harmony_async_http.ResourceTable;
import cz.msebera.android.httpclient.Header;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class ListHolder implements ListContainer.ItemClickedListener {
    private static final String TAG = "ListHolder";
    private static final HiLogLabel label=new HiLogLabel(HiLog.DEBUG,0x00100,"async-http");
    private AbilitySlice mSlice;

    private ComponentContainer mRootLayout;

    private ListContainer listContainer;

    private ListItemProvider listItemProvider;
    /**
     * 显示结果的Text
     */
    private Text mResult;

    public ListHolder(AbilitySlice abilitySlice) {
        mSlice = abilitySlice;
        listItemProvider = new ListItemProvider(abilitySlice);
    }

    public ComponentContainer createComponent() {
        Component mainComponent = LayoutScatter.getInstance(mSlice).parse(com.isoftstone.harmony_async_http.ResourceTable.Layout_ability_main, null, false);
        if (!(mainComponent instanceof ComponentContainer)) {
            return null;
        }
        mRootLayout = (ComponentContainer) mainComponent;
        listContainer = (ListContainer) mRootLayout.findComponentById(ResourceTable.Id_list);
        mResult = (Text) mRootLayout.findComponentById(ResourceTable.Id_tvResult);
        listContainer.setItemProvider(listItemProvider);
        listContainer.setItemClickedListener(this);
        return mRootLayout;
    }
    String url="http://apis.juhe.cn/simpleWeather/query";
    String key="32becf485f7f174d4385957b62f28f61";
    @Override
    public void onItemClicked(ListContainer listContainer, Component component, int i, long l) {
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("city","西安");
        params.put("key",key);
        client.get(url,params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
                HiLog.error(label,"zel-onSuccess:"+responseString,responseString);
                // 通知主线程更新UI
                mSlice.getUITaskDispatcher().asyncDispatch(new Runnable() {
                    @Override
                    public void run() {
                        mResult.setText(responseString);
                    }
                });
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                HiLog.error(label,"zel-onFailure:"+responseString,responseString);
            }
        });

    }
}
