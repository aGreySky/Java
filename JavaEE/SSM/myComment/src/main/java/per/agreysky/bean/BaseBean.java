package per.agreysky.bean;

import lombok.Data;

@Data
public class BaseBean {
    private Page page;

    public BaseBean() {
        this.page = new Page();
    }
}
