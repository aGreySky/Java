package interceptor;

import java.util.Locale;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        ActionContext ac = ai.getInvocationContext();
        Map session = ac.getSession();
        Locale currentLocale = (Locale) session.get("WW_TRANS_I18N_LOCALE");
        if (currentLocale == null) {
            currentLocale = new Locale("zh", "CN");
            session.put("WW_TRANS_I18N_LOCALE", currentLocale);
        }
        return ai.invoke();
    }

}
