package per.agreysky.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import per.agreysky.dto.AdDto;
import per.agreysky.service.AdService;

@Controller
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService adService;

    @RequestMapping
    public String init() {
        return "/content/adList";
    }

    @RequestMapping("/addInit")
    public String addInit() {
        return "/content/adAdd";
    }
    @RequestMapping("/adAdd")
    public String addinit(AdDto adDto) {
        adService.add(adDto);
        return "/content/adAdd";
    }
}
