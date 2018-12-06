package leadme.web;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import leadme.domain.Comment;
import leadme.service.FileService;
import leadme.service.TourService;

@Controller
@RequestMapping("/tour")
public class TourCommentControllerController{
  
  @Autowired TourService tourService;
  @Autowired FileService fileService;
  

  @RequestMapping(value="comment/{value}")
  public String comment(@PathVariable String value,
                        @MatrixVariable String mno,
                        @MatrixVariable String tno,
                        Model model) { 
    
    System.out.println("mno : " + value);
    System.out.println("mno : " + mno);
    System.out.println("tno : " + tno);
    
    model.addAttribute("tour", tourService.tourSelect(tno));
    
    return "/tour/comment";
  }

  @RequestMapping(value = "/commentPhoto.do")
  @ResponseBody
  public Map<String, Object> commentPhoto(MultipartHttpServletRequest multi) {

    System.out.println("렛잇꼬?");
    Map<String, Object> data = new HashMap<>();
    try {
      data.put("filename", fileService.makePhotoFile(multi));
      return data;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  @RequestMapping(value = "/commentContent.do")
  @ResponseBody
  public Map<String, Object> commentContent(@RequestBody Comment comment) {
    
    System.out.println(comment);
    Map<String, Object> message = new HashMap<>();
    try {
      tourService.enrollmentComment(comment);
      message.put("message", "성공");
      return message;
    } catch (Exception e) {
      return null;
    }
  }

}
