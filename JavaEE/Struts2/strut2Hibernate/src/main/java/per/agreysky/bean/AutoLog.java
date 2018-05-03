package per.agreysky.bean;

import java.util.Date;

public class AutoLog {
    private Long auditLogId;
    private String action;
    private String detail;
    private Date createdDate;
    private long entityId;
    private String entityName;
    public Long getAuditLogId() {
        return auditLogId;
    }
    public void setAuditLogId(Long auditLogId) {
        this.auditLogId = auditLogId;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public long getEntityId() {
        return entityId;
    }
    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }
    public String getEntityName() {
        return entityName;
    }
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
    public AutoLog(String action, String detail, Date createdDate,
            long entityId, String entityName) {
        super();
        this.action = action;
        this.detail = detail;
        this.createdDate = createdDate;
        this.entityId = entityId;
        this.entityName = entityName;
    }
    public AutoLog() {
        super();
    }

}
