package com.marticles.airnet.dataservice.controller;

import com.github.pagehelper.PageInfo;
import com.marticles.airnet.dataservice.model.PollutionResponse;
import com.marticles.airnet.dataservice.model.SinglePollutionResponse;
import com.marticles.airnet.dataservice.service.PollutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Marticles
 * @description PollutionController
 * @date 2019/1/25
 */
@RestController
public class PollutionController {

    @Autowired
    PollutionService pollutionService;

    /**
     * 获取全部污染物
     *
     * @param site
     * @param start
     * @param end
     * @return com.marticles.airnet.dataserver.model.PollutionResponse
     * @author Marticles
     * @date 2019/1/25
     */
    @GetMapping("/{site}/pollution")
    public PollutionResponse getAllPollution(@PathVariable String site,
                                             @RequestParam Date start,
                                             @RequestParam Date end) {
        return pollutionService.getAllPollution(site, start, end);
    }

    /**
     * API接口
     *
     * @param site
     * @param start
     * @param end
     * @return java.util.List<java.util.HashMap < java.lang.String , java.lang.Object>>
     * @author Marticles
     * @date 2019/3/23
     */
    @GetMapping("/standard/{site}/pollution")
    public List<HashMap<String, Object>> getAllPollutionForApi(@PathVariable String site,
                                                               @RequestParam Date start,
                                                               @RequestParam Date end) {
        return pollutionService.getAllPollutionForApi(site, start, end);
    }

    /**
     * 分页接口
     *
     * @param site
     * @param start
     * @param end
     * @param pageNum
     * @param pageSize
     * @return com.github.pagehelper.PageInfo
     * @author Marticles
     * @date 2019/3/23
     */
    @GetMapping("/page/{site}/pollution")
    public PageInfo getAllPollutionForPage(@PathVariable String site,
                                           @RequestParam Date start,
                                           @RequestParam Date end,
                                           @RequestParam Integer pageNum,
                                           @RequestParam Integer pageSize) {
        return pollutionService.getAllPollutionForPage(site, start, end, pageNum, pageSize);
    }


    /**
     * 获取某污染物
     *
     * @param site
     * @param pollution
     * @param start
     * @param end
     * @return com.marticles.airnet.dataserver.model.SinglePollutionResponse
     * @author Marticles
     * @date 2019/1/25
     */
    @GetMapping("/{site}/{pollution}")
    public SinglePollutionResponse getPollution(@PathVariable String site,
                                                @PathVariable String pollution,
                                                @RequestParam Date start,
                                                @RequestParam Date end) {
        return pollutionService.getPollution(site, pollution, start, end);
    }


    /**
     * API接口
     *
     * @param site
     * @param pollution
     * @param start
     * @param end
     * @return java.util.List<java.util.HashMap < java.lang.String , java.lang.Object>>
     * @author Marticles
     * @date 2019/3/23
     */
    @GetMapping("/standard/{site}/{pollution}")
    public List<HashMap<String, Object>> getPollutionForApi(@PathVariable String site,
                                                            @PathVariable String pollution,
                                                            @RequestParam Date start,
                                                            @RequestParam Date end) {
        return pollutionService.getPollutionForApi(pollution, site, start, end);
    }

    /**
     * 获取监测站数据最后更新时间
     *
     * @param site
     * @return java.util.HashMap<java.lang.String   ,   java.lang.Object>
     * @author Marticles
     * @date 2019/3/10
     */
    @GetMapping("/{site}/updated-time")
    public HashMap<String, Object> getSiteUpdatedTime(@PathVariable String site) {
        return pollutionService.getSiteUpdatedTime(site);
    }

    /**
     * 日期转换
     *
     * @author Marticles
     * @date 2019/1/25
     */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
