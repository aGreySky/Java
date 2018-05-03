package per.agreysky.service;

import per.agreysky.dto.AdDto;

public interface AdService {
    /**
     * 新增广告
     * @param adDto
     * @return 是否新增成功：true-成功；false-失败
     *
     */
    boolean add(AdDto adDto);
}
