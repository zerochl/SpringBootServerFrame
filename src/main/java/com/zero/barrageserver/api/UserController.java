package com.zero.barrageserver.api;

import com.zero.barrageserver.activeapi.service.CMSActiveService;
import com.zero.barrageserver.activeapi.service.ServerActiveService;
import com.zero.barrageserver.activeapi.service.TenActiveService;
import com.zero.barrageserver.common.annotation.RequestAdviceField;
import com.zero.barrageserver.common.entity.VersionEntity;
import com.zero.barrageserver.dbserver.model.UserTable;
import com.zero.barrageserver.dbserver.service.UserService;
import com.zero.barrageserver.common.entity.VersionEntity;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zero大神 on 2017/11/27.
 */
@RestController
@Log4j2
@AllArgsConstructor
public class UserController {
    UserService userService;
    CMSActiveService cmsActiveService;
    ServerActiveService serverActiveService;
    private TenActiveService tenActiveService;
    @RequestMapping("/")
    @RequestAdviceField
//    @SerializedField(excludes = {"id","password"})
//    @SerializedField(includes = {"id", "email"}, encode = false)
    public UserTable findUserById(HttpServletRequest request){
        String id = request.getParameter("id");
        log.info("request id:" + id);
        UserTable user = userService.getUserById(id);
//        UserTable user = userService.getUserByUserId(Integer.parseInt(id));
        if(null == user){
            log.info("can not find user");
        }
//        log.info("response:" + cmsActiveService.getDownloadInfoList().size());
//        log.info("on findUserById:" + ";thread:" + Thread.currentThread().getId());
//        cmsActiveService.getDownloadInfoList(new Consumer<List<DownloadImageEntity>>() {
//            @Override
//            public void accept(List<DownloadImageEntity> downloadImageEntities) throws Exception {
//                log.info("on accept:" + ";thread:" + Thread.currentThread().getId());
//                log.info("response:" + downloadImageEntities.size());
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                log.info("on error:" + ";thread:" + Thread.currentThread().getId());
//            }
//        });

//        log.info("response home:" + serverActiveService.getHomePageInfoList().size());
//        log.info("response version:" + cmsActiveService.getVersion().getBarragecode());
        log.info("create group id:" + tenActiveService.checkAndReturnGroupId("test3-56e684b1abfcdda66091b6d0"));
        log.info("create group id:" + tenActiveService.checkAndReturnGroupId("test3-55c43920bdf67cc961e53992"));
        VersionEntity versionEntity = new VersionEntity();
        versionEntity.setBarrage("你好 world");
//        log.info("send msg result:" + tenActiveService.sendMsg("test2-56e684b1abfcdda66091b6d0",versionEntity));
        for(int i = 0;i < 1;i++){
            versionEntity = new VersionEntity();
            versionEntity.setBarrage("你好 world" + i);
            tenActiveService.sendMsgAsync("test3-56e684b1abfcdda66091b6d0",versionEntity,false,"test3-56e684b1abfcdda66091b6d0",0);
        }
//        for(int i = 0;i < 80;i++){
//            versionEntity.setBarrage("你好 world" + i);
//            tenActiveService.sendMsgAsync("test3-55c43920bdf67cc961e53992",versionEntity,false);
//        }
        log.info("response home:" + serverActiveService.getHomePageInfoList());
        return user;
    }
}
