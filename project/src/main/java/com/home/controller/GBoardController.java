package com.home.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.home.domain.FBoardBean;
import com.home.domain.GBoardBean;
import com.home.domain.PageBean;
import com.home.sevice.GBoardService;

@Controller
public class GBoardController {
	@Inject
	private GBoardService gBoardService;
	
	 @Resource(name = "uploadPaths")
	   private String uploadPaths;
	 
	  @RequestMapping(value = "/gboard/glist", method = RequestMethod.GET)
      public String flist(Model model,HttpServletRequest request) {
		  String search = request.getParameter("search");
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
         
         if(search != null) {
        	 pbBean.setSearch(search);
         } else {
        	 pbBean.setSearch("");
         }
         
         List<GBoardBean> gboardList = gBoardService.listSearch(pbBean);

         
         //setCount 호출 => 페이징 관련 작업  PageBean 안에서 함
         pbBean.setCount(gBoardService.SearchCount(pbBean));
         
         //model 데이터 담아서 보내기
         model.addAttribute("gboardList",gboardList);
         model.addAttribute("pbBean",pbBean);
         
//         /WEB-INF/views/center/fnotice.jsp
         return "gcenter/gnotice";
      }

	  @RequestMapping(value = "/gboard/gwrite", method = RequestMethod.GET)
      public String gwrite() {
         return "gcenter/gwriteForm";
      }

	  @RequestMapping(value = "/gboard/gwrite", method = RequestMethod.POST)
      public String gwritePost(HttpServletRequest request,MultipartFile file) throws Exception{
         // 파일이름  랜덤문자_파일이름
         UUID uid = UUID.randomUUID();
         String saveName = uid.toString()+"_"+file.getOriginalFilename();
         System.out.println(uploadPaths+":"+saveName);
         // 실제파일 => upload폴더에 복사
         File target = new File(uploadPaths,saveName);
         FileCopyUtils.copy(file.getBytes(), target);
         
         GBoardBean gb = new GBoardBean();
         gb.setName(request.getParameter("name"));
         gb.setPass(request.getParameter("pass"));
         gb.setSubject(request.getParameter("subject"));
         gb.setContent(request.getParameter("content"));
         gb.setFile(saveName);
          System.out.println("파일 : " + gb.getFile());
         gBoardService.insertGBoard(gb);
         
         return "redirect:/gboard/glist";
      }

	   @RequestMapping(value = "/gboard/gcontent", method = RequestMethod.GET)
       public String gcontent(Model model,HttpServletRequest request) {
          //request num 파라미터 가져오기
          int num = Integer.parseInt(request.getParameter("num"));
          System.out.println("num : " + num);
          gBoardService.updateReadCount(num);
          
          GBoardBean gb = gBoardService.getGBoard(num);
          
          //model 데이터 담아서 보내기
          model.addAttribute("gb",gb);
          
//          /WEB-INF/views/center/fcontent.jsp
          return "gcenter/gcontent";
       }

	   @RequestMapping(value = "/gboard/gupdate", method = RequestMethod.GET)
       public String fupdate(Model model,HttpServletRequest request) {
          //request num 파라미터 가져오기
          int num=Integer.parseInt(request.getParameter("num"));
                
          GBoardBean gb = gBoardService.getGBoard(num);
                
          //model 데이터 담아서 보내기
          model.addAttribute("gb",gb);
                
//          /WEB-INF/views/center/fupdateForm.jsp
          return "gcenter/gupdateForm";
       }   

	   @RequestMapping(value = "/gboard/gupdate", method = RequestMethod.POST)
       public String fupdatePost(HttpServletRequest request,MultipartFile file, Model model) throws Exception{
          String saveName="";
          if(!file.isEmpty()) {
             // 파일이름  랜덤문자_파일이름
             UUID uid=UUID.randomUUID();
             saveName=uid.toString()+"_"+file.getOriginalFilename();
             System.out.println(uploadPaths+":"+saveName);
             // 실제파일 => upload폴더에 복사
             File target=new File(uploadPaths,saveName);
             FileCopyUtils.copy(file.getBytes(), target);
          } else {
             saveName=request.getParameter("oldfile");
          }
          
          
          GBoardBean gb=new GBoardBean();
          gb.setNum(Integer.parseInt(request.getParameter("num")));
          gb.setName(request.getParameter("name"));
          gb.setPass(request.getParameter("pass"));
          gb.setSubject(request.getParameter("subject"));
          gb.setContent(request.getParameter("content"));
          gb.setDate((new Timestamp(System.currentTimeMillis())));
          gb.setFile(saveName);
          
          	   System.out.println("-----------------------------");
          	   System.out.println("num : " + gb.getNum());
               System.out.println("name : " + gb.getName());
               System.out.println("pass : " + gb.getPass());
               System.out.println("subject : " + gb.getSubject());
               System.out.println("content : " + gb.getContent());
               System.out.println("-----------------------------");

          GBoardBean gb2 = gBoardService.numCheck(gb);
          if(gb2!=null) {
             // // num pass 일치
             gBoardService.updateGBoard(gb);
             return "redirect:/gboard/glist";
          }else {
             // num pass 틀림
             // msg =" 입력하신 정보는 틀립니다"  model 저장
             model.addAttribute("msg","입력하신 정보는 틀립니다");
             return "gcenter/msg";
          }
       }

       @RequestMapping(value = "/gboard/gdelete", method = RequestMethod.GET)
       public String gdelete(Model model,HttpServletRequest request) {
          //request num 파라미터 가져오기
          int num = Integer.parseInt(request.getParameter("num"));
                
          GBoardBean gb = gBoardService.getGBoard(num);
          //model 데이터 담아서 보내기
          model.addAttribute("gb",gb);
                
//          /WEB-INF/views/center/deleteForm.jsp
          return "gcenter/gdeleteForm";
       }
       
//       　http://localhost:8080/FunWeb/board/fdelete　　　/board/fdelete　가상주소 POST방식
       @RequestMapping(value = "/gboard/gdelete", method = RequestMethod.POST)
       public String fdeletePost(GBoardBean gb,Model model) {
          // num pass 일치 여부 확인
          // select * from board where num=? and pass=?
          GBoardBean gb2 = gBoardService.numCheck(gb);
          
          if(gb2 != null) {
             //delete from board where num=?
             // // num pass 일치
             gBoardService.deleteBoard(gb);
             // response.sendRedirect() 같음
             return "redirect:/gboard/glist";
             
          } else {
             // num pass 틀림
             // msg =" 입력하신 정보는 틀립니다"  model 저장
             model.addAttribute("msg","입력하신 정보는 틀립니다");
//             /WEB-INF/views/center/msg.jsp
             return "fcenter/msg";
          }
       }
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
}
