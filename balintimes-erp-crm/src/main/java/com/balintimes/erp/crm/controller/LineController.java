package com.balintimes.erp.crm.controller;

import com.balintimes.erp.crm.model.Area;
import com.balintimes.erp.crm.model.Line;
import com.balintimes.erp.crm.service.AreaService;
import com.balintimes.erp.util.common.FileDetail;
import com.balintimes.erp.util.common.IoUtil;
import com.balintimes.erp.util.json.AjaxResponse;
import com.balintimes.erp.util.json.ResponseMessage;
import com.balintimes.erp.util.mvc.annon.HasPermissions;
import com.balintimes.erp.util.mvc.annon.MvcModel;
import com.balintimes.erp.util.mvc.model.WebUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by AlexXie on 2015/8/26.
 */
@Controller
@RequestMapping("line")
public class LineController extends BaseController {

    @Value("#{uploadpathProperties['upload.temppath']}")
    private String tempUrl;
    @Value("#{uploadpathProperties['upload.base']}")
    private String baseUrl;

    static List<Line> list = null;
    @Resource
    private AreaService areaInfoService;

    public LineController() {

        list = new ArrayList<Line>(54);
        Line line;
        for (int i = 0; i < 54; i++) {
            line = new Line();
            line.setUid(UUID.randomUUID().toString());
            line.setName(i + "AB水");
            line.setCreatetime(new Date());

            list.add(line);
        }
    }

    @RequestMapping(value = "file")
    @ResponseBody
    public String getFile() {
//        com.balintimes.erp.util.log.LogUtil.recordServiceLog();
        List<Area> list = this.areaInfoService.getAreaInfoList(null, null);
        return "success";
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse listAll( String uid) {

        Line l = null;
        for (Line line : list) {
            if (uid.equalsIgnoreCase(line.getUid())) {
                l = line;
                break;
            }
        }

        return ResponseMessage.successful(l);
    }

//    @RequestMapping(value = "query/{pagesize}/{page}/{query}", method = RequestMethod.GET)
//    @ResponseBody
//    public AjaxResponse getLine(@PathVariable String query, @PathVariable int pagesize, @PathVariable int page) {
//
//        int start = (page - 1) * pagesize;
//        int take = 0;
//
//        List<Line> sublist = new ArrayList<Line>(pagesize);
//        for (int i = start; i < this.list.size() && take < pagesize; i++, take++) {
//            sublist.add(list.get(i));
//        }
//
//        return ResponseMessage.successful(sublist, 54);
//    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse getLine(String query, Integer pagesize, Integer page) {

        int start = (page - 1) * pagesize;
        int take = 0;
        System.out.println(query);

        List<Line> sublist = new ArrayList<Line>(pagesize);
        for (int i = start; i < this.list.size() && take < pagesize; i++, take++) {
            sublist.add(list.get(i));
        }

        return ResponseMessage.successful(sublist, 54);
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse SaveLine(@MvcModel WebUser currentUser, Line line) {

        System.out.println(currentUser.toString());
        System.out.println(line.toString());

        if (line.getUid().equals("0")) {

        } else {

        }
        return ResponseMessage.successful("保存成功");
    }


    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse UploadLine(@MvcModel WebUser currentUser, Line line, HttpServletRequest request, HttpServletResponse response) {

        System.out.println(currentUser.toString());
        System.out.println(line.toString());

        String attPath = baseUrl + tempUrl;
        String fileName = "这是什么？";
        List<FileDetail> fds = null;
        try {
            fds = IoUtil.upload(request, response, tempUrl, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (FileDetail item : fds) {
            item.setBaseUrl(attPath);
        }

        if (line.getUid().equals("0")) {

        } else {

        }
        return ResponseMessage.successful("保存成功");
    }

    @RequestMapping(value = "delete/{uid}", method = RequestMethod.DELETE)
    @HasPermissions("crm.line.delete")
    @ResponseBody
    public AjaxResponse DeleteLine(@MvcModel WebUser currentUser, @PathVariable String uid) {

        System.out.println(currentUser.toString());
        System.out.println(uid);

        return ResponseMessage.successful("保存成功");
    }
}
