package com.home.controller;

import java.io.File;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.home.domain.FBoardBean;
import com.home.domain.PageBean;
import com.home.sevice.FBoardService;

@Controller
public class FBoardController {
	@Inject
	private FBoardService fBoardService;
	
	   @Resource(name = "uploadPath")
	   private String uploadPath;
	   
       @RequestMapping(value = "/fboard/flist", method = RequestMethod.GET)
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
          List<FBoardBean> fboardList = fBoardService.listSearch(pbBean);
          
          //setCount 호출 => 페이징 관련 작업  PageBean 안에서 함
          pbBean.setCount(fBoardService.getSearchCount(pbBean));
          
          //model 데이터 담아서 보내기
          model.addAttribute("fboardList",fboardList);
          model.addAttribute("pbBean",pbBean);
          
//          /WEB-INF/views/center/fnotice.jsp
          return "fcenter/fnotice";
       }
	   
       
       @RequestMapping(value = "/fboard/fwrite", method = RequestMethod.GET)
       public String fwrite() {
          return "fcenter/fwriteForm";
       }
       
          @RequestMapping(value = "/fboard/fwrite", method = RequestMethod.POST)
          public String fwritePost(HttpServletRequest request,MultipartFile file) throws Exception{
             // 파일이름  랜덤문자_파일이름
             UUID uid = UUID.randomUUID();
             String saveName = uid.toString()+"_"+file.getOriginalFilename();
             System.out.println(uploadPath+":"+saveName);
             // 실제파일 => upload폴더에 복사
             File target = new File(uploadPath,saveName);
             FileCopyUtils.copy(file.getBytes(), target);
             
             FBoardBean fb=new FBoardBean();
             fb.setName(request.getParameter("name"));
             fb.setPass(request.getParameter("pass"));
             fb.setSubject(request.getParameter("subject"));
             fb.setContent(request.getParameter("content"));
             fb.setFile(saveName);
              System.out.println("파일 : " + fb.getFile());
             fBoardService.insertFBoard(fb);
             
             return "redirect:/fboard/flist";
          }
       
          @RequestMapping(value = "/fboard/fcontent", method = RequestMethod.GET)
          public String fcontent(Model model,HttpServletRequest request) {
             //request num 파라미터 가져오기
             int num = Integer.parseInt(request.getParameter("num"));

             fBoardService.updateReadcount(num);
             
             FBoardBean fb = fBoardService.getFBoard(num);
             
             //model 데이터 담아서 보내기
             model.addAttribute("fb",fb);
             
//             /WEB-INF/views/center/fcontent.jsp
             return "fcenter/fcontent";
          }
          
      	@RequestMapping(value = "/fboard/filedown", method = RequestMethod.GET)
		public void fileDown(HttpServletRequest request, HttpServletResponse response) throws Exception{
			String fileName = request.getParameter( "file_name" );
			
			// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
			byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File(uploadPath,fileName));
			
			response.setContentType("application/octet-stream");
			response.setContentLength(fileByte.length);
			response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(fileName, "UTF-8")+"\";");
			response.getOutputStream().write(fileByte);
			response.getOutputStream().flush();
			response.getOutputStream().close();

    			 
     }
          
        @RequestMapping(value = "/fboard/fupdate", method = RequestMethod.GET)
        public String fupdate(Model model,HttpServletRequest request) {
        	System.out.println("파일보드컨트롤러 - 업데이트겟");
           //request num 파라미터 가져오기
           int num=Integer.parseInt(request.getParameter("num"));
                 
           FBoardBean fb = fBoardService.getFBoard(num);
                 
           //model 데이터 담아서 보내기
           model.addAttribute("fb",fb);
                 
//           /WEB-INF/views/center/fupdateForm.jsp
           return "fcenter/fupdateForm";
        }   
        
        
        @RequestMapping(value = "/fboard/fupdate", method = RequestMethod.POST)
        public String fupdatePost(HttpServletRequest request,MultipartFile file, Model model) throws Exception{
        	System.out.println("파일보드컨트롤러 - 업데이트포스트");
           String saveName="";
           if(!file.isEmpty()) {
              // 파일이름  랜덤문자_파일이름
              UUID uid=UUID.randomUUID();
              saveName=uid.toString()+"_"+file.getOriginalFilename();
              System.out.println(uploadPath+":"+saveName);
              // 실제파일 => upload폴더에 복사
              File target=new File(uploadPath,saveName);
              FileCopyUtils.copy(file.getBytes(), target);
           } else {
              saveName=request.getParameter("oldfile");
           }
           
           
           FBoardBean fb=new FBoardBean();
           fb.setNum(Integer.parseInt(request.getParameter("num")));
           fb.setName(request.getParameter("name"));
           fb.setPass(request.getParameter("pass"));
           fb.setSubject(request.getParameter("subject"));
           fb.setContent(request.getParameter("content"));
           fb.setDate((new Timestamp(System.currentTimeMillis())));
           fb.setFile(saveName);
           
           		System.out.println("-----------------------------");
           		System.out.println("num : " + fb.getNum());
                System.out.println("name : " + fb.getName());
                System.out.println("pass : " + fb.getPass());
                System.out.println("subject : " + fb.getSubject());
                System.out.println("content : " + fb.getContent());
                System.out.println("-----------------------------");

           FBoardBean fb2 = fBoardService.numCheck(fb);
           if(fb2!=null) {
              //update board set name=?,subject=?,content=?,file=? where num=?
              // // num pass 일치
              fBoardService.fupdateBoard(fb);
              // response.sendRedirect() 같음
              return "redirect:/fboard/flist";
           }else {
              // num pass 틀림
              // msg =" 입력하신 정보는 틀립니다"  model 저장
              model.addAttribute("msg","입력하신 정보는 틀립니다");
              return "center/msg";
           }
        }
        
       // /board/delete?num=${bb.num }
       @RequestMapping(value = "/fboard/fdelete", method = RequestMethod.GET)
       public String fdelete(Model model,HttpServletRequest request) {
          //request num 파라미터 가져오기
          int num = Integer.parseInt(request.getParameter("num"));
                
          FBoardBean fb = fBoardService.getFBoard(num);
          //model 데이터 담아서 보내기
          model.addAttribute("fb",fb);
                
//          /WEB-INF/views/center/deleteForm.jsp
          return "fcenter/fdeleteForm";
       }
       
//       　http://localhost:8080/FunWeb/board/fdelete　　　/board/fdelete　가상주소 POST방식
       @RequestMapping(value = "/fboard/fdelete", method = RequestMethod.POST)
       public String fdeletePost(FBoardBean fb,Model model) {
          // num pass 일치 여부 확인
          // select * from board where num=? and pass=?
          FBoardBean fb2 = fBoardService.numCheck(fb);
          
          if(fb2 != null) {
             //delete from board where num=?
             // // num pass 일치
             fBoardService.deleteBoard(fb);
             // response.sendRedirect() 같음
             return "redirect:/fboard/flist";
             
          } else {
             // num pass 틀림
             // msg =" 입력하신 정보는 틀립니다"  model 저장
             model.addAttribute("msg","입력하신 정보는 틀립니다");
//             /WEB-INF/views/center/msg.jsp
             return "fcenter/msg";
          }
       }
}
