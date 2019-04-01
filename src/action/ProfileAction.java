package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import domain.Assessment;
import domain.Relation;
import domain.User;
import domain.Weibo;
import service.Recommend;
import service.UserService;

public class ProfileAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7646992101483080649L;
	private String uid;

	private Recommend recomSevice;
	private UserService userService;

	public String load() {
		try {
			if (uid == null || uid.trim().isEmpty()) {
				return "success";
			}
			User user = userService.getUser(uid.trim());
			if (user == null) {
				return "success";
			}
			List<Relation> relations = userService.getRelation(uid.trim());
			List<Weibo> weibos = userService.getWeiboes(uid.trim());
			Assessment assessment = recomSevice.assess(uid.trim());

			user.setAssessment(assessment);
			ActionContext.getContext().getSession().put("uid", uid.trim());
			ActionContext.getContext().getSession().put("user", user);
			ActionContext.getContext().getSession().put("emotion", assessment.getEmotion());
			ActionContext.getContext().getSession().put("psychology", assessment.getPsychology());
			ActionContext.getContext().getSession().put("relations", relations);
			ActionContext.getContext().getSession().put("weibos", weibos);
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
