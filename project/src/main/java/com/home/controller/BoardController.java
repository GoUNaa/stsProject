package com.home.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.home.domain.BoardBean;
import com.home.domain.PageBean;
import com.home.sevice.BoardService;

@Controller
public class BoardController {
	
	@Inject
	private BoardService boardService;
	
	   @Resource(name = "uploadPath")
	   private String uploadPath;
	   
	   @RequestMapping(value = "/board/list", method = RequestMethod.GET)
	   public String list(Model model,HttpServletRequest request) {
    	   System.out.println("보드컨트롤러 - list");

	      PageBean pbBean=new PageBean();
	      // 한페이지에 보여줄 글개수 설정 pageSize
	      pbBean.setPageSize(15);
	   //  pageNum 파라미터 가져오기
	      String pageNum=request.getParameter("pageNum");
	      // pageNum없으면  "1" 페이지 설정
	      if(pageNum==null) {
	         pbBean.setPageNum("1");
	      }else {
	         pbBean.setPageNum(pageNum);
	      }
	      // List boardList   = getBoardList(pbBean) 메서드 만들 호출
	      List<BoardBean> boardList=boardService.getBoardList(pbBean);
	      
	      //setCount 호출 => 페이징 관련 작업  PageBean 안에서 함
	      pbBean.setCount(boardService.getBoardCount());
	      
	      //model 데이터 담아서 보내기
	      model.addAttribute("boardList",boardList);
	      model.addAttribute("pbBean",pbBean);
	      
//	      /WEB-INF/views/center/notice.jsp
	      return "center/notice";
	   }
	
	 // 글 보기
	   @RequestMapping(value = "/board/content", method = RequestMethod.GET)
	   public String content(Model model,HttpServletRequest request) {
    	   System.out.println("보드컨트롤러 - content");
		   //request num 파라미터 가져오기
	      int num=Integer.parseInt(request.getParameter("num"));
	      // 조회수 1증가 
	      //update board set readcount=readcount+1 where num=?
	      boardService.updateReadcount(num);
	      
	      BoardBean bb=boardService.getBoard(num);
	      
	      //model 데이터 담아서 보내기
	      model.addAttribute("bb",bb);
	      
//	      /WEB-INF/views/center/content.jsp
	      return "center/content";
	   }
	   
	   
	   // /board/write
	   @RequestMapping(value = "/board/write", method = RequestMethod.GET)
	   public String write() {
    	   System.out.println("보드컨트롤러 - writeGET");

//	      /WEB-INF/views/center/writeForm.jsp
	      return "center/writeForm";
	   }
	   
	   @RequestMapping(value = "/board/write", method = RequestMethod.POST)
	   public String writePost(BoardBean bb) {
    	   System.out.println("보드컨트롤러 - writePost");
	      boardService.insertBoard(bb);
	      
	      // response.sendRedirect() 같음
	      return "redirect:/board/list";
	   } 
	   
       @RequestMapping(value = "/board/update", method = RequestMethod.GET)
       public String update(Model model,HttpServletRequest request) {
    	   System.out.println("보드컨트롤러 - updateGET");

    	   //request num 파라미터 가져오기
           int num = Integer.parseInt(request.getParameter("num"));
                
          BoardBean bb = boardService.getBoard(num);
                
          //model 데이터 담아서 보내기
          model.addAttribute("bb",bb);
                
          return "center/updateForm";
       }
       
       @RequestMapping(value = "/board/update", method = RequestMethod.POST)
       public String updatePost(BoardBean bb,Model model) {
    	   System.out.println("보드컨트롤러 - updatePost");
          // num pass 일치 여부 확인
          // select * from board where num=? and pass=?
          BoardBean bb2 = boardService.numCheck(bb);
          if(bb2!=null) {
             //update board set name=?,subject=?,content=? where num=?
             // // num pass 일치
             boardService.updateBoard(bb);
             // response.sendRedirect() 같음
             return "redirect:/board/list";
          }else {
             // num pass 틀림
             // msg =" 입력하신 정보는 틀립니다"  model 저장
             model.addAttribute("msg","입력하신 정보는 틀립니다");
//             /WEB-INF/views/center/msg.jsp
             return "center/msg";
          }
       }
	   
	   
       @RequestMapping(value = "/board/delete", method = RequestMethod.GET)
       public String delete(Model model,HttpServletRequest request) {
    	   System.out.println("보드컨트롤러 - deleteGET");
    	   
          //request num 파라미터 가져오기
          int num=Integer.parseInt(request.getParameter("num"));
                
          BoardBean bb=boardService.getBoard(num);
	      
	      //model 데이터 담아서 보내기
	      model.addAttribute("bb",bb);
                
//          /WEB-INF/views/center/deleteForm.jsp
          return "center/deleteForm";
       }
       
       @RequestMapping(value = "/board/delete", method = RequestMethod.POST)
       public String deletePost(BoardBean bb,Model model) {
    	   System.out.println("보드컨트롤러 - deletePost");

          // num pass 일치 여부 확인
          // select * from board where num=? and pass=?
          BoardBean bb2=boardService.numCheck(bb);
          
          if(bb2!=null) {
             //delete from board where num=?
             // // num pass 일치
             boardService.deleteBoard(bb);
             // response.sendRedirect() 같음
             return "redirect:/board/list";
          }else {
             // num pass 틀림
             // msg =" 입력하신 정보는 틀립니다"  model 저장
             model.addAttribute("msg","입력하신 정보는 틀립니다");
//             /WEB-INF/views/center/msg.jsp
             return "center/msg";
          }
       }
	   
	   @RequestMapping(value = "/board/reWrite", method = RequestMethod.GET)
	   public String reWrite(Model model,HttpServletRequest request) {
    	   System.out.println("보드컨트롤러 - reWriteGET");
           //request num 파라미터 가져오기
           int num=Integer.parseInt(request.getParameter("num"));
           int re_ref=Integer.parseInt(request.getParameter("re_ref"));
           int re_lev=Integer.parseInt(request.getParameter("re_lev"));
           int re_seq=Integer.parseInt(request.getParameter("re_seq"));
           
           BoardBean bb=new BoardBean();
           bb.setNum(num);
           bb.setRe_ref(re_ref);
           bb.setRe_lev(re_lev);
           bb.setRe_seq(re_seq);
                 
           //model 데이터 담아서 보내기
           model.addAttribute("bb",bb);
           
	      return "center/reWriteForm";
	   }
	   
	   @RequestMapping(value = "/board/reWrite", method = RequestMethod.POST)
	   public String reWritePost(BoardBean bb) {
    	   System.out.println("보드컨트롤러 - reWritePost");
	      boardService.reInsertBoard(bb);
	      
	      return "redirect:/board/list";
	   } 
	   
	   
	   
	   
	   
	   
	   
	   
}
