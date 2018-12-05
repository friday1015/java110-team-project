package leadme.web;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import leadme.domain.Guide;
import leadme.service.GuideService;

@Controller
@RequestMapping("/guide")
public class GuideController {

  @Autowired GuideService guideService;

  @RequestMapping(value="profile/{gmno}")
  public String login(@PathVariable String gmno, Model model) {

    System.out.println(gmno);
    model.addAttribute("gmno", gmno);

    return "/guide/profile";
  }


  @RequestMapping(value="guideProfile.do", method=RequestMethod.POST)
  @ResponseBody
  public Map<String ,Object> guideProfile(@RequestBody Guide guide) {

    Map<String ,Object> data = new HashMap<String, Object>();

    try {

      data.put("member", guideService.guideFindByGno(guide));
      return data;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }
  
  
  @RequestMapping(value="profileModify")
  public String profileModify() {

    return "/guide/profileModify";
  }
  
  @RequestMapping(value="profileModify.do", method=RequestMethod.POST)
  @ResponseBody
  public Map<String ,Object> profileModify(@RequestBody String jsonData) {
    
    Map<String ,Object> message = new HashMap<>();
    
    try {
      guideService.profileModify(jsonData);
      message.put("message", "성공");
      return message;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    
    
    
    //Map<String, Object> map = new HashMap<String, Object>();
    /*try {
      System.out.println("여기는 컨트롤러입니다");
      guideService.profileModify(map);
      
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }*/
  }
  
  
  @RequestMapping(value="myTravelStatus.do", method=RequestMethod.POST)
  @ResponseBody
  public Map<String ,Object> myTravelStatus(@RequestBody Guide guide) {
    
    guideService.myTravelStatus();
    
    return null;

  }
  
  @RequestMapping(value="cancelTravelStatus.do", method=RequestMethod.POST)
  @ResponseBody
  public Map<String ,Object> cancelTravelStatus(@RequestBody Guide guide) {
    
    guideService.cancelTravelStatus();
    
    return null;

  }
  
  @RequestMapping(value="goneTravelStatus.do", method=RequestMethod.POST)
  @ResponseBody
  public Map<String ,Object> goneTravelStatus(@RequestBody Guide guide) {
    
    guideService.goneTravelStatus();
    
    return null;

  }
}
