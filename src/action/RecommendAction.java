package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import domain.Assessment;
import domain.Recommendation;
import domain.User;
import service.Recommend;
import service.UserService;

public class RecommendAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5926644022655914193L;
	private String uid;
	
	private Recommend recomSevice;
	private UserService userService;
	
	public String recommend()
	{
		try {
			if(uid == null ||uid.trim().isEmpty() )
			{
				return "success";
			}
			User user = userService.getUser(uid.trim());
			if(user == null )
			{
				ActionContext.getContext().getSession().put("uidMessage", "Wrong Uid!");
				return "success";
			}
			Assessment assessment = recomSevice.assess(uid.trim());
			List<Recommendation> result = recomSevice.recommend(uid.trim());
			if(result == null || null == assessment)
				ActionContext.getContext().getSession().put("uidMessage", "Wrong Uid!");
			else {
				user.setAssessment(assessment);
				ActionContext.getContext().getSession().put("uid", uid.trim());
				ActionContext.getContext().getSession().put("user", user);
				ActionContext.getContext().getSession().put("emotion", assessment.getEmotion());
				ActionContext.getContext().getSession().put("psychology", assessment.getPsychology());
				ActionContext.getContext().getSession().put("recommendation", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	
	public String random()
	{
		try {
			User user = userService.getUser();
			if(user == null )
			{
				ActionContext.getContext().getSession().put("uidMessage", "Wrong Uid!");
				return "success";
			}
			Assessment assessment = recomSevice.assess(user.getUid());
			List<Recommendation> result = recomSevice.recommend(user.getUid());
			if(result == null || null == assessment)
				ActionContext.getContext().getSession().put("uidMessage", "Wrong Uid!");
			else {
				user.setAssessment(assessment);
				ActionContext.getContext().getSession().put("uid", user.getUid());
				ActionContext.getContext().getSession().put("user", user);
				ActionContext.getContext().getSession().put("emotion", assessment.getEmotion());
				ActionContext.getContext().getSession().put("psychology", assessment.getPsychology());
				ActionContext.getContext().getSession().put("recommendation", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setRecomSevice(Recommend recomSevice) {
		this.recomSevice = recomSevice;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
