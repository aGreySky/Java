package per.agreysky.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    //    @RequestMapping(value = "/homead", method = RequestMethod.GET)
    //    public List<Ad> homead()
    //            throws JsonParseException, JsonMappingException, IOException {
    //        ObjectMapper mapper = new ObjectMapper();
    //        String s = "[{\"title\":\"暑假5折\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191639092-2000037796.png\",\"link\":\"http://www.imooc.com/wap/index\"},{\"title\":\"特价出国\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191648124-298129318.png\",\"link\":\"http://www.imooc.com/wap/index\"},{\"title\":\"亮亮车\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191653983-1962772127.png\",\"link\":\"http://www.imooc.com/wap/index\"},{\"title\":\"学钢琴\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191700420-1584459466.png\",\"link\":\"http://www.imooc.com/wap/index\"},{\"title\":\"电影\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191706733-367929553.png\",\"link\":\"http://www.imooc.com/wap/index\"},{\"title\":\"旅游热线\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191713186-495002222.png\",\"link\":\"http://www.imooc.com/wap/index\"}]";
    //        return mapper.readValue(s, new TypeReference<List<Ad>>() {
    //        });
    //    }
    //    @RequestMapping(value = "/homelist/{city}/{page}",
    //            method = RequestMethod.GET)
    //    public BusinessList homelist()
    //            throws JsonParseException, JsonMappingException, IOException {
    //        ObjectMapper mapper = new ObjectMapper();
    //        String s = "{\"hasMore\":true,\"data\":[{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201638030-473660627.png\",\"title\":\"汉堡大大\",\"subTitle\":\"叫我汉堡大大，还你多彩口味\",\"price\":\"28\",\"distance\":\"120m\",\"mumber\":\"389\",\"id\":\"14876663372078291\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201645858-1342445625.png\",\"title\":\"北京开源饭店\",\"subTitle\":\"[望京]自助晚餐\",\"price\":\"98\",\"distance\":\"140m\",\"mumber\":\"689\",\"id\":\"7721159408497393\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201652952-1050532278.png\",\"title\":\"服装定制\",\"subTitle\":\"原价xx元，现价xx元，可修改一次\",\"price\":\"1980\",\"distance\":\"160\",\"mumber\":\"106\",\"id\":\"8827917909033072\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201700186-1351787273.png\",\"title\":\"婚纱摄影\",\"subTitle\":\"免费试穿，拍照留念\",\"price\":\"2899\",\"distance\":\"160\",\"mumber\":\"58\",\"id\":\"6460159458888495\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201708124-1116595594.png\",\"title\":\"麻辣串串烧\",\"subTitle\":\"双人免费套餐等你抢购\",\"price\":\"0\",\"distance\":\"160\",\"mumber\":\"1426\",\"id\":\"07233259807505266\"}]}";
    //        return mapper.readValue(s, new TypeReference<BusinessList>() {
    //        });
    //    }
    //
    //    @RequestMapping(value = "/submitComment", method = RequestMethod.POST)
    //    public Map<String, Object> submitComment()
    //            throws JsonParseException, JsonMappingException, IOException {
    //        Map<String, Object> result = new HashMap<String, Object>();
    //        result.put("error", 0);
    //        result.put("msg", "ok");
    //        return result;
    //    }
}
