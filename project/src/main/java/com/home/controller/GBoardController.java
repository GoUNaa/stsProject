package com.home.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.home.domain.GBoardBean;
import com.home.domain.PageBean;
import com.home.sevice.GBoardService;

@Controller
public class GBoardController {
	@Inject
	private GBoardService gBoardService;
	
	  @RequestMapping(value = "/gboard/glist", method = RequestMethod.GET)
      public String flist(Model model,HttpServletRequest request) {
         PageBean pbBean = new PageBean();
         // 한페이지에 보여줄 글개수 설정 pageSize
         pbBean.setPageSize(15);
      //  pageNum 파라미터 가져오기
         String pageNum = request.getParameter("pageNum");
         // pageNum없으면  "1" 페이지 설정
         if(pageNum==null) {
            pbBean.setPageNum("1");
         }else {
            pbBean.setPageNum(pageNum);
         }
         List<GBoardBean> gboardList = gBoardService.getGBoardList(pbBean);
         if(gboardList == null) {
        	 System.out.println("gboardList 널");
        	 System.out.println(gboardList);

         }
         //setCount 호출 => 페이징 관련 작업  PageBean 안에서 함
         pbBean.setCount(gBoardService.getGBoardCount());
         
         //model 데이터 담아서 보내기
         model.addAttribute("gboardList",gboardList);
         model.addAttribute("pbBean",pbBean);
         
//         /WEB-INF/views/center/fnotice.jsp
         return "gcenter/gnotice";
      }











}
