package pers.agreysky.idle.action;

import java.io.IOException;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import pers.agreysky.idle.constant.TypeConstant;
import pers.agreysky.idle.enums.ResultEnum;
import pers.agreysky.idle.exception.IdleException;
import pers.agreysky.idle.service.FindKindOfItemsAndSearchItemsActionService;
import pers.agreysky.idle.util.ResultVOUtil;
import pers.agreysky.idle.vo.PartEntityVO;
import pers.agreysky.idle.vo.ResultWithDataVO;

/**
 * url:items
 * 传入：kind或者title
 * 传出：resultVO{code，msg，data{物品集合}}
 * @author zzq
 *
 */
public class FindKindOfItemsAndSearchItemsAction extends ActionSupport {
    private FindKindOfItemsAndSearchItemsActionService findKindOfItemsAndSearchItemsActionService;
    private String kind;
    private String title;
    private ResultWithDataVO resultVO;

    @Override
    public String execute() throws IOException {
        if (kind != null && kind != "") {
            if (kind.equals(TypeConstant.HELP)
                    || kind.equals(TypeConstant.DOHelp)) {
                resultVO = ResultVOUtil.success(JSONArray
                        .fromObject(findKindOfItemsAndSearchItemsActionService
                                .getHelpsByType(kind)));
            } else if (kind.equals(TypeConstant.DOFRIEND)) {
                resultVO = ResultVOUtil.success(JSONArray
                        .fromObject(findKindOfItemsAndSearchItemsActionService
                                .getFriendsByType()));
            } else if (kind.equals(TypeConstant.DOJOB)
                    || kind.equals(TypeConstant.JOB)) {
                resultVO = ResultVOUtil.success(JSONArray
                        .fromObject(findKindOfItemsAndSearchItemsActionService
                                .getJobsByType(kind)));
            } else if (kind.equals(TypeConstant.DIGIT)
                    || kind.equals(TypeConstant.FOOD)
                    || kind.equals(TypeConstant.BODYBUILDING)
                    || kind.equals(TypeConstant.BOOK)
                    || kind.equals(TypeConstant.CLOTH)
                    || kind.equals(TypeConstant.OTHER)) {
                resultVO = ResultVOUtil.success(JSONArray
                        .fromObject(findKindOfItemsAndSearchItemsActionService
                                .getItemsByType(kind)));
            } else {
                throw new IdleException(ResultEnum.TYPE_NOT_EXIST);
            }
        } else if (title != "" && title != null) {
            String itemsSql = "from Items where name like '%" + title
                    + "%' OR overview like '%" + title + "%' OR type like '%"
                    + title + "%'";
            String helpsSql = " from Helps where name like '%" + title
                    + "%' OR overview like '%" + title + "%' OR type like '%"
                    + title + "%'";
            String jobsSql = "from Jobs where name like '%" + title
                    + "%' OR overview like '%" + title + "%' OR type like '%"
                    + title + "%'";
            String friendsSql = "from Friends where overview like '%" + title
                    + "%' ";
            ArrayList<PartEntityVO> itemsList = findKindOfItemsAndSearchItemsActionService
                    .getItemsByUserCdn(itemsSql);
            ArrayList<PartEntityVO> helpsList = findKindOfItemsAndSearchItemsActionService
                    .getHelpsByUserCdn(helpsSql);
            ArrayList<PartEntityVO> jobsList = findKindOfItemsAndSearchItemsActionService
                    .getJobsByUserCdn(jobsSql);
            ArrayList<PartEntityVO> friendsList = findKindOfItemsAndSearchItemsActionService
                    .getFriendsByUserCdn(friendsSql);
            ArrayList<PartEntityVO> list = new ArrayList<PartEntityVO>();
            if (!itemsList.isEmpty()) {
                list.addAll(itemsList);
            }
            if (!helpsList.isEmpty()) {
                list.addAll(helpsList);
            }
            if (!jobsList.isEmpty()) {
                list.addAll(jobsList);
            }
            if (!friendsList.isEmpty()) {
                list.addAll(friendsList);
            }
            resultVO = ResultVOUtil.success(JSONArray.fromObject(list));
        } else {
            throw new IdleException(ResultEnum.CONDITION_NOT_NULL);
        }
        return SUCCESS;
    }
    public FindKindOfItemsAndSearchItemsActionService getFindKindOfItemsAndSearchItemsActionService() {
        return findKindOfItemsAndSearchItemsActionService;
    }
    public void setFindKindOfItemsAndSearchItemsActionService(
            FindKindOfItemsAndSearchItemsActionService findKindOfItemsAndSearchItemsActionService) {
        this.findKindOfItemsAndSearchItemsActionService = findKindOfItemsAndSearchItemsActionService;
    }
    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public ResultWithDataVO getResultVO() {
        return resultVO;
    }
    public void setResultVO(ResultWithDataVO resultVO) {
        this.resultVO = resultVO;
    }

}
