package per.agreysky.Interceptor;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import per.agreysky.bean.Teachers;

public class EntityBeanInterceptor extends EmptyInterceptor {
    private ThreadLocal entityBeans = new ThreadLocal();

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) {
        AutoLogUtil.LogIt("Delete", (Teachers) entity);
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id,
            Object[] currentState, Object[] previousState,
            String[] propertyNames, Type[] types) {
        AutoLogUtil.LogIt("Update", (Teachers) entity);
        return false;
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) {
        AutoLogUtil.LogIt("Save", (Teachers) entity);
        return false;
    }

}
