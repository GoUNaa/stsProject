package com.home.controller;

import java.io.File;
import java.net.URLEncoder;
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
	   
	   //http://localhost:8080/FunWeb/board/flist　　          /board/flist　가상주소
       //http://localhost:8080/FunWeb/board/flist?pageNum=1　　/board/flist　가상주소
       @RequestMapping(value = "/fboard/flist", method = RequestMethod.GET)
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
          // List boardList   = getBoardList(pbBean) 메서드 만들 호출
          List<FBoardBean> fboardList = fBoardService.getFBoardList(pbBean);
          
          //setCount 호출 => 페이징 관련 작업  PageBean 안에서 함
          pbBean.setCount(fBoardService.getFBoardCount());
          
          //model 데이터 담아서 보내기
          model.addAttribute("fboardList",fboardList);
          model.addAttribute("pbBean",pbBean);
          
//          /WEB-INF/views/center/fnotice.jsp
          return "fcenter/fnotice";
       }
	   
       //  /board/fupdate?num=${bb.num}
       //http://localhost:8080/FunWeb/board/fupdate?num=번호 /board/fupdate　가상주소
       @RequestMapping(value = "/board/fupdate", method = RequestMethod.GET)
       public String fupdate(Model model,HttpServletRequest request) {
          //request num 파라미터 가져오기
          int num=Integer.parseInt(request.getParameter("num"));
                
          FBoardBean fb = fBoardService.getFBoard(num);
                
          //model 데이터 담아서 보내기
          model.addAttribute("fb",fb);
                
//          /WEB-INF/views/center/fupdateForm.jsp
          return "center/fupdateForm";
       }   
       
       // /board/fwrite
       //http://localhost:8080/FunWeb/board/fwrite　　/board/fwrite　가상주소
       @RequestMapping(value = "/fboard/fwrite", method = RequestMethod.GET)
       public String fwrite() {
//          /WEB-INF/views/center/fwriteForm.jsp
          return "fcenter/fwriteForm";
       }
       
//      　http://localhost:8080/FunWeb/board/fwrite　　　/board/fwrite　가상주소 POST방식
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
             int num=Integer.parseInt(request.getParameter("num"));

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
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
       
       
       
       @RequestMapping(value = "/fboard/fupdate", method = RequestMethod.POST)
       public String fupdatePost(HttpServletRequest request,MultipartFile file, Model model) throws Exception{
          String saveName="";
          if(!file.isEmpty()) {
             // 파일이름  랜덤문자_파일이름
             UUID uid=UUID.randomUUID();
             saveName = uid.toString()+"_"+file.getOriginalFilename();
             System.out.println(uploadPath+":"+saveName);
             // 실제파일 => upload폴더에 복사
             File target = new File(uploadPath,saveName);
             FileCopyUtils.copy(file.getBytes(), target);
          } else {
             saveName=request.getParameter("oldfile");
          }
          
          
          FBoardBean fb=new FBoardBean();
          fb.setName(request.getParameter("name"));
          fb.setPass(request.getParameter("pass"));
          fb.setSubject(request.getParameter("subject"));
          fb.setContent(request.getParameter("content"));
          fb.setFile(saveName);
                
          FBoardBean fb2 = fBoardService.numCheck(fb);
          if(fb2 != null) {
             //update board set name=?,subject=?,content=?,file=? where num=?
             // // num pass 일치
        	  fBoardService.fupdateBoard(fb);
//             http://localhost:8080/FunWeb/board/flist 가상주소 이동
             // response.sendRedirect() 같음
             return "redirect:/fboard/flist";
          }else {
             // num pass 틀림
             // msg =" 입력하신 정보는 틀립니다"  model 저장
             model.addAttribute("msg","입력하신 정보는 틀립니다");
//             /WEB-INF/views/center/msg.jsp
             return "fcenter/msg";
          }
       }
	   
}
