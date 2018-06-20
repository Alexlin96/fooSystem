package com.pure.controller;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pure.common.PageInfo;
import com.pure.common.ReturnData;
import com.pure.common.SysCodeMsg;
import com.pure.common.Util;
import com.pure.db.TFilm;
import com.pure.db.TFllow;
import com.pure.db.TUser;
import com.pure.service.FilmService;
import com.pure.service.UserService;

@Controller
public class FilmController extends BaseController {
 
	
	@Resource
	private FilmService filmService;
	
	@Resource 
	private UserService userService;
	
	/*
	 *获取电影分类数据 
	 **/
	@RequestMapping(value = "getFilmPageInfo", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData getFilmPageInfo(HttpServletRequest request,Integer pageCurrent, Integer pageSize,TFilm tFilm) {
		ReturnData returnData = new ReturnData();
		try {
			PageInfo res=filmService.getFilmPageInfo(pageCurrent,pageSize,tFilm);
			returnData.setData(res);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	@RequestMapping(value = "getFilmById", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData getFilmById(HttpServletRequest request,Integer id) {
		ReturnData returnData = new ReturnData();
		try {
			TUser user=(TUser) request.getSession().getAttribute("user");
			Map<String,Object> res=filmService.getFilmById(id);
			if (user != null) {
				int temp=userService.getUserFollowByFilmId(id);
				if (temp>0) {
					res.put("status", "已收藏");
				}
			}else {
				res.put("status", "未收藏");
			}
			returnData.setData(res);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	@RequestMapping(value = "deleteFilmById", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ReturnData deleteFilmById(Integer id) {
		ReturnData returnData = new ReturnData();
		try {
			filmService.deleteFilmById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	
	@RequestMapping(value = "saveFilm", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ReturnData saveFilm(TFilm tFilm,String releasedate1) {
		ReturnData returnData = new ReturnData();
		try {
			if (!Util.isNullString(releasedate1)) {
				tFilm.setReleasedate(Util.StrToDate(releasedate1, "yyyy-MM-dd"));
			}
			filmService.saveFilm(tFilm);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnData.setCode(SysCodeMsg.CODE_10000);
			returnData.setMessage(SysCodeMsg.MSG_10000);
		}
		return returnData;
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public ReturnData upload(@RequestParam("file") MultipartFile file[], String spId, HttpServletRequest request) {
		ReturnData returnData = new ReturnData();
		String savepathUrl = "";
		try {
			for (MultipartFile uploadFile : file) {
				if (file == null || file.length == 0) {
					returnData.setData("文件为空！");
				} else {
					String fileType = uploadFile.getOriginalFilename().substring(
							uploadFile.getOriginalFilename().indexOf("."), uploadFile.getOriginalFilename().length());
					String savePath = Util.getUuid("1") + fileType;
					
					//String path = request.getSession().getServletContext().getRealPath("//attachments//");
					/* 上传图片地址 */
					String rela = "E:/java/FilmRecommend/FilmRecommend/static/img/";
					File folder = new File(rela);
					folder.mkdir();
					FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), new File(rela, savePath));
					if (file.length == 1) {
						savepathUrl = savePath;
					} else {
						savepathUrl += savePath + ",";
					}
				}
			}
			returnData.setData(savepathUrl);
		} catch (Exception e) {
			e.printStackTrace();
			returnData.setData(SysCodeMsg.CODE_20001);
			returnData.setMessage(SysCodeMsg.MSG_20001);
		}
		return returnData;
	}
	
	
}
