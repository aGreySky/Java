package pers.agreysky.idle.util;

import pers.agreysky.idle.vo.ResultVO;
import pers.agreysky.idle.vo.ResultWithDataVO;

public class ResultVOUtil {
    public static ResultWithDataVO success(Object object) {
        ResultWithDataVO resultVO = new ResultWithDataVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
