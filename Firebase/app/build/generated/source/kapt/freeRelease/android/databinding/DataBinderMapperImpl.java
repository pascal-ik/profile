
package android.databinding;
import pamtech.com.firebase.BR;
class DataBinderMapperImpl extends android.databinding.DataBinderMapper {
    public DataBinderMapperImpl() {
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case pamtech.com.firebase.R.layout.activity_main:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_main_0".equals(tag)) {
                            return new pamtech.com.firebase.databinding.ActivityMainBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
                }
                case pamtech.com.firebase.R.layout.item_messages:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/item_messages_0".equals(tag)) {
                            return new pamtech.com.firebase.databinding.ItemMessagesBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for item_messages is invalid. Received: " + tag);
                }
        }
        return null;
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    @Override
    public int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 423753077: {
                if(tag.equals("layout/activity_main_0")) {
                    return pamtech.com.firebase.R.layout.activity_main;
                }
                break;
            }
            case 1813401028: {
                if(tag.equals("layout/item_messages_0")) {
                    return pamtech.com.firebase.R.layout.item_messages;
                }
                break;
            }
        }
        return 0;
    }
    @Override
    public String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"};
    }
}